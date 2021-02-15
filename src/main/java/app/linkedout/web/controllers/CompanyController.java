package app.linkedout.web.controllers;

import app.linkedout.domain.models.binding.CompanyAddBindingModel;
import app.linkedout.domain.models.service.CompanyServiceModel;
import app.linkedout.domain.models.view.CompanyAddEmployeeViewModel;
import app.linkedout.domain.models.view.CompanyAllViewModel;
import app.linkedout.domain.models.view.CompanyDetailsViewModel;
import app.linkedout.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("companyAddBindingModel")) {
            model.addAttribute("companyAddBindingModel", new CompanyAddBindingModel());
            model.addAttribute("isAdded", true);
        }
        return "company-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid CompanyAddBindingModel companyAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyAddBindingModel", bindingResult);
            return "redirect:add";
        }

        boolean isAdded = companyService.add(modelMapper.map(companyAddBindingModel, CompanyServiceModel.class));

        if (!isAdded) {
            redirectAttributes.addFlashAttribute("companyAddBindingModel", companyAddBindingModel);
            redirectAttributes.addFlashAttribute("isAdded", false);
            return "redirect:add";
        }

        httpSession.setAttribute("recentlyUpdated", LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return "redirect:/";
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("companies", companyService.findAll().stream()
                .map(companyServiceModel -> modelMapper.map(companyServiceModel, CompanyAllViewModel.class))
                .collect(Collectors.toList()));
        return "company-all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("company", modelMapper.map(companyService.findById(id), CompanyDetailsViewModel.class));
        return "company-details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, HttpSession httpSession) {
        companyService.deleteById(id);
        httpSession.setAttribute("recentlyUpdated", LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "redirect:/";
    }
}
