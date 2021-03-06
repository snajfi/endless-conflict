package cz.endless.conflict.entities;

import cz.endless.conflict.entities.communication.Conversation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by dobeji1 on 15.03.2019.
 */
@NamedQueries({
        @NamedQuery(name = Player.GET_ALL_PLAYERS,
                query = "select p from Player p "),
        @NamedQuery(name = Player.GET_PLAYER_BY_ID,
                query = "select p from Player p where p.id = :id"),
        @NamedQuery(name = Player.GET_PLAYER_BY_NICKNAME,
                query = "select p from Player p where p.nickname = :nickname"),
        @NamedQuery(name = Player.GET_PLAYER_BY_LOGIN,
                query = "select p from Player p where p.login = :login"),
        @NamedQuery(name = Player.GET_PLAYER_BY_LOGIN_AND_PASSWORD,
                query = "select p from Player p where p.login = :login and p.password = :password"),
        @NamedQuery(name = Player.GET_PLAYER_BY_EMAIL,
                query = "select p from Player p where p.email = :email"),
        @NamedQuery(name = Player.GET_PLAYER_BY_HINT,
                query = "SELECT p FROM Player p WHERE LOWER(p.nickname) LIKE :hint"),

})
@Table(name = "PLAYER")
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String GET_ALL_PLAYERS = "Player.GET_ALL_PLAYERS";
    public static final String GET_PLAYER_BY_ID = "Player.GET_PLAYER_BY_ID";
    public static final String GET_PLAYER_BY_NICKNAME = "Player.GET_PLAYER_BY_NICKNAME";
    public static final String GET_PLAYER_BY_LOGIN = "Player.GET_PLAYER_BY_LOGIN";
    public static final String GET_PLAYER_BY_LOGIN_AND_PASSWORD = "Player.GET_PLAYER_BY_LOGIN_AND_PASSWORD";
    public static final String GET_PLAYER_BY_EMAIL = "Player.GET_PLAYER_BY_EMAIL";
    public static final String GET_PLAYER_BY_HINT = "Player.GET_PLAYER_BY_HINT";


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

    @Column
    private boolean administrator;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Land> lands;

    @ManyToMany
    @JoinTable(
            name = "RECIPIENTS_IN_CONVERSATIONS",
            joinColumns = @JoinColumn(name = "RECIPIENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "ID")
    )
    private List<Conversation> conversations;

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

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }
}
