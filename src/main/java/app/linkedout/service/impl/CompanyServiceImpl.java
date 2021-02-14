package app.linkedout.service.impl;

import app.linkedout.domain.entities.Company;
import app.linkedout.domain.models.service.CompanyServiceModel;
import app.linkedout.repository.CompanyRepository;
import app.linkedout.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
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
}
