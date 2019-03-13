package cz.endless.conflict.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by dobeji1 on 13.03.2019.
 */
@Table(name = "AGE")
@Entity
public class Age implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUMBER")
    private int number;

    @ManyToOne
    @JoinColumn(name = "AGE_CONFIGURATION_ID")
    private AgeConfiguration ageConfiguration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age = (Age) o;
        return Objects.equals(id, age.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public AgeConfiguration getAgeConfiguration() {
        return ageConfiguration;
    }

    public void setAgeConfiguration(AgeConfiguration ageConfiguration) {
        this.ageConfiguration = ageConfiguration;
    }
}
