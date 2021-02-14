package app.linkedout.service.impl;

import app.linkedout.domain.entities.Employee;
import app.linkedout.domain.models.service.CompanyServiceModel;
import app.linkedout.domain.models.service.EmployeeServiceModel;
import app.linkedout.repository.EmployeeRepository;
import app.linkedout.service.CompanyService;
import app.linkedout.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeServiceModel add(EmployeeServiceModel employeeServiceModel) {
        CompanyServiceModel companyServiceModel = companyService.getByName(employeeServiceModel.getCompany().getName());
        employeeServiceModel.setCompany(companyServiceModel);

        Employee employee = modelMapper.map(employeeServiceModel, Employee.class);
        employeeRepository.save(employee);

        return modelMapper.map(employeeRepository.saveAndFlush(modelMapper.map(employeeServiceModel, Employee.class)), EmployeeServiceModel.class);
    }
}
