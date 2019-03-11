package cz.endless.conflict.enums.data;

import cz.endless.conflict.helper.EnumTranslator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by snajfi1 on 01.06.2018.
 */
public enum Resource {
    GOLD("Gold",false,5,0),
    FOOD("Food",true,5,10),
    WOOD("Wood",true,5,10),
    MANA("Mana",false,3,10),
    RESEARCH_POINT("Research point",true,1,10),
    POINT_OF_CONSTRUCTION("Point of construction",true,1,10);


    private String name;
    private boolean salable;
    private double defaultProduction;
    private Integer minimalPriceOnMarket;

    Resource(String name, boolean salable, double defaultProduction, Integer minimalPriceOnMarket) {
        this.name = name;
        this.salable = salable;
        this.defaultProduction = defaultProduction;
        this.minimalPriceOnMarket = minimalPriceOnMarket;
    }

    public static List<Resource> salableResources() {
        return Arrays.stream(values())
                .filter(Resource::isSalable)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public boolean isSalable() {
        return salable;
    }

    public double getDefaultProduction() {
        return defaultProduction;
    }

    public Integer getMinimalPriceOnMarket() {
        return minimalPriceOnMarket;
    }

    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }
}
