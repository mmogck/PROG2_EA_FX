package resources.gameconstants;

/**
 * Interface for Constants, which are changing the Balance of the Game.
 *
 * @author Markus Mogck, Jonas Ulrich, Felix Busch
 */
public interface IGamebalanceConstants
{

    //Hinderniswerte der verschiednen Felder
    public final int IMPEDIMENT_ROCK = Integer.MAX_VALUE;
    public final int IMPEDIMENT_FIELD = 1;
    public final int IMPEDIMENT_TREE = 2;
    public final int IMPEDIMENT_WATER = 4;

    //Aktionspunkte des Helden
    public final int HERO_ACTION_POINTS = 2;

    public final float LVLMULTIPLIER_BASE_VALUE = 0.7f;
    public final float LVLMULTIPLIER_SCALING = 0.1f;
    
    //Werte fuer die verschiedenen Schwierigkeitsstufen
    public final float EASY_XPMULTIPLIER = 0.5f;
    public final float EASY_GOLDMULTIPLIER = 0.5f;
    public final float MEDIUM_XPMULTIPLIER = 1f;
    public final float MEDIUM_GOLDMULTIPLIER = 1f;
    public final float HARD_XPMULTIPLIER = 1.5f;
    public final float HARD_GOLDMULTIPLIER = 1.5f;
}
