package cz.endless.conflict.beans;

import cz.endless.conflict.entities.Age;
import cz.endless.conflict.entities.AgeConfiguration;
import cz.endless.conflict.services.AgeService;

import javax.annotation.PostConstruct;
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
    private List<Age> ages;
    private List<AgeConfiguration> ageConfigurations;
    private Age newAge;

    @PostConstruct
    public void init() {
        ages = ageService.getAllAges();
        ageConfigurations = ageService.getAllAgesConfigurations();
        newAge = new Age();
    }

    public List<Age> getAges() {
        return ages;
    }

    public String saveNewAge() {
        ageService.createNewAge(newAge);
        return "ageAdministration";
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
}
