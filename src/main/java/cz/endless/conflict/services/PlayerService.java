package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;

import java.util.List;

/**
 * Created by snajfi on 18.3.2019.
 */
public interface PlayerService {

    /**
     * @param player with all required fields
     * @return persisted player
     */
    Player saveNewPlayer(Player player);

    Player getPlayerByNickname(String nickname);

    Player findPlayerByLogin(String login);

    List<Player> getAllPlayers();

    List<Player> getPlayerByHintNickname(String hint);

    Boolean isNicknameAvailable(String nickname);

    Boolean isLoginAvailable(String login);

    Boolean isEmailAvailable(String email);

    boolean authenticatePlayer(String login, String password);

}
