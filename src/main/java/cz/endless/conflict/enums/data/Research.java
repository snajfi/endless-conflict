package cz.endless.conflict.enums.data;

import cz.endless.conflict.enums.BonusType;
import cz.endless.conflict.helper.EnumTranslator;
import cz.endless.conflict.pojos.models.BonusPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by snajfi1 on 12.07.2018.
 */
public enum Research {

    // Independent tree
    WRITING,
    MEDICINE,
    TRADE,
    DRAWING,
    SPECIAL_BUILDINGS,


    // Military tree
    MILITARY_TRADITION,
    MILITIA,
    FACE_PAINTING,
    WEAPON_PRODUCTION,
    KNIGHT_TOURNAMENTS,
    ANIMAL_HUNTING,

    // Economic tree
    ECONOMICS_PROSPERITY,
    TOOLS_PRODUCTION,
    FORESTRY,
    CORVEE,
    CULTURE;


    static {
        WRITING.cost(15);
        MEDICINE.cost(20)
                .dependsOn(WRITING)
                .bringBonus(BonusType.UNIT_LOSES,-0.1);
        MILITARY_TRADITION.cost(20)
                .blockedBy(ECONOMICS_PROSPERITY);
        ECONOMICS_PROSPERITY.cost(20)
                .blockedBy(MILITARY_TRADITION);
        TRADE.cost(100)
                .dependsOn(WRITING);
        DRAWING.cost(120)
                .dependsOn(WRITING)
                .bringBonus(BonusType.POINTS_OF_CONSTRUCTION_PRODUCTION, 0.1);
        SPECIAL_BUILDINGS.cost(80)
                .dependsOn(DRAWING);
        FACE_PAINTING.cost(15)
                .dependsOn(WRITING)
                .dependsOn(MILITARY_TRADITION)
                .forNations(Arrays.asList(Race.DWARF))
                .bringBonus(BonusType.ARMY_STRENGTH, 0.05);
        MILITIA.cost(90)
                .dependsOn(MILITARY_TRADITION)
                .bringBonus(BonusType.DEFENSE_STRENGTH, 0.05)
                .bringBonus(BonusType.RESOURCE_PRODUCTION, -0.05);
        WEAPON_PRODUCTION.cost(90)
                .dependsOn(MILITARY_TRADITION).
                bringBonus(BonusType.UNIT_PRODUCTION, 0.05)
                .bringBonus(BonusType.RESOURCE_PRODUCTION, -0.05);
        KNIGHT_TOURNAMENTS.cost(90)
                .dependsOn(MILITARY_TRADITION)
                .blockedBy(ANIMAL_HUNTING)
                .bringBonus(BonusType.HORSEMAN_TRAINING, 0.1);
        ANIMAL_HUNTING.cost(90)
                .dependsOn(MILITARY_TRADITION)
                .blockedBy(KNIGHT_TOURNAMENTS)
                .bringBonus(BonusType.ARCHERS_TRAINING, 0.1)
                .bringBonus(BonusType.FOOD_PRODUCTION, 0.05);

        TOOLS_PRODUCTION.cost(90)
                .dependsOn(ECONOMICS_PROSPERITY)
                .bringBonus(BonusType.RESOURCE_PRODUCTION, 0.05)
                .bringBonus(BonusType.UNIT_PRODUCTION, -0.05);
        FORESTRY.cost(90)
                .dependsOn(TOOLS_PRODUCTION)
                .blockedBy(CORVEE)
                .bringBonus(BonusType.WOOD_PRODUCTION, 0.1);
        CORVEE.cost(90)
                .dependsOn(ECONOMICS_PROSPERITY)
                .blockedBy(FORESTRY)
                .blockedBy(CULTURE)
                .bringBonus(BonusType.FOOD_PRODUCTION, 0.1)
                .bringBonus(BonusType.HAPPINESS, -0.05);
        CULTURE.cost(90)
                .dependsOn(ECONOMICS_PROSPERITY)
                .dependsOn(WRITING)
                .blockedBy(CORVEE)
                .bringBonus(BonusType.HAPPINESS, 0.1);
    }

    private int costInResearchPoint;
    private List<Research> dependsOn = new ArrayList<>();
    private List<Research> blockedBy = new ArrayList<>();
    private List<Race> forRaces = new ArrayList<>(Arrays.asList(Race.values()));
    private List<BonusPojo> bonuses = new ArrayList<>();

    Research() {
    }

    public static List<Research> getAllResearchesWithBonusType(BonusType bonusType) {
        List<Research> researchList = new ArrayList<>();
        for (Research research : values()) {
            for (BonusPojo bonusPojo : research.getBonuses()) {
                if (bonusPojo.getBonusType().equals(bonusType)) {
                    researchList.add(research);
                }
            }
        }
        return researchList;
    }

    public List<Race> getForRaces() {
        return forRaces;
    }

    private Research forNations(List<Race> races) {
        this.forRaces = races;
        return this;
    }

    public List<BonusPojo> getBonuses() {
        return bonuses;
    }

    private Research bringBonus(List<BonusPojo> bonuses) {
        this.bonuses = bonuses;
        return this;
    }

    private Research bringBonus(BonusType bonusType, double value) {
        this.bonuses.add(new BonusPojo(bonusType,value));
        return this;
    }

    private Research dependsOn(List<Research> research) {
        this.dependsOn = research;
        return this;
    }

    private Research dependsOn(Research research) {
        this.dependsOn.add(research);
        return this;
    }

    public List<Research> getDependsOn() {
        return dependsOn;
    }

    private Research blockedBy(List<Research> research) {
        this.blockedBy = research;
        return this;
    }

    private Research blockedBy(Research research) {
        this.blockedBy.add(research);
        return this;
    }

    public List<Research> getBlockedBy() {
        return blockedBy;
    }

    private Research cost(int cost) {
        this.costInResearchPoint = cost;
        return this;
    }

    public int getCostInResearchPoint() {
        return costInResearchPoint;
    }

    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }

}
