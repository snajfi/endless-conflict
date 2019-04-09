package cz.endless.conflict.entities;

import cz.endless.conflict.entities.age.Age;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by dobeji1 on 15.03.2019.
 */
@NamedQueries({
        @NamedQuery(name = Player.GET_ALL_PLAYERS,
                query = "select p from Player p "),
        @NamedQuery(name = Player.GET_PLAYER_BY_NICKNAME,
                query = "select p from Player p where p.nickname = :nickname"),
        @NamedQuery(name = Player.GET_PLAYER_BY_LOGIN,
                query = "select p from Player p where p.login = :login"),
        @NamedQuery(name = Player.GET_PLAYER_BY_EMAIL,
                query = "select p from Player p where p.email = :email")
})
@Table(name = "PLAYER")
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_ALL_PLAYERS = "Player.GET_ALL_PLAYERS";
    public static final String GET_PLAYER_BY_NICKNAME = "Player.GET_PLAYER_BY_NICKNAME";
    public static final String GET_PLAYER_BY_LOGIN = "Player.GET_PLAYER_BY_LOGIN";
    public static final String GET_PLAYER_BY_EMAIL = "Player.GET_PLAYER_BY_EMAIL";


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NICKNAME", nullable = false, unique = true)
    private String nickname;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
