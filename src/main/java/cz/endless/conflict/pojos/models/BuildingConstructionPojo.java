package cz.endless.conflict.pojos.models;

import cz.endless.conflict.enums.data.Building;

import java.io.Serializable;

/**
 * Created by snajfi1 on 21.12.2017.
 */
public class BuildingConstructionPojo implements Serializable{

    private static final long serialVersionUID = 1L;

    private Building building;
    private int currentValue;
    private int requestedConstruction = 0;

    public BuildingConstructionPojo(Building building, int currentValue) {
        this.building = building;
        this.currentValue = currentValue;
    }


    public int getRequestedConstruction() {
        return requestedConstruction;
    }

    public void setRequestedConstruction(int requestedConstruction) {
        this.requestedConstruction = requestedConstruction;
    }

    public Building getBuilding() {
        return building;
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
