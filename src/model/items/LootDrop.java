/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.items;

import resources.gameconstants.CommonConstants;
import resources.gameconstants.IItemConstants;

/**
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
