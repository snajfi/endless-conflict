package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Age;
import cz.endless.conflict.entities.AgeConfiguration;
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
    private List<Age> ages;
    private List<AgeConfiguration> ageConfigurations;
    private Age newAge;
    private AgeConfiguration ageConfiguration;
    private AgeConfiguration nullAgeConfiguration;

    @PostConstruct
    public void init() {
        ages = ageService.getAllAges();
        ageConfigurations = ageService.getAllAgesConfigurations();
        newAge = new Age();
        newAge.setNumber(ageService.getNextAgeNumber());
        ageConfiguration = new AgeConfiguration();
        nullAgeConfiguration = new AgeConfiguration();
    }

    public List<Age> getAges() {
        return ages;
    }

    public String saveNewAge() {
        if (ageService.getAgeByNumber(newAge.getNumber()) == null) {
            newAge.setAgeConfiguration(ageConfiguration);
            ageService.createNewAge(newAge);
            return "ageAdministration";
        } else {
            utilsService.addLocalizedMessage("errorNumberMustBeUnique", FacesMessage.SEVERITY_ERROR, String.valueOf(newAge.getNumber()));
            return "";
        }

    }

    public void configurationChange (AjaxBehaviorEvent e){
        AgeConfiguration ageConfiguration = (AgeConfiguration) ((UIOutput)e.getSource()).getValue();
        if (ageConfiguration.getId() == null) {
            this.ageConfiguration = nullAgeConfiguration;
        }
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
