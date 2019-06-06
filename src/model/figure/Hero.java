package model.figure;

import control.ingamemanagement.MoveController;
import javafx.scene.image.Image;
import model.ingamemanagement.Quest;
import model.items.Equipment;
import model.misc.Position;

/**
 * Class for the playable figure hero.
 *
 * @author Jonas Ulrich
 */
public class Hero extends Figure
{

    public Hero(int healthPoints,
                int attackPower,
                int armorClass,
                int movementPoints,
                Position position,
                Equipment equipment,
                Image sprite)
    {
        super(healthPoints,
              attackPower,
              armorClass,
              movementPoints,
              position,
              equipment,
              sprite);
    }

    public Hero(HeroEnum hero)
    {
        super(hero.getHealthPoints(),
              hero.getAttackPower(),
              hero.getArmorClass(),
              hero.getMovementPoints(),
              hero.getPosition(),
              hero.getEquipment(),
              hero.getSprite());
    }

    @Override
    public void move(Quest quest)
    {
        MoveController.initializeHeroMovement(this, quest);
    }

    @Override
    public String toString()
    {
        return "Figure: Hero" + "; "
               + "Position: " + super.getPosition() + "; "
               + "Health Points: " + super.getHealthPoints() + "; "
               + "Attack Power: " + super.getAttackPower() + "; "
               + "Armor Class: " + super.getArmorClass() + "; "
               + "Equipment: " + super.getEquipment();
    }
}
