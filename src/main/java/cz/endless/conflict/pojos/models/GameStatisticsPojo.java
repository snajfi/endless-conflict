package cz.endless.conflict.pojos.models;

import java.io.Serializable;

/**
 * Created by dobeji1 on 15.02.2018.
 */

public class GameStatisticsPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String landName;
    private String nation;
    private double amount;

    public GameStatisticsPojo(String landName, String nation, double amount) {
        this.landName = landName;
        this.nation = nation;
        this.amount = amount;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
