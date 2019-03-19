package cz.endless.conflict.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.Objects;

/**
 * Created by dobeji1 on 13.03.2019.
 */
@NamedQueries({
        @NamedQuery(name = Age.GET_ALL_AGES,
                query = "select a from Age a "),
        @NamedQuery(name = Age.GET_LAST_AGE_NUMBER,
                query = "select max(a.number) from Age a"),
        @NamedQuery(name = Age.GET_AGE_BY_NUMBER,
                query = "select a from Age a where a.number = :number")
})
@Table(name = "AGE")
@Entity
public class Age implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_ALL_AGES = "Age.GET_ALL_AGES";
    public static final String GET_LAST_AGE_NUMBER = "Age.GET_LAST_AGE_NUMBER";
    public static final String GET_AGE_BY_NUMBER = "Age.GET_AGE_BY_NUMBER";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUMBER", nullable = false, unique = true)
    private int number;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    private Player creator;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "STARTED_AT")
    private Date startedAt;

    @Column(name = "ENDED_AT")
    private Date endeddAt;

    @ManyToOne(cascade = CascadeType.MERGE)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public AgeConfiguration getAgeConfiguration() {
        return ageConfiguration;
    }

    public void setAgeConfiguration(AgeConfiguration ageConfiguration) {
        this.ageConfiguration = ageConfiguration;
    }
}
