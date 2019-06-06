package control.ingamemanagement;

import control.IOController;
import model.figure.Figure;
import model.misc.Dice;
import model.misc.Position;
import resources.gameconstants.IOutputStrings;

/**
 * This class handles the fighting between two figures.
 *
 * @author Jonas Ulrich
 */
public class FightController
{

    /**
     * Attacks the target an deduckts the HealthPoints if the attack is
     * succesfull.
     *
     * @param user
     * @param target
     * @param range
     */
    public static void attackTarget(Figure user, Figure target, int range)
    {
        if (checkIfTargetIsInRange(user, target, range)
            && checkIfUserHitsTarget(user, target))
        {
            target.setHealthPoints(target.getHealthPoints()
                                   - user.getAttackPower());
        } else
        {
            IOController.printMessage(IOutputStrings.ERROR_ATTACK_FAILED);
        }
    }

    /**
     * Checks if the target is within the users range by comparing it to the
     * distance between the two.
     *
     * @param user
     * @param target
     * @param range
     * @return true or false
     */
    public static boolean checkIfTargetIsInRange(Figure user, Figure target,
                                                 int range)
    {
        return getDistance(user.getPosition(), target.getPosition()) <= range;
    }

    /**
     * Determines the distance between two positions.
     *
     * @param position1
     * @param position2
     * @return the numerical distance between the two positions
     */
    public static double getDistance(Position position1, Position position2)
    {
        double res;
        Position tmp = new Position(position1.getX() - position2.getX(),
                                    position1.getY() - position2.getY());
        res = Math.abs(Math.sqrt(Math.pow(tmp.getX(),
                                          2) + Math.pow(tmp.getY(),
                                                        2)));
        return res;
    }

    /**
     * Checks if th3e attacker hits the target.
     *
     * @param user
     * @param target
     * @return true or false, depending on the outcome of the comparioson.
     */
    public static boolean checkIfUserHitsTarget(Figure user, Figure target)
    {
        return user.getArmorClass() < (target.getAttackPower()
                                       + Dice.getRandomDiceInteger());
    }
}
