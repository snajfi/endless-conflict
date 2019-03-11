package cz.endless.conflict.pojos.models;

/**
 * Created by dobeji1 on 08.08.2018.
 */

public class AgentLossPojo {

    private double percentageAmount;
    private boolean success;

    public AgentLossPojo(double percentageAmount, boolean success) {
        this.percentageAmount = percentageAmount;
        this.success = success;
    }

    public double getPercentageAmount() {
        return percentageAmount;
    }

    public boolean isSuccess() {
        return success;
    }
}
