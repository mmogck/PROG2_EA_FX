package model.map;

import javafx.scene.image.Image;
import view.IOColor;

/**
 * 
 *
 * @author Markus Mogck
 */
public class Square
{
    private ESquare eSquare;
    
    private boolean marked;
    private int movementPointsToSubtract;
    
    private final int impediment;
    
    private final char symbol;
    private final Image sprite;

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
