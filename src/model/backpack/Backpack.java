package model.backpack;

import model.items.LootDrop;
import resources.gameconstants.CommonConstants;

/**
 * The Backpack with will be used to safe the dropped Materials the Heros 
 * collect.
 * 
 * The concept of the backpack is to safe stacks of the same material in one 
 * Backpack Slot. So in an Array of LootDrops that is the backpack we will safe
 * an Array of the specific LootDrop (for example Leather), the size of those 
 * Stacks depends on the rarity of the Material.
 * 
 * This will be finished at a later date.
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
