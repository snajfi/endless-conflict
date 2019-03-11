package cz.endless.conflict.pojos.models.consequences;

import cz.endless.conflict.enums.data.Unit;

/**
 * Created by snajfi1 on 09.08.2018.
 */
public class AttackConsequencesToUnit {

    private Unit affectedUnit;
    private double loosesFrom;
    private double loosesTo;

    public AttackConsequencesToUnit(Unit affectedUnit, double loosesFrom, double loosesTo) {
        this.affectedUnit = affectedUnit;
        this.loosesFrom = loosesFrom;
        this.loosesTo = loosesTo;
    }

    public Unit getAffectedUnit() {
        return affectedUnit;
    }

    public double getLoosesFrom() {
        return loosesFrom;
    }

    public double getLoosesTo() {
        return loosesTo;
    }
}
