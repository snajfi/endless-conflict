package cz.endless.conflict.beans;


import cz.endless.conflict.entities.Player;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Kariton on 18.3.2019.
 */
@SessionScoped
@Named
public class LoggedPlayerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player loggedPlayer = null;

    public boolean isPlayerLogged() {
        return loggedPlayer != null;
    }

    public Player getLoggedPlayer() {
        return loggedPlayer;
    }

    public void setLoggedPlayer(Player loggedPlayer) {
        this.loggedPlayer = loggedPlayer;
    }

    public String lougout() {
        setLoggedPlayer(null);
        return "public_main";
    }
}
