package view.viewmodels;

import javafx.scene.layout.GridPane;
import model.ingamemanagement.Quest;
import model.misc.Position;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IGuiConstants;

/**
 *
 * @author Markus Mogck
 */
public class FXGameboard extends GridPane
{

    private FXTile[][] fxtiles;

    public FXGameboard()
    {
        fxtiles = new FXTile[IGameConstants.GAMEBOARD_TILES_WIDTH][IGameConstants.GAMEBOARD_TILES_HEIGHT];

        this.setVgap(IGuiConstants.GAMEBOARD_TILES_GAP);
        this.setHgap(IGuiConstants.GAMEBOARD_TILES_GAP);

        initializeFXTiles();
    }

    private void initializeFXTiles()
    {
        for (int y = 0; y < fxtiles.length; y++)
        {
            for (int x = 0; x < fxtiles[0].length; x++)
            {
                fxtiles[x][y] = new FXTile(new Position(x, y));

                GridPane.setConstraints(fxtiles[x][y], x, y);

                this.getChildren().add(fxtiles[x][y]);
            }
        }
    }

    public void printTiles(Quest quest)
    {
        for (int y = 0; y < fxtiles.length; y++)
        {
            for (int x = 0; x < fxtiles[0].length; x++)
            {
                if (isTileVisible(quest, new Position(x, y)))
                {
                    fxtiles[x][y].printSquares(quest);
                } else
                {
                    fxtiles[x][y].printTileAsNotVisible();
                }
            }
        }
    }

    private boolean isTileVisible(Quest quest, Position tilePosition)
    {
        return quest
                .getGameBoard()
                .getTileAtTilePosition(tilePosition).isVisible();
    }

    public FXTile[][] getFxtiles()
    {
        return fxtiles;
    }

    public FXTile getFXTileAtPosition(Position position)
    {
        return getFXTileAtTilePosition(Position.getTilePositionFromPosition(position));
    }

    public FXTile getFXTileAtTilePosition(Position tilePosition)
    {
        return this.fxtiles[tilePosition.getX()][tilePosition.getY()];
    }

    public void setFxtiles(FXTile[][] fxtiles)
    {
        this.fxtiles = fxtiles;
    }
}
