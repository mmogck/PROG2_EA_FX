package model.figure;

import control.ingamemanagement.EnemyController;
import model.ingamemanagement.Quest;
import model.misc.Position;

/**
 * The Enemy Class contains informations about enemy figures.
 *
 * @author Jonas Ulrich
 */
public class Enemy extends Figure
{
    private boolean active;
    private Hero focusedHero;

    public Enemy(EnemyEnum enemyType, Position position)
    {
        super(enemyType.getHealthPoints(),
              enemyType.getAttackPower(),
              enemyType.getArmorClass(),
              0,
              position,
              enemyType.getEquipment());
    }

    @Override
    public void move(Quest quest)
    {
        EnemyController.moveEnemyToFocusedHero(this, quest);
    }
    
    @Override
    public String toString()
    {
        return "Figure: Enemy" + "; "
                + "Position: " + super.getPosition() + "; "
                + "Health Points: " + super.getHealthPoints() + "; "
                + "Attack Power: " + super.getAttackPower() + "; "
                + "Armor Class: " + super.getArmorClass() + "; "
                + "Active: " + this.active + "; "
                + "Focused Hero: " + this.focusedHero;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public Hero getFocusedHero()
    {
        return focusedHero;
    }

    public void setFocusedHero(Hero focusedHero)
    {
        this.focusedHero = focusedHero;
    }

}
