package cz.endless.conflict.enums.data;

import cz.endless.conflict.enums.BonusType;
import cz.endless.conflict.helper.EnumTranslator;
import cz.endless.conflict.pojos.models.BonusPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by snajfi1 on 04.07.2018.
 */
public enum Race {
    DWARF,ELF,ORC,GNOME,HUMAN,UNDEAD;

    private String description;
    private int houseCapacity = 500;
    private int marketTax = 5;
    private double baseHappiness = 0.85;
    private double tavernEffect = 1.5;
    private List<BonusPojo> nationBonuses = new ArrayList<>();

    static {

        DWARF   .setDescription("")
                .setChangeOfHouseCapacity(-100)
                .setChangeOfMarketTax(5)
                .setChangeOfBaseHappinness(0)
                .setChangeOfTavernEffect(0.2)
                .addNationBonus(BonusType.ATTACK_STRENGTH,0.1)
                .addNationBonus(BonusType.SWORDSMAN_TRAINING, 0.1);

        ELF     .setDescription("")
                .setChangeOfHouseCapacity(0)
                .setChangeOfMarketTax(3)
                .setChangeOfBaseHappinness(0)
                .setChangeOfTavernEffect(0);

        ORC     .setDescription("")
                .setChangeOfHouseCapacity(0)
                .setChangeOfMarketTax(3)
                .setChangeOfBaseHappinness(0)
                .setChangeOfTavernEffect(0);

        GNOME   .setDescription("")
                .setChangeOfHouseCapacity(-30)
                .setChangeOfMarketTax(3)
                .setChangeOfBaseHappinness(0)
                .setChangeOfTavernEffect(0);

        HUMAN   .setDescription("")
                .setChangeOfHouseCapacity(50)
                .setChangeOfMarketTax(0)
                .setChangeOfBaseHappinness(0)
                .setChangeOfTavernEffect(0);

        UNDEAD  .setDescription("")
                .setChangeOfHouseCapacity(0)
                .setChangeOfMarketTax(1)
                .setChangeOfBaseHappinness(0)
                .setChangeOfTavernEffect(0);

    }


    public List<BonusPojo> getNationBonusesOfGivenType(BonusType bonusType) {
        return nationBonuses.stream()
                .filter(bonusPojo -> bonusPojo.getBonusType().equals(bonusType))
                .collect(Collectors.toList());
    }

    public String getDescription() {
        return description;
    }

    public int getHouseCapacity() {
        return houseCapacity;
    }

    public int getMarketTax() {
        return marketTax;
    }

    public double getBaseHappiness() {
        return baseHappiness;
    }

    public double getTavernEffect() {
        return tavernEffect;
    }

    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }

    public List<BonusPojo> getNationBonuses() {
        return nationBonuses;
    }

    private Race setDescription(String description) {
        this.description=description;
        return this;
    }

    /**
     * Value will be summed with default value.
     * @param houseCapacity
     * @return
     */
    private Race setChangeOfHouseCapacity(int houseCapacity) {
        this.houseCapacity= this.houseCapacity+houseCapacity;
        return this;
    }

    /**
     * Value will be summed with default value.
     * @param marketTax
     * @return
     */
    private Race setChangeOfMarketTax(int marketTax) {
        this.marketTax= this.marketTax+marketTax;
        return this;
    }

    /**
     * Value will be summed with default value.
     * @param happinness
     * @return
     */
    private Race setChangeOfBaseHappinness(double happinness) {
        this.baseHappiness= this.baseHappiness+happinness;
        return this;
    }

    /**
     * Value will be summed with default value.
     * @param tavernEffect
     * @return
     */
    private Race setChangeOfTavernEffect(double tavernEffect) {
        this.tavernEffect= this.tavernEffect+tavernEffect;
        return this;
    }

    private Race addNationBonus(BonusType bonusType, double value) {
        this.nationBonuses.add(new BonusPojo(bonusType,value));
        return this;
    }

}
