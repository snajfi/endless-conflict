package cz.endless.conflict.enums.data;

import cz.endless.conflict.builders.MaintenanceBuilder;
import cz.endless.conflict.helper.EnumTranslator;
import cz.endless.conflict.pojos.models.Maintenance;

import java.util.*;

public enum Building {

    HOUSES("House descr.",5,false,new MaintenanceBuilder(Resource.GOLD,0.05).build(),null, Collections.emptyList()),
    LUMBERMILL("Lumbermill descr.",5,false,new MaintenanceBuilder(Resource.WOOD,0.3).build(),Resource.WOOD, Collections.emptyList()),
    FARM("Farm descr.",5,false,new MaintenanceBuilder(Resource.WOOD,0.15).build(),Resource.FOOD, Collections.emptyList()),
    BARRACK("Barrack descr.",5,false,new MaintenanceBuilder(Resource.WOOD,0.25).build(),null, Arrays.asList(Unit.WARRIOR, Unit.ARCHER)),
    STABLES("Stables descr.",5,false,new MaintenanceBuilder(Resource.WOOD,0.25).build(),null, Arrays.asList(Unit.CAVALRY)),
    LIBRARY("Library descr.",5,false,new MaintenanceBuilder(Resource.WOOD,0.25).build(),null, Collections.emptyList()),
    TAVERN("Tavern descr.",5,false,new MaintenanceBuilder(Resource.GOLD,0.15).build(),null, Collections.emptyList()),
    WATCHTOWER("Watchtower descr.",5,false,new MaintenanceBuilder(Resource.GOLD,0.18).build(),null, Collections.emptyList()),
    BUILDERS_WORKSHOP("Builders workshop descr.",5,false,new MaintenanceBuilder(Resource.WOOD,0.3).build(),null, Collections.emptyList());

    public static final Map<Resource,Integer> defaultPriceForBuilding = new HashMap<>();

    static {
        defaultPriceForBuilding.put(Resource.GOLD,100);
        defaultPriceForBuilding.put(Resource.WOOD,10);
    }

    private String description;
    private int requiredPOC;
    private boolean production;
    private Maintenance maintenance;
    private Resource producedResource;
    private List<Unit> producedUnits;


    Building(String description, int requiredPOC, boolean production, Maintenance maintenance, Resource producedResource, List<Unit> producedUnits) {
        this.description = description;
        this.requiredPOC = requiredPOC;
        this.production = production;
        this.maintenance = maintenance;
        this.producedResource = producedResource;
        this.producedUnits = producedUnits;

    }

    public String getDescription() {
        return description;
    }

    public int getRequiredPOC() {
        return requiredPOC;
    }

    public boolean isProduction() {
        return production;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public Resource getProducedResource() {
        return producedResource;
    }

    public List<Unit> getProducedUnits() {
        return producedUnits;
    }

    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }
}
