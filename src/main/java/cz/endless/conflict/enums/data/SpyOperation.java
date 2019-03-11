package cz.endless.conflict.enums.data;

import cz.endless.conflict.enums.CountryAttribute;
import cz.endless.conflict.enums.SpyOperationType;
import cz.endless.conflict.helper.EnumTranslator;
import cz.endless.conflict.pojos.models.AgentLossPojo;
import cz.endless.conflict.pojos.models.consequences.ConsequencePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dobeji1 on 08.08.2018.
 */

public enum SpyOperation {
    SPYING_ARMY(),
    SPYING_BUILDINGS(),
    SPYING_RESOURCES(),

    STEAL_GOLD(),
    STEAL_FOOD(),
    STEAL_WOOD(),

    INQUISITION();

    static {
        SPYING_ARMY.chance(90).spyOperationType(SpyOperationType.INFILTRATION).agentLoss(0.01, false);
        SPYING_BUILDINGS.chance(80).spyOperationType(SpyOperationType.INFILTRATION).agentLoss(0.01, false);
        SPYING_RESOURCES.chance(80).spyOperationType(SpyOperationType.INFILTRATION).agentLoss(0.01, false);

        STEAL_GOLD.chance(85).spyOperationType(SpyOperationType.ROBBERY).makeConsequence(Resource.GOLD, 0.2).agentLoss(0.04, false);
        STEAL_FOOD.chance(85).spyOperationType(SpyOperationType.ROBBERY).makeConsequence(Resource.FOOD, 0.2).agentLoss(0.04, false);
        STEAL_WOOD.chance(85).spyOperationType(SpyOperationType.ROBBERY).makeConsequence(Resource.WOOD, 0.2).agentLoss(0.04, false);

        INQUISITION.chance(55).spyOperationType(SpyOperationType.SABOTAGE)
                .makeConsequence(CountryAttribute.HAPPINESS, 0.1)
                .makeConsequence(CountryAttribute.POPULATION, 0.1)
                .agentLoss(0.04, false)
                .agentLoss(0.02, true);
    }

    private int chance;
    private SpyOperationType spyOperationType;
    private List<ConsequencePojo> consequences = new ArrayList<>();
    private List<AgentLossPojo> agentLosses = new ArrayList<>();

    SpyOperation() {
    }

    public int getChance() {
        return chance;
    }

    private SpyOperation chance(int chance) {
        this.chance = chance;
        return this;
    }

    public SpyOperationType getSpyOperationType() {
        return spyOperationType;
    }

    private SpyOperation spyOperationType(SpyOperationType spyOperationType) {
        this.spyOperationType = spyOperationType;
        return this;
    }

    public List<ConsequencePojo> getConsequences() {
        return consequences;
    }

    private SpyOperation makeConsequence(CountryAttribute countryAttribute, Unit affectedUnit, Resource affectedResource, Double percentageAmount) {
        this.consequences.add(new ConsequencePojo(countryAttribute, affectedUnit, affectedResource, percentageAmount));
        return this;
    }

    private SpyOperation makeConsequence(CountryAttribute countryAttribute, Double percentageAmount) {
        this.consequences.add(new ConsequencePojo(countryAttribute,  percentageAmount));
        return this;
    }

    private SpyOperation makeConsequence( Unit affectedUnit, Double percentageAmount) {
        this.consequences.add(new ConsequencePojo(affectedUnit, percentageAmount));
        return this;
    }

    private SpyOperation makeConsequence(Resource affectedResource, Double percentageAmount) {
        this.consequences.add(new ConsequencePojo(affectedResource, percentageAmount));
        return this;
    }

    public List<AgentLossPojo> getAgentLosses() {
        return agentLosses;
    }

    private SpyOperation agentLoss(Double percentageAmount, boolean success) {
        this.agentLosses.add(new AgentLossPojo(percentageAmount, success));
        return this;
    }

    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }
}
