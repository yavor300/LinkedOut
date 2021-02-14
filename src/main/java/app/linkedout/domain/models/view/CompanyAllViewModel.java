package app.linkedout.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CompanyAllViewModel {
    private String id;
    private String description;
    private String name;
    private String town;
}
