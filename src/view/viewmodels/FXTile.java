package view.viewmodels;

import javafx.scene.layout.GridPane;
import model.misc.Position;
import resources.gameconstants.IGameConstants;

/**
 * GridPane with an Array of the squares (as StackPanes).
 *
 * @author Markus Mogck
 */
public class FXTile extends GridPane
{
    private FXSquare[][] fxsquares;
    private boolean visible;

    public FXTile()
    {
        fxsquares = new FXSquare[IGameConstants.GAMEBOARD_SQUARES_WIDTH][IGameConstants.GAMEBOARD_SQUARES_HEIGHT];
        initializeFXSquares();
    }
    
    private void initializeFXSquares()
    {
        for (int i = 0; i < fxsquares.length; i++)
        {
            for (int j = 0; j < fxsquares[0].length; j++)
            {
                fxsquares[i][j] = new FXSquare();
                this.getChildren().add(fxsquares[i][j]);
            }
        }
    }
    
    public boolean isTileVisible()
    {
        return visible;
    }
    
    public void setTileVisible(boolean visible)
    {
        this.visible = visible;
    }

    public FXSquare[][] getFxsquares()
    {
        return fxsquares;
    }
    
    public FXSquare getFXSquareAtPosition(Position position)
    {
        return fxsquares[position.getX()][position.getY()];
    }
    
    public FXSquare getFXSquareAtTileAndSquarePosition(Position tilePosition,
                                                       Position squarePosition)
    {
        return getFXSquareAtPosition(
                Position.getPositionFromTileAndSquarePosition(tilePosition,
                                                              squarePosition));
    }

    public void setFxsquares(FXSquare[][] fxsquares)
    {
        this.fxsquares = fxsquares;
    }
}
