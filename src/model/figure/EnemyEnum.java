package model.figure;

import model.items.Equipment;

/**
 * Contains all the different types of enemies.
 *
 * @author Markus Mogck
 */
public enum EnemyEnum
{
    ENEMY1(100, 10, 2, null),
    ENEMY2(100, 15, 4, null),
    ENEMY3(100, 20, 6, null),
    ENEMY4(100, 25, 8, null),
    ENEMY5(100, 50, 10, null);

    private EnemyEnum(int healthPoints,
                      int attackPower,
                      int armorClass,
                      Equipment equipment)
    {
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.armorClass = armorClass;

        this.equipment = equipment;
    }

    private int healthPoints;
    private int attackPower;
    private int armorClass;

    private Equipment equipment;

    public String getName()
    {
        return this.name();
    }
    
    public int getHealthPoints()
    {
        return healthPoints;
    }

    public int getAttackPower()
    {
        return attackPower;
    }

    public int getArmorClass()
    {
        return armorClass;
    }

    public Equipment getEquipment()
    {
        return equipment;
    }
}
