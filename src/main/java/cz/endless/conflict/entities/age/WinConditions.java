package cz.endless.conflict.entities.age;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by dobeji1 on 18.03.2019.
 */

@Table(name = "WIN_CONDITIONS")
@Entity
public class WinConditions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<WinCondition> winCondition;

    @Column(name = "ALL_REQUIRED")
    private boolean allRequired;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinConditions that = (WinConditions) o;
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

    public List<WinCondition> getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(List<WinCondition> winCondition) {
        this.winCondition = winCondition;
    }

    public boolean isAllRequired() {
        return allRequired;
    }

    public void setAllRequired(boolean allRequired) {
        this.allRequired = allRequired;
    }
}
