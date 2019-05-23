package model.map;

import resources.gameconstants.IGamebalanceConstants;
import resources.gameconstants.IOutputStrings;

/**
 * Contains the different scenery objects with their sprites and impediment
 * values.
 *
 * @author TSB8TG
 */
public enum ESquare
{

    ROCK(IGamebalanceConstants.IMPEDIMENT_ROCK,
         IOutputStrings.CONSOLE_MAPOUTPUT_ROCK),
    FIELD(IGamebalanceConstants.IMPEDIMENT_FIELD,
          IOutputStrings.CONSOLE_MAPOUTPUT_FIELD),
    TREE(IGamebalanceConstants.IMPEDIMENT_TREE,
         IOutputStrings.CONSOLE_MAPOUTPUT_TREE),
    WATER(IGamebalanceConstants.IMPEDIMENT_WATER,
          IOutputStrings.CONSOLE_MAPOUTPUT_WATER);

    private final int impediment;
    private final char symbol;
    private final Object sprite;

    ESquare(int impediment, char symbol)
    {
        this.impediment = impediment;
        this.symbol = symbol;
        this.sprite = null;
    }

    public int getImpediment()
    {
        return impediment;
    }

    public char getSymbol()
    {

        return this.symbol;
    }

    public Object getSprite()
    {
        return sprite;
    }
}
