package model.figure;

import control.ingamemanagement.FightController;
import javafx.scene.image.Image;
import model.ingamemanagement.Quest;
import model.items.Equipment;
import model.misc.Position;

/**
 * Figure class.
 *
 * @author Jonas Ulrich, Markus Mogck
 */
public abstract class Figure
{

    private int healthPoints;
    private int attackPower;
    private int armorClass;
    private int movementPoints;

    private Position position;

    private Equipment equipment;

    private Image sprite;

    public Figure(int healthPoints,
                  int attackPower,
                  int armorClass,
                  int movementPoints,
                  Position location,
                  Equipment equipment,
                  Image sprite)
    {
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.armorClass = armorClass;
        this.movementPoints = movementPoints;
        this.position = location;
        this.equipment = equipment;
        this.sprite =  sprite;
    }

    public abstract void move(Quest quest);

    public void attack(Figure target)
    {
        //1 muss mit Waffenreichweite ersetzt werden.
        FightController.attackTarget(this, target, 1);
    }

    @Override
    public abstract String toString();

    public boolean isAlive()
    {
        return (this.healthPoints > 0);
    }

    public int getHealthPoints()
    {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints = healthPoints;
    }

    public int getAttackPower()
    {
        return attackPower;
    }

    public void setAttackPower(int attackPower)
    {
        this.attackPower = attackPower;
    }

    public int getArmorClass()
    {
        return armorClass;
    }

    public void setArmorClass(int armorClass)
    {
        this.armorClass = armorClass;
    }

    public int getMovementPoints()
    {
        return movementPoints;
    }

    public void setMovementPoints(int movementPoints)
    {
        this.movementPoints = movementPoints;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Equipment getEquipment()
    {
        return equipment;
    }

    public void setEquipment(Equipment equipment)
    {
        this.equipment = equipment;
    }

    public Image getSprite()
    {
        return sprite;
    }

    public void setSprite(Image sprite)
    {
        this.sprite = sprite;
    }
}
