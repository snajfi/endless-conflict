package cz.endless.conflict.enums.data;

import cz.endless.conflict.enums.BonusType;
import cz.endless.conflict.pojos.models.BonusPojo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dobeji1 on 16.08.2018.
 */

public enum General {
    COMMANDER, DEFENDER;

    static {
        COMMANDER
                .addBonusLevel(1, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.05)))
                .addBonusLevel(2, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.10)))
                .addBonusLevel(3, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.15)))
                .addBonusLevel(4, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.20)))
                .addBonusLevel(5, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.25)))
                .addBonusLevel(6, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.30)))
                .addBonusLevel(7, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.35)))
                .addBonusLevel(8, Arrays.asList(new BonusPojo(BonusType.ATTACK_STRENGTH, 0.40)));
        DEFENDER
                .addBonusLevel(1, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.05)))
                .addBonusLevel(2, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.10)))
                .addBonusLevel(3, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.15)))
                .addBonusLevel(4, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.20)))
                .addBonusLevel(5, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.25)))
                .addBonusLevel(6, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.30)))
                .addBonusLevel(7, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.35)))
                .addBonusLevel(8, Arrays.asList(new BonusPojo(BonusType.DEFENSE_STRENGTH, 0.40)));
    }

    private Map<Integer, List<BonusPojo>> bonusMap = new HashMap<>();

    General() {
    }


    private General addBonusLevel(int level, List<BonusPojo> bonusPojos) {
        this.bonusMap.put(level, bonusPojos);
        return this;
    }


    public Map<Integer, List<BonusPojo>> getBonusMap() {
        return bonusMap;
    }
}
