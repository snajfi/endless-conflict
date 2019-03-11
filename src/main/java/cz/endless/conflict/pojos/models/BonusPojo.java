package cz.endless.conflict.pojos.models;

import cz.endless.conflict.enums.BonusType;

/**
 * Created by snajfi1 on 05.03.2018.
 */
public class BonusPojo {

    private BonusType bonusType;
    private double value;

    public BonusPojo(BonusType bonusType, double value) {
        this.bonusType = bonusType;
        this.value = value;
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public double getValue() {
        return value;
    }
}
