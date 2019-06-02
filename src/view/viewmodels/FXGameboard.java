package view.viewmodels;

import javafx.scene.layout.GridPane;
import model.misc.Position;
import resources.gameconstants.IGameConstants;

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
        
        initializeFXTiles();
    }

    private void initializeFXTiles()
    {
        for (int i = 0; i < fxtiles.length; i++)
        {
            for (int j = 0; j < fxtiles[0].length; j++)
            {
                fxtiles[i][j] = new FXTile();
                this.getChildren().add(fxtiles[i][j]);
            }
        }
    }
    
    public FXTile[][] getFxtiles()
    {
        return fxtiles;
    }

    public FXTile getFXTileAtPosition(Position position)
    {
        return getFXTileAtTilePosition(Position.getTileFromPosition(position));
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
