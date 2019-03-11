package cz.endless.conflict.pojos.models.consequences;

import cz.endless.conflict.enums.CountryAttribute;

/**
 * Created by snajfi1 on 09.08.2018.
 */
public class AttackConsequencesToLand {

    private CountryAttribute countryAttribute;
    private double from;
    private double to;
    private double effect;

    public AttackConsequencesToLand(CountryAttribute countryAttribute, double from, double to, double effect) {
        this.countryAttribute = countryAttribute;
        this.from = from;
        this.to = to;
        this.effect = effect;
    }

    public CountryAttribute getCountryAttribute() {
        return countryAttribute;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getEffect() {
        return effect;
    }
}
