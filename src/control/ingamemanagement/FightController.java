package control.ingamemanagement;

import control.IOController;
import model.figure.Figure;
import model.misc.Dice;
import model.misc.Position;
import resources.gameconstants.IFightControllerConstants;
/**
 * 
 * @author Jonas Ulrich
 */
public class FightController 
{

    public static void attackTarget(Figure user, Figure target)
    {
        if(checkIfTargetIsInRange(user, target, 
                IFightControllerConstants.ONE) && 
                checkIfUserHitsTarget(user, target))
        {
            target.setHealthPoints(target.getHealthPoints() 
                                    - user.getAttackPower());
        }
        else
        {
            IOController.printMessage(IFightControllerConstants.FIGHT_UNSUCESSFUL);
        }
    }
    
    
    
    public static boolean checkIfTargetIsInRange(Figure user, Figure target, int range)
    {
        return getDistance(user.getPosition(), target.getPosition())<= range;
    }
    
    
    public static double getDistance(Position x, Position y)
        {
            double res;
            Position tmp = new Position(x.getX() - y.getX(),
                                        x.getY() - y.getY());        
            res = Math.abs(Math.sqrt(Math.pow(tmp.getX(), 
                           IFightControllerConstants.TWO) + Math.pow(tmp.getY(),
                           IFightControllerConstants.TWO)));        
            return res;
        }
    
    public static boolean checkIfUserHitsTarget(Figure user, Figure target)
    {
        return user.getArmorClass() < (target.getAttackPower()+ 
                Dice.getRandomDiceInteger());
    }
}