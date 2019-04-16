package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.services.LandService;
import cz.endless.conflict.services.UtilsService;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by dobeji1 on 12.04.2019.
 */

@Named
@ViewScoped
public class LandBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private LandService landService;
    @Inject private LoggedPlayerBean loggedPlayerBean;
    @Inject private UtilsService utilsService;

    private String newLandName;

    public String createLand(Age age) {
        if (newLandName.length() < 3) {
            utilsService.addLocalizedMessage("errorShortNameOfLand", "createLandMessage" + age.getId().toString(), FacesMessage.SEVERITY_ERROR);
            return "";
        }
        if (landService.isLandNameAvailableInAge(newLandName, age)) {
            Land land = new Land();
            land.setName(newLandName);
            land.setPlayer(loggedPlayerBean.getLoggedPlayer());
            loggedPlayerBean.getLoggedPlayer().getLands().add(land);
            land.setAge(age);
            land.setLandInAgeId(landService.getLastLandInAgeId(age));
            landService.createLand(land);
            loggedPlayerBean.setPlayedLand(landService.findLandByPlayerAndAge(loggedPlayerBean.getLoggedPlayer(), age));
            return "game_main";
        } else {
            utilsService.addLocalizedMessage("errorNameOfLandNotAvailable","createLandMessage" + age.getId().toString(), FacesMessage.SEVERITY_ERROR);
            return "";
        }
    }

    public String enterAge(Age age) {
        loggedPlayerBean.setPlayedLand(landService.findLandByPlayerAndAge(loggedPlayerBean.getLoggedPlayer(), age));
        return "game_main";
    }

    public String getNewLandName() {
        return newLandName;
    }

    public void setNewLandName(String newLandName) {
        this.newLandName = newLandName;
    }
}
