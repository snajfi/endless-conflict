package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.services.LandService;

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

    private String newLandName;

    public String createLand(Age age) {
        Land land = new Land();
        land.setName(newLandName);
        land.setPlayer(loggedPlayerBean.getLoggedPlayer());
        land.setAge(age);
        landService.createLand(land);
        return "game_main";
    }

    public String getNewLandName() {
        return newLandName;
    }

    public void setNewLandName(String newLandName) {
        this.newLandName = newLandName;
    }
}
