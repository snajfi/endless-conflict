package cz.endless.conflict.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by dobeji1 on 13.03.2019.
 */
@Table(name = "AGE_CONFIGURATION")
@Entity
public class AgeConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STARTED_AT")
    private Date startedAt;

    @Column(name = "ENDED_AT")
    private Date endeddAt;

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

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndeddAt() {
        return endeddAt;
    }

    public void setEndeddAt(Date endeddAt) {
        this.endeddAt = endeddAt;
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
