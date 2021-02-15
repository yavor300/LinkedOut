package app.linkedout.service;

import app.linkedout.domain.entities.Company;
import app.linkedout.domain.models.service.CompanyServiceModel;

import java.util.List;

public interface CompanyService {
    boolean add(CompanyServiceModel companyServiceModel);

    List<CompanyServiceModel> findAll();

    CompanyServiceModel getByName(String name);

    CompanyServiceModel findById(String id);

    void deleteById(String id);
}
