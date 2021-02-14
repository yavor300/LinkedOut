package app.linkedout.domain.models.service;

import app.linkedout.domain.entities.enums.EducationalLevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeServiceModel {
    private String id;
    private LocalDate birthday;
    private EducationalLevelEnum educationLevel;
    private String firstName;
    private String jobTitle;
    private String lastName;
    private BigDecimal salary;
    private CompanyServiceModel company;
}
