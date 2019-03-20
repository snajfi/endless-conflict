package cz.endless.conflict.enums;

import cz.endless.conflict.helper.EnumTranslator;

public enum WinConditionType {
    PRESTIGE,
    ALI_PRESTIGE,
    AMOUNT_OF_SOLDIERS;


    public String getKey() {
        return EnumTranslator.getMessageKey(this);
    }
}
