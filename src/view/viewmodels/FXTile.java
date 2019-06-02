package view.viewmodels;

import javafx.scene.layout.GridPane;
import model.ingamemanagement.Quest;
import model.misc.Position;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IGuiConstants;

/**
 * GridPane with an Array of the squares (as StackPanes).
 *
 * @author Markus Mogck
 */
public class FXTile extends GridPane
{

    private Position tilePosition;

    private FXSquare[][] fxsquares;
    private boolean visible;

    public FXTile(Position tilePosition)
    {
        this.tilePosition = tilePosition;

        this.setVgap(IGuiConstants.GAMEBOARD_SQUARES_GAP);
        this.setHgap(IGuiConstants.GAMEBOARD_SQUARES_GAP);
        
        fxsquares = new FXSquare[IGameConstants.GAMEBOARD_SQUARES_WIDTH][IGameConstants.GAMEBOARD_SQUARES_HEIGHT];
        initializeFXSquares();
    }

    private void initializeFXSquares()
    {
        for (int y = 0; y < fxsquares.length; y++)
        {
            for (int x = 0; x < fxsquares[0].length; x++)
            {
                fxsquares[x][y]
                = new FXSquare(Position
                        .getPositionFromTileAndSquarePosition(tilePosition.getX(),
                                                              tilePosition.getY(),
                                                              x,
                                                              y));

                GridPane.setConstraints(fxsquares[x][y], x, y);

                this.getChildren().add(fxsquares[x][y]);
            }
        }
    }
    
    public void printSquares(Quest quest)
    {
        for (int y = 0; y < fxsquares.length; y++)
        {
            for (int x = 0; x < fxsquares[0].length; x++)
            {
                fxsquares[x][y].print(quest);
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
