package app.linkedout.service.impl;

import app.linkedout.domain.entities.Company;
import app.linkedout.domain.entities.Employee;
import app.linkedout.domain.models.service.CompanyServiceModel;
import app.linkedout.domain.models.service.EmployeeServiceModel;
import app.linkedout.repository.CompanyRepository;
import app.linkedout.repository.EmployeeRepository;
import app.linkedout.service.CompanyService;
import app.linkedout.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(CompanyServiceModel companyServiceModel) {
        if (companyRepository.findByName(companyServiceModel.getName()).isPresent()) {
            return false;
        }

        Company company = modelMapper.map(companyServiceModel, Company.class);
        companyRepository.save(company);

        return true;
    }

    @Override
    public List<CompanyServiceModel> findAll() {
        return companyRepository.findAll()
                .stream().map(c -> modelMapper.map(c, CompanyServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyServiceModel getByName(String name) {
       return modelMapper.map(companyRepository.findByName(name).get(), CompanyServiceModel.class);
    }

    @Override
    public CompanyServiceModel findById(String id) {
        return modelMapper.map(companyRepository.findById(id).get(), CompanyServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        CompanyServiceModel companyServiceModel = findById(id);
        for (EmployeeServiceModel employee : companyServiceModel.getEmployees()) {
            Employee employeeEntity = modelMapper.map(employee, Employee.class);
            employeeEntity.setCompany(null);
            employeeRepository.save(employeeEntity);
        }
        companyRepository.deleteById(id);
    }
}
