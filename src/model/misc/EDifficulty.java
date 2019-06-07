package model.misc;

import resources.gameconstants.IGamebalanceConstants;

/**
 * Enumeration that holds the different difficulties.
 *
 * @author Markus Mogck
 */
public enum EDifficulty
{
    EASY(IGamebalanceConstants.EASY_XPMULTIPLIER,
         IGamebalanceConstants.EASY_GOLDMULTIPLIER),
    MEDIUM(IGamebalanceConstants.MEDIUM_XPMULTIPLIER,
         IGamebalanceConstants.MEDIUM_GOLDMULTIPLIER),
    HARD(IGamebalanceConstants.HARD_XPMULTIPLIER,
         IGamebalanceConstants.HARD_GOLDMULTIPLIER);

    private final float xpMultiplier;
    private final float goldMultiplier;

    EDifficulty(float xpMultiplier,
                float goldMultiplier)
    {
        this.xpMultiplier = xpMultiplier;
        this.goldMultiplier = goldMultiplier;
    }

    public float getXpMultiplier()
    {
        return xpMultiplier;
    }

    public float getGoldMultiplier()
    {
        return goldMultiplier;
    }
}
