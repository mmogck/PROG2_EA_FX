package model.items;

import resources.gameconstants.CommonConstants;
import resources.gameconstants.IItemConstants;

/**
 * Enum that saves the different kinds of materials Enemys can drop upon their
 * death. They are differentiated by rarity and all have an explicit Number to
 * clearly differ between them. At the current point his is just a conzept that
 * will be further refined and added upon.
 *
 * @author Jonas Ulrich
 */
public enum LootDrop
{

    LEATHER(IItemConstants.COMMON_RARITY, CommonConstants.ONE),
    IRON(IItemConstants.COMMON_RARITY, CommonConstants.TWO);

    private int rarity;
    private final int numberAmongRarity;

    private LootDrop(int rarity, int numberAmongRarity)
    {
        this.rarity = rarity;
        this.numberAmongRarity = numberAmongRarity;
    }

    public int getRarity()
    {
        return rarity;
    }

    public void setRarity(int rarity)
    {
        this.rarity = rarity;
    }
}
