package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.services.PlayerService;
import cz.endless.conflict.services.UtilsService;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by dobeji1 on 10.04.2019.
 */

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private PlayerService playerService;
    @Inject private UtilsService utilsService;
    @Inject private LoggedPlayerBean loggedPlayerBean;
    @Inject private ImportBean importBean;

    private String login = "";
    private String password = "";

    public String authenticatePlayer() {
        if (!importBean.isDefaultDataImported()) {
            importBean.importData();
        }
        if (login == null || password == null || !playerService.authenticatePlayer(login, password)) {
            utilsService.addLocalizedMessage("errorLoginWrongCombination", "loginMessages", FacesMessage.SEVERITY_ERROR);
            return "";
        } else {
            Player player = playerService.findPlayerByLogin(login);
            loggedPlayerBean.setLoggedPlayer(player);
            if (player.isAdministrator()) {
                return "administration_main";
            }
            return "";
        }
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
}
