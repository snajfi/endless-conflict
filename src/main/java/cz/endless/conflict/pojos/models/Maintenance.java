package cz.endless.conflict.pojos.models;

import cz.endless.conflict.enums.data.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by snajfi1 on 01.06.2018.
 */
public class Maintenance {

    private Map<Resource, Double> costs;

    private Maintenance(HashMap<Resource,Double> costs) {
        this.costs = costs;
    }

    public Map<Resource, Double> getCosts() {
        return costs;
    }

    public Maintenance(Map<Resource, Double> costs) {
        this.costs = costs;
    }
}
