package cz.endless.conflict.entities.age;

import cz.endless.conflict.enums.WinConditionType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dobeji1 on 20.03.2019.
 */

@Table(name = "WIN_CONDITION")
@Entity
public class WinCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "WIN_CONDITION_TYPE")
    private WinConditionType winConditionType;

    @Column (name = "VALUE")
    private int value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WinConditionType getWinConditionType() {
        return winConditionType;
    }

    public void setWinConditionType(WinConditionType winConditionType) {
        this.winConditionType = winConditionType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
