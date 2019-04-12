package cz.endless.conflict.entities;

import cz.endless.conflict.entities.age.Age;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by dobeji1 on 12.04.2019.
 */
@Table(name = "LAND")
@Entity
public class Land implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

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
