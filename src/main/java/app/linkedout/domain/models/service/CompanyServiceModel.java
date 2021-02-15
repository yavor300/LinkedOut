package app.linkedout.domain.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyServiceModel {
    private String id;
    private BigDecimal budget;
    private String description;
    private String name;
    private String town;
    private List<EmployeeServiceModel> employees;
}
