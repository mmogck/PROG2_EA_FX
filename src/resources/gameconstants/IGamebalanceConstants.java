package resources.gameconstants;

/**
 * Interface for Constants, which are changing the Balance of the Game.
 *
 * @author Markus Mogck, Jonas Ulrich, Felix Busch
 */
public interface IGamebalanceConstants
{

    public final int IMPEDIMENT_ROCK = Integer.MAX_VALUE;
    public final int IMPEDIMENT_FIELD = 1;
    public final int IMPEDIMENT_TREE = 2;
    public final int IMPEDIMENT_WATER = 4;

    public final int HERO_ACTION_POINTS = 2;

    public final float LVLMULTIPLIER_BASE_VALUE = 0.7f;
    public final float LVLMULTIPLIER_SCALING = 0.1f;
}
