/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.backpack;

import model.items.LootDrop;
import resources.gameconstants.CommonConstants;

/**
 *
 * @author Jonas Ulrich
 */
public class Backpack
{
    private LootDrop[] backpack;

    
    private int NumberOfItemsInBackpack;
    private final int size;
    
    public Backpack(int size)
    {
        this.backpack = new LootDrop[size];
        this.size = size;
        this.NumberOfItemsInBackpack = CommonConstants.ZERO;
    }
    
     public int getNumberOfItemsInBackpack()
    {
        return NumberOfItemsInBackpack;
    }

    public void setNumberOfItemsInBackpack(int NumberOfItemsInBackpack)
    {
        this.NumberOfItemsInBackpack = NumberOfItemsInBackpack;
    }

    public int getSize()
    {
        return size;
    }
    
    public LootDrop getItem(int BackpackSlot)
    {
        return this.backpack[BackpackSlot];
    }
    
    public LootDrop[] getBackpack()
    {
        return backpack;
    }

    public void setBackpack(int i, LootDrop lootDrop)
    {
        this.backpack[i] = lootDrop;
    }
    
}
