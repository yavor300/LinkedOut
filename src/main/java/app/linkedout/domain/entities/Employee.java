package app.linkedout.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseEntity {
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false, name = "education_level")
    private String educationLevel;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private BigDecimal salary;
    @ManyToOne
    private Company company;
}
