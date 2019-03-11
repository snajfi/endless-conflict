package cz.endless.conflict.enums.data.spells;

/**
 * Created by snajfi1 on 20.08.2018.
 */
public enum Spell { // should be targeted on land and should have some once effect or time for which will remain, if nobody dis-spell it.
    // weakening spells - spells, which decrease target defense
    FOG(10,SpellCategory.WEAKENING), // decease bonus of watch towers in target land for some time.


    // Destructive spells
    FIREBALLS(15,SpellCategory.DESTRUCTIVE), // Destroy small amount of buildings


    // Self casted spells / mainly
    MAGIC_SHIELD(15,SpellCategory.SUPPORTING); // increase magic rezistance in land for some time.

    private String descriptionKey;
    private int baseManaCost;
    //private List<SpellEffect> spellEffects;
    private SpellCategory spellCategory;

    Spell(int baseManaCost, SpellCategory spellCategory) {
        this.baseManaCost = baseManaCost;
        this.spellCategory = spellCategory;
    }
}
