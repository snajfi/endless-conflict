package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.services.PlayerService;
import cz.endless.conflict.services.UtilsService;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by snajfi on 18.3.2019.
 */
@Named
@ViewScoped
public class PlayerCreationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject private PlayerService playerService;
    @Inject private LoggedPlayerBean loggedPlayerBean;
    @Inject private UtilsService utilsService;

    private Player newPlayer = new Player();
    private String emailRepeat;


    public void savePlayer() {
        Boolean checkOfUniqueFields = true;

        if (!playerService.isLoginAvailable(newPlayer.getLogin())) {
            checkOfUniqueFields = false;
            utilsService.addLocalizedMessage("errorLoginIsNotAvailable", FacesMessage.SEVERITY_ERROR);

        }

        if (!playerService.isNicknameAvailable(newPlayer.getNickname())) {
            checkOfUniqueFields = false;
            utilsService.addLocalizedMessage("errorNicknameIsNotAvailable", FacesMessage.SEVERITY_ERROR);
        }

        if (!playerService.isEmailAvailable(newPlayer.getEmail())) {
            checkOfUniqueFields = false;
            utilsService.addLocalizedMessage("errorEmailIsUse", FacesMessage.SEVERITY_ERROR);
        }

        if (!isEmailSyntax(newPlayer.getEmail())) {
            checkOfUniqueFields = false;
            utilsService.addLocalizedMessage("errorEmailHasWrongSyntax", FacesMessage.SEVERITY_ERROR);
        }

        if (!emailRepeat.equals(newPlayer.getEmail())) {
            checkOfUniqueFields = false;
            utilsService.addLocalizedMessage("errorEmailIsNotMatch", FacesMessage.SEVERITY_ERROR);
        }

        if (checkOfUniqueFields) {
            // check mandatory fields + check email format

            // login new player
            loggedPlayerBean.setLoggedPlayer(playerService.saveNewPlayer(newPlayer));
            utilsService.addLocalizedMessage("userSuccessfullyRegistered", FacesMessage.SEVERITY_INFO);
        }

    }

    public boolean isEmailSyntax(String email) {
        Pattern pattern = Pattern.compile("^((([!#$%&'*+\\-/=?^_`{|}~\\w])|([!#$%&'*+\\-/=?^_`{|}~\\w][!#$%&'*+\\-/=?^_`{|}~\\.\\w]{0,}[!#$%&'*+\\-/=?^_`{|}~\\w]))[@]\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)$");
        return pattern.matcher(email).matches();
    }


    public Player getNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
    }

    public String getEmailRepeat() {
        return emailRepeat;
    }

    public void setEmailRepeat(String emailRepeat) {
        this.emailRepeat = emailRepeat;
    }
}
