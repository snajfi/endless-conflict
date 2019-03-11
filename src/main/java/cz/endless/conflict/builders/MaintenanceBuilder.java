package cz.endless.conflict.builders;

import cz.endless.conflict.enums.data.Resource;
import cz.endless.conflict.pojos.models.Maintenance;

import java.util.HashMap;

/**
 * Created by snajfi1 on 27.06.2018.
 */
public class MaintenanceBuilder {

    private HashMap<Resource, Double> costs = new HashMap<>();

    public MaintenanceBuilder() {
    }

    public MaintenanceBuilder(Resource resource, Double value) {
        add(resource,value);
    }

    public MaintenanceBuilder add(Resource resource, Double value) {
        if (costs.containsKey(resource)) {
            costs.put(resource,costs.get(resource)+value);
        } else {
            costs.put(resource,value);
        }
        return this;
    }

    public Maintenance build() {
        if (costs==null) {
            costs = new HashMap<>();
        }
        return new Maintenance(costs);
    }



}
