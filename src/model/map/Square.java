package model.map;

import javafx.scene.image.Image;
import view.IOColor;

/**
 * Class for scenery squares.
 *
 * @author Markus Mogck
 */
public class Square
{

    private ESquare eSquare;

    //Square gets marked if in range for movement
    //not something the square should know                     !!!!!!!!!!!!!
    private boolean marked;
    //Movementpoints needed to reach this square when marked
    private int movementPointsToSubtract;

    private final int impediment;

    private final char symbol;
    private final Image sprite;

    /**
     * Constructor of square. The constructor uses an element of an enumeration
     * as template for creating a new square.
     *
     * @param eSquare template square
     */
    public Square(ESquare eSquare)
    {
        this.eSquare = eSquare;

        this.marked = false;
        this.movementPointsToSubtract = Integer.MAX_VALUE;

        this.impediment = eSquare.getImpediment();

        this.symbol = eSquare.getSymbol();
        this.sprite = eSquare.getSprite();
    }

    public void setMarked(boolean marked)
    {
        this.marked = marked;
    }

    public boolean isMarked()
    {
        return this.marked;
    }

    public ESquare getESquare()
    {
        return eSquare;
    }

    public int getMovementPointsToSubtract()
    {
        return movementPointsToSubtract;
    }

    public void setMovementPointsToSubtract(int movementPointsToSubtract)
    {
        this.movementPointsToSubtract = movementPointsToSubtract;
    }

    public int getImpediment()
    {
        return impediment;
    }

    public String getSymbol()
    {
        //Gruener hintergrund wenn markiert
        if (marked)
        {
            return IOColor.BACKGROUND_GREEN.getStartSequenz()
                   + Character.toString(this.symbol)
                   + IOColor.BACKGROUND_GREEN.getEndSequenz();
        } else
        {
            return Character.toString(this.symbol);
        }
    }

    public Object getSprite()
    {
        return sprite;
    }
}
