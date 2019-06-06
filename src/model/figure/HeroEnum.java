package model.figure;

import javafx.scene.image.Image;
import model.items.Equipment;
import model.misc.Position;
import resources.gameconstants.IGameConstants;
import view.ESprites;

/**
 * Enum with the values for the differnt heroes.
 *
 * @autor Markus Mogck
 */
public enum HeroEnum
{

    HERO1(150, 10, 10, 4,
          new Position(Position.getPositionFromTileAndSquarePosition(
                          IGameConstants.START_TILE_POSITIOM,
                          new Position(1, 1))),
          null, ESprites.HERO.getImage()),
    HERO2(100, 34, 8, 5,
          new Position(Position.getPositionFromTileAndSquarePosition(
                          IGameConstants.START_TILE_POSITIOM,
                          new Position(1, 3))),
          null, ESprites.HERO.getImage()),
    HERO3(80, 50, 8, 5,
          new Position(Position.getPositionFromTileAndSquarePosition(
                          IGameConstants.START_TILE_POSITIOM,
                          new Position(3, 1))),
          null, ESprites.HERO.getImage()),
    HERO4(50, 40, 8, 10,
          new Position(Position.getPositionFromTileAndSquarePosition(
                          IGameConstants.START_TILE_POSITIOM,
                          new Position(3, 3))),
          null, ESprites.HERO.getImage()),;

    private final int healthPoints;
    private final int attackPower;
    private final int armorClass;

    private final int movementPoints;
    private final Position position;

    private final Equipment equipment;

    private final Image sprite;

    private HeroEnum(int healthPoints,
                     int attackPower,
                     int armorClass,
                     int movementPoints,
                     Position position,
                     Equipment equipment,
                     Image sprite)
    {
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.armorClass = armorClass;

        this.movementPoints = movementPoints;
        this.position = position;

        this.equipment = equipment;
        this.sprite = sprite;
    }

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

    public int getMovementPoints()
    {
        return movementPoints;
    }

    public Position getPosition()
    {
        return position;
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
