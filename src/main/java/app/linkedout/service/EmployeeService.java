package app.linkedout.service;

import app.linkedout.domain.models.service.EmployeeServiceModel;

import java.util.Arrays;
import java.util.List;

public interface EmployeeService {
    EmployeeServiceModel add(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAll();
}
