package cz.endless.conflict.enums.data;

import cz.endless.conflict.enums.BonusType;
import cz.endless.conflict.enums.CountryAttribute;
import cz.endless.conflict.helper.EnumTranslator;
import cz.endless.conflict.pojos.models.consequences.AttackConsequencesToLand;
import cz.endless.conflict.pojos.models.consequences.AttackConsequencesToUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AttackType {
    NORMAL_ATTACK,NIGHT_AMBUSH,SHOOTING,HUNTING_PATROL,TRAPPING,DRAGONS_RAID,DRAGONS_DESTRUCTION;

    static {
        NORMAL_ATTACK.unitsWhichCanAttack(new Unit[]{Unit.WARRIOR, Unit.ARCHER, Unit.CAVALRY})
                     .unitsWhichCanDefend(Unit.values())

                     .addBonusTypesAffectsAttacker(Arrays.asList(
                             BonusType.ATTACK_STRENGTH,
                             BonusType.ARMY_STRENGTH
                     ))

                     .addBonusTypesAffectsDefender(Arrays.asList(
                             BonusType.DEFENSE_STRENGTH,
                             BonusType.ARMY_STRENGTH
                     ));



        NIGHT_AMBUSH.unitsWhichCanAttack(Unit.WARRIOR)
                    .unitsWhichCanDefend(Unit.WARRIOR)

                    .addAttackerArmyConsequence(Unit.WARRIOR,0,0.40)
                    .addDefenderArmyConsequence(Unit.WARRIOR, 0, 0.20)
                    .addDefenderArmyConsequence(Unit.ARCHER, 0, 0.20)
                    .addDefendersLandConsequence(CountryAttribute.COMBAT_ABILITY,1,0.93,-0.06)
                    .addDefendersLandConsequence(CountryAttribute.COMBAT_ABILITY,0.92,0.87,-0.04)
                    .addDefendersLandConsequence(CountryAttribute.COMBAT_ABILITY,0.87,0.80,-0.02)

                    .addBonusTypesAffectsAttacker(Arrays.asList(
                            BonusType.TACTIC_STRENGTH,
                            BonusType.TACTIC_ATTACK,
                            BonusType.NIGHT_AMBUSH_ATTACK,
                            BonusType.ARMY_STRENGTH
                    ))

                    .addBonusTypesAffectsDefender(Arrays.asList(
                            BonusType.DEFENSE_STRENGTH,
                            BonusType.ARMY_STRENGTH
                    ));

        SHOOTING.unitsWhichCanAttack(Unit.ARCHER)
                .unitsWhichCanDefend(Unit.ARCHER)
                .addAttackerArmyConsequence(Unit.ARCHER,0,0.40)
                .addDefenderArmyConsequence(Unit.ARCHER, 0, 0.08)
                .addDefenderArmyConsequence(Unit.WARRIOR, 0, 0.08)
                .addDefenderArmyConsequence(Unit.CAVALRY, 0, 0.08)

                .addBonusTypesAffectsAttacker(Arrays.asList(
                        BonusType.TACTIC_STRENGTH,
                        BonusType.TACTIC_ATTACK,
                        BonusType.SHOOTING_ATTACK,
                        BonusType.ARMY_STRENGTH
                ))

                .addBonusTypesAffectsDefender(Arrays.asList(
                        BonusType.ARMY_STRENGTH,
                        BonusType.TACTIC_STRENGTH,
                        BonusType.TACTIC_DEFENSE,
                        BonusType.SHOOTING_ATTACK
                ));

        HUNTING_PATROL.unitsWhichCanAttack(Unit.CAVALRY)
                    .unitsWhichCanDefend(Unit.CAVALRY)
                    .addAttackerArmyConsequence(Unit.CAVALRY,0,0.40)
                    .addDefenderArmyConsequence(Unit.WARRIOR, 0, 0.04)
                    .addDefenderArmyConsequence(Unit.ARCHER, 0, 0.04)
                    .addDefenderArmyConsequence(Unit.CAVALRY, 0, 0.04)

                    .addBonusTypesAffectsAttacker(Arrays.asList(
                            BonusType.TACTIC_STRENGTH,
                            BonusType.TACTIC_ATTACK,
                            BonusType.HUNTING_ATTACK,
                            BonusType.ARMY_STRENGTH
                    ))

                    .addBonusTypesAffectsDefender(Arrays.asList(
                            BonusType.ARMY_STRENGTH,
                            BonusType.TACTIC_STRENGTH,
                            BonusType.TACTIC_DEFENSE,
                            BonusType.HUNTING_DEFENSE
                    ));

        TRAPPING.unitsWhichCanAttack(new Unit[] {Unit.WARRIOR,Unit.ARCHER})
                .unitsWhichCanDefend(Unit.CAVALRY)
                .addAttackerArmyConsequence(Unit.WARRIOR,0,0.15)
                .addAttackerArmyConsequence(Unit.ARCHER,0,0.15)
                .addDefenderArmyConsequence(Unit.CAVALRY, 0, 0.25)

                .addBonusTypesAffectsAttacker(Arrays.asList(
                        BonusType.TACTIC_STRENGTH,
                        BonusType.TACTIC_ATTACK,
                        BonusType.TRAPPING_ATTACK,
                        BonusType.ARMY_STRENGTH
                ))

                .addBonusTypesAffectsDefender(Arrays.asList(
                        BonusType.ARMY_STRENGTH,
                        BonusType.TACTIC_STRENGTH,
                        BonusType.TACTIC_DEFENSE,
                        BonusType.TRAPPING_DEFENSE
                ));

    }

    private List<Unit> attackingUnits;
    private List<Unit> defendingUnits;
    private List<AttackConsequencesToUnit> attackerArmyConsequences = new ArrayList<>();
    private List<AttackConsequencesToUnit> defenderArmyConsequences = new ArrayList<>();
    private List<AttackConsequencesToLand> defenderLandConsequences = new ArrayList<>();
    private List<BonusType> bonusesAffectingAttacker = new ArrayList<>();
    private List<BonusType> bonusesAffectingDefender = new ArrayList<>();

    AttackType() {
    }

    public boolean canUnitAttack(Unit unit) {
        return attackingUnits.contains(unit);
    }

    public boolean canUnitDefend(Unit unit) {
        return defendingUnits.contains(unit);
    }

    public static List<AttackType> inWhichUnitCanAttack(Unit unit) {
        return Arrays.stream(values())
                .filter(attackType -> attackType.attackingUnits.contains(unit))
                .collect(Collectors.toList());
    }

    public static List<AttackType> inWhichUnitCanDefend(Unit unit) {
        return Arrays.stream(values())
                .filter(attackType -> attackType.defendingUnits.contains(unit))
                .collect(Collectors.toList());
    }

    public List<Unit> getAttackingUnits() {
        return attackingUnits;
    }

    public List<Unit> getDefendingUnits() {
        return defendingUnits;
    }

    public List<AttackConsequencesToUnit> getAttackerArmyConsequences() {
        return attackerArmyConsequences;
    }

    public List<AttackConsequencesToUnit> getDefenderArmyConsequences() {
        return defenderArmyConsequences;
    }

    public List<AttackConsequencesToLand> getDefenderLandConsequences() {
        return defenderLandConsequences;
    }

    private AttackType unitsWhichCanAttack(Unit[] units){
        this.attackingUnits = Arrays.asList(units);
        return this;
    }

    private AttackType unitsWhichCanDefend(Unit[] units){
        this.defendingUnits = Arrays.asList(units);
        return this;
    }

    private AttackType unitsWhichCanAttack(Unit unit){
        this.attackingUnits = new ArrayList<>();
        this.attackingUnits.add(unit);
        return this;
    }

    private AttackType unitsWhichCanDefend(Unit unit){
        this.defendingUnits = new ArrayList<>();
        this.defendingUnits.add(unit);
        return this;
    }

    private AttackType addAttackerArmyConsequence(Unit unit, double from, double to) {
        this.attackerArmyConsequences.add(new AttackConsequencesToUnit(unit,from,to));
        return this;
    }

    private AttackType addDefenderArmyConsequence(Unit unit, double from, double to) {
        this.defenderArmyConsequences.add(new AttackConsequencesToUnit(unit,from,to));
        return this;
    }

    private AttackType addDefendersLandConsequence(CountryAttribute countryAttribute, double from, double to, double effect) {
        this.defenderLandConsequences.add(new AttackConsequencesToLand(countryAttribute,from,to,effect));
        return this;
    }

    private AttackType addBonusTypesAffectsAttacker(BonusType bonusType){
        this.bonusesAffectingAttacker.add(bonusType);
        return this;
    }

    private AttackType addBonusTypesAffectsAttacker(List<BonusType> bonusType){
        this.bonusesAffectingAttacker.addAll(bonusType);
        return this;
    }

    private AttackType addBonusTypesAffectsDefender(BonusType bonusType){
        this.bonusesAffectingDefender.add(bonusType);
        return this;
    }

    private AttackType addBonusTypesAffectsDefender(List<BonusType> bonusType){
        this.bonusesAffectingDefender.addAll(bonusType);
        return this;
    }


    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }

}
