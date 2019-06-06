package model.figure;

import javafx.scene.image.Image;
import model.items.Equipment;
import view.ESprites;

/**
 * Contains all the different types of enemies.
 *
 * @author Markus Mogck
 */
public enum EnemyEnum
{

    ENEMY1(100, 10, 2, null, ESprites.ENEMY.getImage()),
    ENEMY2(100, 15, 4, null, ESprites.ENEMY.getImage()),
    ENEMY3(100, 20, 6, null, ESprites.ENEMY.getImage()),
    ENEMY4(100, 25, 8, null, ESprites.ENEMY.getImage()),
    ENEMY5(100, 50, 10, null, ESprites.ENEMY.getImage());

    private EnemyEnum(int healthPoints,
                      int attackPower,
                      int armorClass,
                      Equipment equipment,
                      Image sprite)
    {
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.armorClass = armorClass;

        this.equipment = equipment;
        this.sprite = sprite;
    }

    private final int healthPoints;
    private final int attackPower;
    private final int armorClass;

    private final Equipment equipment;

    private final Image sprite;

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

    public Image getSprite()
    {
        return sprite;
    }
}
