package app.linkedout.web.controllers;

import app.linkedout.domain.models.binding.EmployeeAddBindingModel;
import app.linkedout.domain.models.service.EmployeeServiceModel;
import app.linkedout.domain.models.view.CompanyAddEmployeeViewModel;
import app.linkedout.service.CompanyService;
import app.linkedout.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("employeeAddBindingModel")) {
            model.addAttribute("employeeAddBindingModel", new EmployeeAddBindingModel());
        }

        model.addAttribute("companies",companyService.findAll().stream()
                .map(companyServiceModel -> modelMapper.map(companyServiceModel, CompanyAddEmployeeViewModel.class))
                .collect(Collectors.toList()));

        return "employee-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid EmployeeAddBindingModel employeeAddBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeAddBindingModel", bindingResult);
            return "redirect:add";
        }

        employeeService.add(modelMapper.map(employeeAddBindingModel, EmployeeServiceModel.class));

        httpSession.setAttribute("recentlyUpdated", LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return "redirect:/";
    }
}
