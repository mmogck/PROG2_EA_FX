package model.misc;

import java.util.Random;
import resources.gameconstants.IGameConstants;

/**
 * Class for Random operations like the ingame dice.
 *
 * @author Markus Mogck
 */
public class Dice
{

    private static Random random = new Random();

    public static int getRandomInteger()
    {
        return random.nextInt();
    }

    public static int getRandomInteger(int min, int max)
    {
        //+1, da random.nextInt bei 0 anfängt
        return min + random.nextInt(max + 1 - min);
    }

    public static int getRandomDiceInteger()
    {
        return getRandomInteger(IGameConstants.DICE_MIN_VALUE,
                                IGameConstants.DICE_MAX_VALUE);
    }
}
