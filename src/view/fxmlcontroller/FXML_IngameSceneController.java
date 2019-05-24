package view.fxmlcontroller;

import control.IOController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.figure.Enemy;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import resources.gameconstants.IFileConstants;
import resources.gameconstants.IGameConstants;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_IngameSceneController implements Initializable
{

    @FXML
    private AnchorPane anchorpane_gameboard;

    private GridPane gridpane_gameboard;
    private GridPane[][] gridpane_tiles;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeGameGrid();
        //resetImageViews();
    }

    public void printGameBoard(Quest quest)
    {
        for (int tile_y = 0;
             tile_y < IGameConstants.GAMEBOARD_TILES_HEIGHT; tile_y++)
        {
            for (int tile_x = 0;
                 tile_x < IGameConstants.GAMEBOARD_TILES_HEIGHT; tile_x++)
            {
                for (int square_y = 0;
                     square_y < IGameConstants.GAMEBOARD_TILES_HEIGHT;
                     square_y++)
                {
                    for (int square_x = 0;
                         square_x < IGameConstants.GAMEBOARD_TILES_HEIGHT;
                         square_x++)
                    {
                        //zeige passendes sprite zu square
                        
                        if (quest.getGameBoard()
                                .getSquareFromTileAndSquarePosition(tile_x,
                                                                    tile_y,
                                                                    square_x,
                                                                    square_y)
                                .isMarked())
                        {
                            for (Node node : gridpane_tiles[tile_x][tile_y].getChildren())
                            {
                                
                            }
                        }
                    }
                }
            }
        }

        for (Hero hero : quest.getHeroes())
        {

        }

        for (Enemy enemy : quest.getEnemies())
        {

        }
    }
    
    private void printTile()
    {
        
    }

    private void resetImageViews()
    {
        for (Node node_tiles : gridpane_gameboard.getChildren())
        {
            GridPane grid = (GridPane) node_tiles;
            for (Node node_squares : grid.getChildren())
            {
                StackPane stackPane = (StackPane) node_squares;
                for (Node node_sprites : stackPane.getChildren())
                {
                    node_sprites.setVisible(false);
                }
            }
        }
    }

    @FXML
    private void handleMenuFileCloseAction(ActionEvent event)
    {
        IOController.closeGame();
    }

    private void initializeGameGrid()
    {
        gridpane_tiles
        = new GridPane[IGameConstants.GAMEBOARD_TILES_WIDTH][IGameConstants.GAMEBOARD_TILES_HEIGHT];

        gridpane_gameboard = getTilesGridPane();
        anchorpane_gameboard.getChildren().add(gridpane_gameboard);
    }

    private GridPane getTilesGridPane()
    {
        GridPane gridPane = new GridPane();

        gridPane.setVgap(4);
        gridPane.setHgap(4);

        for (int i = 0; i < IGameConstants.GAMEBOARD_TILES_HEIGHT; i++)
        {
            for (int j = 0; j < IGameConstants.GAMEBOARD_TILES_WIDTH; j++)
            {
                gridpane_tiles[j][i] = getSquaresGridPane();
                gridPane.add(gridpane_tiles[j][i], j, i, 1, 1);
            }
        }

        return gridPane;
    }

    private GridPane getSquaresGridPane()
    {
        GridPane gridPane = new GridPane();

        gridPane.setPrefWidth(anchorpane_gameboard.getWidth());
        gridPane.setPrefHeight(anchorpane_gameboard.getHeight());
        gridPane.setVgap(1);
        gridPane.setHgap(1);

        for (int i = 0; i < IGameConstants.GAMEBOARD_SQUARES_HEIGHT; i++)
        {
            for (int j = 0; j < IGameConstants.GAMEBOARD_SQUARES_WIDTH; j++)
            {
                gridPane.add(getStackPaneWithImageViews(), j, i, 1, 1);
            }
        }

        return gridPane;
    }

    private StackPane getStackPaneWithImageViews()
    {
        StackPane stackPane = new StackPane();

        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_FIELD));
        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_ROCK));
        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_WATER));
        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_TREE));
        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_HERO));
        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_ENEMY));
        stackPane.getChildren()
                .add(getImageView(IFileConstants.FILE_PATH_SPRITE_MARKED));

        return stackPane;
    }

    private ImageView getImageView(String pathToImage)
    {
        ImageView imageView
                  = new ImageView(new File(pathToImage).toURI().toString());

        // LAYOUT AENDERN; SODASS DIE GROESSEN SICH AUTOMATISCH ANPASSEN!!!!!!!!!!!!
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);

        return imageView;
    }

}
