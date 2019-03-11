package cz.endless.conflict.enums.data.spells;

import cz.endless.conflict.enums.data.Building;

import java.util.List;

public enum EffectType {
    BUILDING_DESTRUCTION;


    private List<Building> affectedBuildings;
    private double numberOfAffect;

}
