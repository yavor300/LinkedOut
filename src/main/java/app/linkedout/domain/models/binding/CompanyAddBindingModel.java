package app.linkedout.domain.models.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CompanyAddBindingModel {
    @NotBlank(message = "Name must be added.")
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 characters.")
    private String name;
    @NotBlank(message = "Town must be specified.")
    @Size(min = 2, max = 10, message = "Town must be between 2 and 10 characters")
    private String town;
    @NotBlank(message = "Description must be added.")
    @Size(min = 10, message = "Description must be at least 10 characters.")
    private String description;
    @NotNull(message = "Budget must be added.")
    @Positive(message = "Budget must be positive.")
    private BigDecimal budget;
}
