package cz.endless.conflict.pojos.models;

/**
 * Created by snajfi1 on 06.03.2018.
 */
public class ProductionBonusPojo {

    private String name;

    private double value;

    public ProductionBonusPojo(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
