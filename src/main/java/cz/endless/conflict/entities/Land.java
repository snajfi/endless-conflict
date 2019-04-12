package cz.endless.conflict.entities;

import cz.endless.conflict.entities.age.Age;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by dobeji1 on 12.04.2019.
 */
@NamedQueries({
        @NamedQuery(name = Land.GET_LAND_BY_NAME_AND_AGE,
                query = "select l from Land l where l.name = :name and l.age = :age"),
        @NamedQuery(name = Land.GET_LAND_BY_PLAYER_AND_AGE,
                query = "select l from Land l where l.player = :player and l.age = :age"),
        @NamedQuery(name = Land.GET_LAST_LAND_IN_AGE_ID,
                query = "select max(l.landInAgeId) from Land l where l.age = :age"),
})
@Table(name = "LAND")
@Entity
public class Land implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_LAND_BY_NAME_AND_AGE = "Land.GET_LAND_BY_NAME_AND_AGE";
    public static final String GET_LAND_BY_PLAYER_AND_AGE = "Land.GET_LAND_BY_PLAYER_AND_AGE";
    public static final String GET_LAST_LAND_IN_AGE_ID = "Land.GET_LAST_LAND_IN_AGE_ID";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAND_IN_AGE_ID")
    private Integer landInAgeId;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "AGE_ID")
    private Age age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Land land = (Land) o;
        return Objects.equals(id, land.id);
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

    public Integer getLandInAgeId() {
        return landInAgeId;
    }

    public void setLandInAgeId(Integer landInAgeId) {
        this.landInAgeId = landInAgeId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }
}
