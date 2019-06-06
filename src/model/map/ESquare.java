package model.map;

import javafx.scene.image.Image;
import resources.gameconstants.IGamebalanceConstants;
import resources.gameconstants.IOutputStrings;
import view.ESprites;

/**
 * Contains the different scenery objects with their sprites and impediment
 * values.
 *
 * @author Markus Mogck
 */
public enum ESquare
{

    ROCK(IGamebalanceConstants.IMPEDIMENT_ROCK,
         IOutputStrings.CONSOLE_MAPOUTPUT_ROCK,
         ESprites.ROCK.getImage()),
    FIELD(IGamebalanceConstants.IMPEDIMENT_FIELD,
          IOutputStrings.CONSOLE_MAPOUTPUT_FIELD,
          ESprites.FIELD.getImage()),
    TREE(IGamebalanceConstants.IMPEDIMENT_TREE,
         IOutputStrings.CONSOLE_MAPOUTPUT_TREE,
         ESprites.TREE.getImage()),
    WATER(IGamebalanceConstants.IMPEDIMENT_WATER,
          IOutputStrings.CONSOLE_MAPOUTPUT_WATER,
          ESprites.WATER.getImage());

    private final int impediment;
    private final char symbol;
    private final Image sprite;

    ESquare(int impediment, char symbol, Image image)
    {
        this.impediment = impediment;
        this.symbol = symbol;
        this.sprite = image;
    }

    public int getImpediment()
    {
        return impediment;
    }

    public char getSymbol()
    {

        return this.symbol;
    }

    public Image getSprite()
    {
        return sprite;
    }
}
