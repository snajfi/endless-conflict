package cz.endless.conflict.pojos.models.consequences;

import cz.endless.conflict.enums.CountryAttribute;
import cz.endless.conflict.enums.data.Resource;
import cz.endless.conflict.enums.data.Unit;

/**
 * Created by dobeji1 on 08.08.2018.
 */

public class ConsequencePojo {

    private CountryAttribute countryAttribute;
    private Unit affectedUnit;
    private Resource affectedResource;
    private Double percentageAmount;

    public ConsequencePojo() {
    }

    public ConsequencePojo(CountryAttribute countryAttribute, Unit affectedUnit, Resource affectedResource, Double percentageAmount) {
        this.countryAttribute = countryAttribute;
        this.affectedUnit = affectedUnit;
        this.affectedResource = affectedResource;
        this.percentageAmount = percentageAmount;
    }

    public ConsequencePojo(CountryAttribute countryAttribute, Double percentageAmount) {
        this.countryAttribute = countryAttribute;
        this.percentageAmount = percentageAmount;
    }

    public ConsequencePojo(Unit affectedUnit, Double percentageAmount) {
        this.affectedUnit = affectedUnit;
        this.percentageAmount = percentageAmount;
    }

    public ConsequencePojo(Resource affectedResource, Double percentageAmount) {
        this.affectedResource = affectedResource;
        this.percentageAmount = percentageAmount;
    }

    public CountryAttribute getCountryAttribute() {
        return countryAttribute;
    }

    public Unit getAffectedUnit() {
        return affectedUnit;
    }

    public Resource getAffectedResource() {
        return affectedResource;
    }

    public Double getPercentageAmount() {
        return percentageAmount;
    }
}
