package app.linkedout.service;

import app.linkedout.domain.models.service.EmployeeServiceModel;

import java.util.Arrays;
import java.util.List;

public interface EmployeeService {
    EmployeeServiceModel add(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAll();

    EmployeeServiceModel findById(String id);

    void deleteById(String id);
}
