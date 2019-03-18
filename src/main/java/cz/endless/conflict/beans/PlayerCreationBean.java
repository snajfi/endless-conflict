package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.services.PlayerService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by snajfi on 18.3.2019.
 */
@Named
@ViewScoped
public class PlayerCreationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject private PlayerService playerService;
    @Inject private LoggedPlayerBean loggedPlayerBean;

    private Player newPlayer = new Player();


    public void savePlayer() {
        Boolean checkOfUniqueFields = true;

        if (!playerService.isLoginAvailable(newPlayer.getLogin())) {
            // add message about unavailable login name
            checkOfUniqueFields = false;
        }

        if (!playerService.isNicknameAvailable(newPlayer.getNickname())) {
            // add message about unavailable nickname
            checkOfUniqueFields = false;
        }

        if (checkOfUniqueFields) {
            // check mandatory fields + check email format

            // login new player
           loggedPlayerBean.setLoggedPlayer(playerService.saveNewPlayer(newPlayer));
        }

    }


    public Player getNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
    }
}
