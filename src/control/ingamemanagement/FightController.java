package control.ingamemanagement;

import control.IOController;
import model.figure.Figure;
import model.misc.Dice;
import model.misc.Position;
import resources.gameconstants.IOutputStrings;

/**
 *
 * @author Jonas Ulrich
 */
public class FightController
{

    public static void attackTarget(Figure user, Figure target, int range)
    {
        if (checkIfTargetIsInRange(user, target,range)
            && checkIfUserHitsTarget(user, target))
        {
            target.setHealthPoints(target.getHealthPoints()
                                   - user.getAttackPower());
        } else
        {
            IOController.printMessage(IOutputStrings.ERROR_ATTACK_FAILED);
        }
    }

    public static boolean checkIfTargetIsInRange(Figure user, Figure target, int range)
    {
        return getDistance(user.getPosition(), target.getPosition()) <= range;
    }

    public static double getDistance(Position x, Position y)
    {
        double res;
        Position tmp = new Position(x.getX() - y.getX(),
                                    x.getY() - y.getY());
        res = Math.abs(Math.sqrt(Math.pow(tmp.getX(),
                                          2) + Math.pow(tmp.getY(),
                                                        2)));
        return res;
    }

    public static boolean checkIfUserHitsTarget(Figure user, Figure target)
    {
        return user.getArmorClass() < (target.getAttackPower()
                                       + Dice.getRandomDiceInteger());
    }
}
