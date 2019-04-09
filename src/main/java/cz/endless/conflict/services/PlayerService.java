package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;

/**
 * Created by snajfi on 18.3.2019.
 */
public interface PlayerService {

    /**
     * @param player with all required fields
     * @return persisted player
     */
    Player saveNewPlayer(Player player);

    Boolean isNicknameAvailable(String nickname);

    Boolean isLoginAvailable(String login);

    Boolean isEmailAvailable(String email);

}
