package app.linkedout.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

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
}
