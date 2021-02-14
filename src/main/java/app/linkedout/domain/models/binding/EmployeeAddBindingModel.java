package app.linkedout.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeAddBindingModel {
    @NotBlank(message = "First name must be added.")
    @Size(min = 2, message = "First name must be minimum 2 characters long.")
    private String firstName;
    @NotBlank(message = "Last name must be added.")
    @Size(min = 2, message = "Last name must be minimum 2 characters long.")
    private String lastName;
    @NotBlank(message = "Education level must be added.")
    private String educationLevel;
    @NotBlank(message = "Company must be added.")
    private String companyName;
    @NotBlank(message = "Job title must be added.")
    private String jobTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthdate must be added.")
    @Past(message = "Birthdate must be in the past.")
    private LocalDate birthday;
    @Positive(message = "Salary must be a positive number.")
    @NotNull(message = "Salary must be added")
    private BigDecimal salary;
}
