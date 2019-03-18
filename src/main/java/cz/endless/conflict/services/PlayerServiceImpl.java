package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by snajfi on 18.3.2019.
 */
public class PlayerServiceImpl implements PlayerService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Override
    public Player saveNewPlayer(Player player) {
        return entityManager.merge(player);
    }

    @Override
    public Boolean isNicknameAvailable(String nickname) {
        return null;
    }

    @Override
    public Boolean isLoginAvailable(String login) {
        return null;
    }
}
