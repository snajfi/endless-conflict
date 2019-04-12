package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.entities.age.AgeConfiguration;
import cz.endless.conflict.entities.age.WinCondition;
import cz.endless.conflict.entities.age.WinConditions;
import cz.endless.conflict.services.AgeService;
import cz.endless.conflict.services.UtilsService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dobeji1 on 15.03.2019.
 */

@Named
@ViewScoped
public class AgeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private AgeService ageService;
    @Inject private UtilsService utilsService;
    @Inject private LoggedPlayerBean loggedPlayerBean;
    @Inject private ImportBean importBean;
    private List<Age> ages;
    private List<AgeConfiguration> ageConfigurations;
    private Age newAge;
    private AgeConfiguration ageConfiguration;
    private AgeConfiguration nullAgeConfiguration;

    @PostConstruct
    public void init() {
        if (!importBean.isDefaultDataImported()) {
            importBean.importData();
        }
        ages = ageService.getAllAges();
        ageConfigurations = ageService.getAllAgesConfigurations();
        newAge = new Age();
        newAge.setNumber(ageService.getNextAgeNumber());
        WinConditions winConditions = new WinConditions();
        winConditions.setWinCondition(new ArrayList<>());
        newAge.setWinConditions(winConditions);
        ageConfiguration = new AgeConfiguration();
        nullAgeConfiguration = new AgeConfiguration();
    }

    public List<Age> getAges() {
        return ages;
    }

    public String saveNewAge() {
        for (WinCondition winCondition : newAge.getWinConditions().getWinCondition()) {
            if (winCondition.getWinConditionType() == null || winCondition.getWinConditionType().equals("")) {
                utilsService.addLocalizedMessage("errorWinConditionMustBeFilled", FacesMessage.SEVERITY_ERROR);
                return "";
            }
        }
        if (ageService.getAgeByNumber(newAge.getNumber()) == null) {
            newAge.setAgeConfiguration(ageConfiguration);
            ageService.createNewAge(newAge);
            return "ageAdministration";
        } else {
            utilsService.addLocalizedMessage("errorNumberMustBeUnique", FacesMessage.SEVERITY_ERROR, String.valueOf(newAge.getNumber()));
            return "";
        }
    }

    public void addNewWinCondition() {
        if (newAge.getWinConditions().getWinCondition() == null) {
            newAge.getWinConditions().setWinCondition(new ArrayList<>());
        }
        newAge.getWinConditions().getWinCondition().add(new WinCondition());
    }

    public void deleteCondition(int index) {
        newAge.getWinConditions().getWinCondition().remove(index);
    }

    public void configurationChange (AjaxBehaviorEvent e){
        AgeConfiguration ageConfiguration = (AgeConfiguration) ((UIOutput)e.getSource()).getValue();
        if (ageConfiguration.getId() == null) {
            this.ageConfiguration = nullAgeConfiguration;
        }
    }

    public boolean haveLoggedPlayerLandInAge(Age age) {
        Player loggedPlayer = loggedPlayerBean.getLoggedPlayer();
        if (loggedPlayer == null) {
            return false;
        }
        for (Land land : loggedPlayer.getLands()) {
            if (land.getAge().equals(age)) {
                return true;
            }
        }
        return false;
    }

    public Date today() {
        return new Date();
    }

    public void setAges(List<Age> ages) {
        this.ages = ages;
    }

    public List<AgeConfiguration> getAgeConfigurations() {
        return ageConfigurations;
    }

    public void setAgeConfigurations(List<AgeConfiguration> ageConfigurations) {
        this.ageConfigurations = ageConfigurations;
    }

    public Age getNewAge() {
        return newAge;
    }

    public void setNewAge(Age newAge) {
        this.newAge = newAge;
    }

    public AgeConfiguration getAgeConfiguration() {
        return ageConfiguration;
    }

    public void setAgeConfiguration(AgeConfiguration ageConfiguration) {
        this.ageConfiguration = ageConfiguration;
    }

    public AgeConfiguration getNullAgeConfiguration() {
        return nullAgeConfiguration;
    }

    public void setNullAgeConfiguration(AgeConfiguration nullAgeConfiguration) {
        this.nullAgeConfiguration = nullAgeConfiguration;
    }
}
