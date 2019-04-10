package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by snajfi on 18.3.2019.
 */
@Stateless
public class PlayerServiceImpl implements PlayerService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Override
    public Player saveNewPlayer(Player player) {
        return entityManager.merge(player);
    }

    @Override
    public Player findPlayerByLogin(String login) {
        TypedQuery<Player> query = entityManager.createNamedQuery(Player.GET_PLAYER_BY_LOGIN,Player.class);
        query.setParameter("login", login);
        List<Player> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Boolean isNicknameAvailable(String nickname) {
        TypedQuery<Player> query = entityManager.createNamedQuery(Player.GET_PLAYER_BY_NICKNAME,Player.class);
        query.setParameter("nickname", nickname);
        List<Player> resultList = query.getResultList();
        return resultList.isEmpty();
    }

    @Override
    public Boolean isLoginAvailable(String login) {
        TypedQuery<Player> query = entityManager.createNamedQuery(Player.GET_PLAYER_BY_LOGIN,Player.class);
        query.setParameter("login", login);
        List<Player> resultList = query.getResultList();
        return resultList.isEmpty();
    }

    @Override
    public Boolean isEmailAvailable(String email) {
        TypedQuery<Player> query = entityManager.createNamedQuery(Player.GET_PLAYER_BY_EMAIL,Player.class);
        query.setParameter("email", email);
        List<Player> resultList = query.getResultList();
        return resultList.isEmpty();
    }

    @Override
    public boolean authenticatePlayer(String login, String password) {
        TypedQuery<Player> query = entityManager.createNamedQuery(Player.GET_PLAYER_BY_LOGIN_AND_PASSWORD, Player.class);
        query.setParameter("login",login);
        query.setParameter("password",password);
        List<Player> players = query.getResultList();
        return !players.isEmpty();
    }
}
