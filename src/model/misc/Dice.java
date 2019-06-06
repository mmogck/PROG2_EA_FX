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

    /**
     * Returns a random integer in bounds of integer min and max value.
     *
     * @return
     */
    public static int getRandomInteger()
    {
        return random.nextInt();
    }

    /**
     * Returns a random integer in bounds of min and max.
     *
     * @param min lower bound
     * @param max upper bound
     * @return random integer
     */
    public static int getRandomInteger(int min, int max)
    {
        //+1, da random.nextInt bei 0 anfängt
        return min + random.nextInt(max + 1 - min);
    }

    /**
     * Returns and random integer in bounds of the games dice.
     *
     * @return
     */
    public static int getRandomDiceInteger()
    {
        return getRandomInteger(IGameConstants.DICE_MIN_VALUE,
                                IGameConstants.DICE_MAX_VALUE);
    }
}
