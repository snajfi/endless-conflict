package cz.endless.conflict.entities.age;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by dobeji1 on 13.03.2019.
 */
@NamedQueries({
        @NamedQuery(name = AgeConfiguration.GET_ALL_AGE_CONFIGURATIONS,
                query = "select ac from AgeConfiguration ac"),
        @NamedQuery(name = AgeConfiguration.GET_AGE_CONFIGURATION_BY_ID,
                query = "select ac from AgeConfiguration ac where ac.id = :id")
})
@Table(name = "AGE_CONFIGURATION")
@Entity
public class AgeConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_ALL_AGE_CONFIGURATIONS = "AgeConfiguration.GET_ALL_AGE_CONFIGURATIONS";
    public static final String GET_AGE_CONFIGURATION_BY_ID = "AgeConfiguration.GET_AGE_CONFIGURATION_BY_ID";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROUND_STACK")
    private int roundStack;

    @Column(name = "LOCKED_STACK")
    private int lockedStack;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeConfiguration that = (AgeConfiguration) o;
        return Objects.equals(id, that.id);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoundStack() {
        return roundStack;
    }

    public void setRoundStack(int roundStack) {
        this.roundStack = roundStack;
    }

    public int getLockedStack() {
        return lockedStack;
    }

    public void setLockedStack(int lockedStack) {
        this.lockedStack = lockedStack;
    }
}
