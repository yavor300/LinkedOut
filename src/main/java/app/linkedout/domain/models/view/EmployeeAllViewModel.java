package app.linkedout.domain.models.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeAllViewModel {
    private String id;
    private String birthday;
    private String firstName;
    private String jobTitle;
    private String lastName;
}
