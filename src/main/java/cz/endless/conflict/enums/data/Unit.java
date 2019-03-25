package cz.endless.conflict.enums.data;

import cz.endless.conflict.builders.MaintenanceBuilder;
import cz.endless.conflict.pojos.models.Maintenance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by snajfi1 on 01.06.2018.
 */
public enum Unit {
    WARRIOR(1,1,2,true,2,"warrior_description",
                new MaintenanceBuilder(Resource.GOLD,0.003).add(Resource.FOOD,0.001).build()),
    ARCHER(2,1,2,true,1.5,"archer_description",
                new MaintenanceBuilder(Resource.GOLD,0.001).add(Resource.FOOD,0.001).add(Resource.WOOD,0.001).build()),
    CAVALRY(3,2,3,true,0.5,"cavalry_description",
                new MaintenanceBuilder(Resource.GOLD,0.05).add(Resource.FOOD,0.005).build()),
    SHOOTER_TOWER(0,4,3,true,0.5,"shooter_tower_description",
                new MaintenanceBuilder(Resource.GOLD,0.05).add(Resource.FOOD,0.005).build());

    private int attack;
    private int defense;
    private double pointPerUnit;
    private boolean tradable;
    private double defaultProduction;
    private String description;
    private Maintenance maintenance;

    Unit(int attack, int defense, double pointPerUnit, boolean tradable, double defaultProduction, String description, Maintenance maintenance) {
        this.attack = attack;
        this.defense = defense;
        this.pointPerUnit = pointPerUnit;
        this.tradable = tradable;
        this.defaultProduction = defaultProduction;
        this.description = description;
        this.maintenance = maintenance;
    }

    /**
     * @return all units, which has tradable set to true
     */
    public static List<Unit> tradableUnits() {
        return Arrays.stream(values())
                .filter(unit -> unit.tradable)
                .collect(Collectors.toList());
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public double getPointPerUnit() {
        return pointPerUnit;
    }

    public boolean isTradable() {
        return tradable;
    }

    public double getDefaultProduction() {
        return defaultProduction;
    }

    public String getDescription() {
        return description;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

}
