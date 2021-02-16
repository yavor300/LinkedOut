package app.linkedout.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal budget;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String town;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Employee> employees;
}
