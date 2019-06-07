package control.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.ingamemanagement.EnemyController;
import exception.InvalidSquareCharacterException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.figure.EEnemy;
import model.figure.Enemy;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.map.ESquare;
import model.map.GameBoard;
import model.map.ISizedObject;
import model.map.Square;
import model.map.Tile;
import model.misc.Position;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IOutputStrings;
import view.IOColor;

/**
 * The MapController creates the gameboard from json files and controls actions
 * in relation to the gameboard.
 *
 * @author Markus Mogck
 */
public class MapController
{

    /**
     * Resets the marked squares
     *
     * @param gameBoard
     */
    public static void resetMarks(GameBoard gameBoard)
    {
        resetTiles(gameBoard);
    }

    /**
     * Resets the marks for the tiles.
     *
     * @param gameboard
     */
    private static void resetTiles(GameBoard gameboard)
    {
        for (int tiles_y = 0; tiles_y < IGameConstants.GAMEBOARD_TILES_HEIGHT; tiles_y++)
        {
            for (int tiles_x = 0; tiles_x < IGameConstants.GAMEBOARD_TILES_WIDTH; tiles_x++)
            {
                resetSquares(gameboard, tiles_x, tiles_y);
            }
        }
    }

    /**
     * Reset the squares in a tile.
     *
     * @param gameBoard
     * @param tiles_x x value of tile position
     * @param tiles_y y value of tile position
     */
    private static void resetSquares(GameBoard gameBoard, int tiles_x, int tiles_y)
    {
        for (int squares_y = 0; squares_y < IGameConstants.GAMEBOARD_SQUARES_HEIGHT;
             squares_y++)
        {
            for (int squares_x = 0;
                 squares_x < IGameConstants.GAMEBOARD_SQUARES_WIDTH;
                 squares_x++)
            {
                gameBoard.getTileAtTilePosition(tiles_x, tiles_y)
                        .getSquareAtSquarePosition(squares_x, squares_y)
                        .setMarked(false);
            }
        }
    }

    /**
     * Checks wether the player found a new tile at its current position and
     * uncovers it if found.
     *
     * @param quest active quest
     * @param position current position of the hero
     */
    public static void exploreNewFoundTileVisibleIfFound(Quest quest,
                                                         Position position)
    {
        if (isPositionAtNewTileEdge(position))
        {
            //Tile sichtbar machen
            quest.getGameBoard().getTileAtTilePosition(
                    getNewFoundTilePosition(quest.getGameBoard(), position))
                    .setVisible(true);

            //Gegner aufdecken und auf aktiv setzen
            EnemyController
                    .setEnemiesActiveAndGiveFocusedHero(quest,
                                                        getNewFoundTilePosition(
                                                                quest.getGameBoard(),
                                                                position));
        }
    }

    /**
     * Checks wether a position is on the edge of a tile.
     *
     * @param position position to be checked
     * @return true if position is at tile edge
     */
    public static boolean isPositionAtNewTileEdge(Position position)
    {
        return ((position.toSquarePosition().getX() == 0)
                || (position.toSquarePosition().getX()
                    == IGameConstants.GAMEBOARD_SQUARES_WIDTH - 1))
               || ((position.toSquarePosition().getY() == 0)
                   || (position.toSquarePosition().getY()
                       == IGameConstants.GAMEBOARD_SQUARES_HEIGHT - 1));
    }

    /**
     * Returns the position of the new found tile.
     *
     * @param gameBoard active gameboard
     * @param position player position
     * @return tile position
     */
    private static Position getNewFoundTilePosition(GameBoard gameBoard,
                                                    Position position)
    {
        if (position.toSquarePosition().getX() == 0)
        {
            return Position.getTilePositionFromPosition(position.getX() - 1,
                                                        position.getY());
        } else if (position.toSquarePosition().getX()
                   == IGameConstants.GAMEBOARD_SQUARES_WIDTH - 1)
        {
            return Position.getTilePositionFromPosition(position.getX() + 1,
                                                        position.getY());
        } else if (position.toSquarePosition().getY() == 0)
        {
            return Position.getTilePositionFromPosition(position.getX(),
                                                        position.getY() - 1);
        } else if (position.toSquarePosition().getY()
                   == IGameConstants.GAMEBOARD_SQUARES_HEIGHT - 1)
        {
            return Position.getTilePositionFromPosition(position.getX(),
                                                        position.getY() + 1);
        }

        return null;
    }

    /**
     * Creates new gameboard form filepath to json template.
     *
     * @param filepath filepath to json file
     * @return new GameBoard
     */
    public static GameBoard getNewGameBoard(String filepath)
    {
        return createGameBoardFromJsonMap(getJsonMapFromJsonFile(filepath));
    }

    /**
     * Returns the enemies saved at the json map.
     *
     * @param filepath filepath to json file
     * @return Array List of enemies
     */
    public static ArrayList<Enemy> getEnemysFromJsonMap(String filepath)
    {
        return getEnemysFromJsonMap(getJsonMapFromJsonFile(filepath));
    }

    /**
     * Returns the enemies saved at the json map.
     *
     * @param jsonMap json map
     * @return Array List of enemies
     */
    private static ArrayList<Enemy> getEnemysFromJsonMap(JsonMap jsonMap)
    {
        return jsonMap.getEnemies();
    }

    /**
     * Creates GameBoard from JsonMap.
     *
     * @param jsonMap
     * @return new GameBoard
     */
    private static GameBoard createGameBoardFromJsonMap(JsonMap jsonMap)
    {
        GameBoard gameBoard
                  = new GameBoard(IGameConstants.GAMEBOARD_TILES_WIDTH,
                                  IGameConstants.GAMEBOARD_TILES_HEIGHT);

        //Y-Wert der Tiles
        for (int tiles_y = 0; tiles_y < gameBoard.getHeight(); tiles_y++)
        {
            //X-Wert der Tiles
            for (int tiles_x = 0; tiles_x < gameBoard.getWidth(); tiles_x++)
            {
                //Neues Tile auf dem GameBoard platzieren
                gameBoard.setTileAtTilePosition(tiles_x, tiles_y,
                                                getNewTileFromJsonMap(
                                                        jsonMap, tiles_x, tiles_y));
            }
        }

        return gameBoard;
    }

    /**
     * Returns new tile at the given position from json map.
     *
     * @param jsonMap
     * @param tile_x tile position x
     * @param tile_y tile position y
     * @return new Tile
     */
    private static Tile getNewTileFromJsonMap(JsonMap jsonMap,
                                              int tile_x, int tile_y)
    {
        Tile newTile
             = new Tile(IGameConstants.GAMEBOARD_SQUARES_WIDTH,
                        IGameConstants.GAMEBOARD_SQUARES_HEIGHT,
                        isTileStartTile(new Position(tile_x, tile_y), jsonMap));

        //Y-Wert der Squares
        for (int squares_y = 0; squares_y < newTile.getHeight(); squares_y++)
        {
            //X-Wert der Squares
            for (int squares_x = 0; squares_x < newTile.getWidth(); squares_x++)
            {
                try
                {
                    newTile.setSquareAt(getSquareFromChar(
                            jsonMap.getCharAtPosition(Position
                                    .getPositionFromTileAndSquarePosition(
                                            new Position(
                                                    tile_x,
                                                    tile_y),
                                            new Position(
                                                    squares_x, squares_y)
                                    )
                            )
                    ),
                                        squares_x,
                                        squares_y);
                } catch (InvalidSquareCharacterException ex)
                {
                    //Fehler wenn aus dem Char kein Square
                    //erzeugt werden kann
                    Logger.getLogger(
                            MapController.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }

        return newTile;
    }

    /**
     * Checks wether the tile at the given position is the start tile.
     *
     * @param tilePosition
     * @param jsonMap
     * @return true if its the start tile
     */
    private static boolean isTileStartTile(Position tilePosition,
                                           JsonMap jsonMap)
    {
        return tilePosition.equals(jsonMap.getStartTilePosition());
    }

    /**
     * Returns a JsonMap from filepath to the json file.
     *
     * @param filepath filepath to json file
     * @return new JsonMap
     */
    private static JsonMap getJsonMapFromJsonFile(String filepath)
    {
        Gson gson = getGsonBuilder();
        try
        {
            JsonMap jsonMap
                    = gson.fromJson(new FileReader(filepath), JsonMap.class);
            return jsonMap;
        } catch (FileNotFoundException ex)
        {
            return null;
        }
    }

    /**
     * GsonBuilder for reading Json files.
     *
     * @return created GsonBuilder with PrettyPrinting
     */
    private static Gson getGsonBuilder()
    {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Creates new square from the Char at the json file
     *
     * @param charToConvert charachter to convert
     * @return new Square
     * @throws InvalidSquareCharacterException
     */
    public static Square getSquareFromChar(char charToConvert)
            throws InvalidSquareCharacterException
    {
        switch (charToConvert)
        {
            case IGameConstants.FIELD_CHAR:
                return new Square(ESquare.FIELD);
            case IGameConstants.ROCK_CHAR:
                return new Square(ESquare.ROCK);
            case IGameConstants.TREE_CHAR:
                return new Square(ESquare.TREE);
            case IGameConstants.WATER_CHAR:
                return new Square(ESquare.WATER);

            default:
                throw new InvalidSquareCharacterException(
                        IOutputStrings.EXCEPTION_INVALIDSQUARECHARACTER);
        }
    }

    /**
     * Returns an empty string array with given length.
     *
     * @param lenght length of the array
     * @return new array of empty strings ("", not null)
     */
    private static String[] getEmptyStringArray(int lenght)
    {
        String[] lines = new String[lenght];

        for (int m = 0; m < lines.length; m++)
        {
            lines[m] = "";
        }

        return lines;
    }

    /**
     * Returns the String of the square at the given position for the console
     * output.
     *
     * @param quest active quest
     * @param tile_x tile position x
     * @param tile_y tile position y
     * @param square_x square position x
     * @param square_y square position y
     * @return String with square sequence
     */
    private static String getStringForGameBoard(Quest quest,
                                                int tile_x,
                                                int tile_y,
                                                int square_x,
                                                int square_y
    )
    {
        for (Hero hero : quest.getHeroes())
        {
            if (hero.getPosition()
                    .equals(Position
                            .getPositionFromTileAndSquarePosition(tile_x,
                                                                  tile_y,
                                                                  square_x,
                                                                  square_y))
                && hero.isAlive())
            {
                return IOColor.FONT_BLUE.getStartSequenz()
                       + IOutputStrings.CONSOLE_MAPOUTPUT_HERO
                       + IOColor.FONT_BLUE.getEndSequenz()
                       + " ";
            }
        }
        for (Enemy enemy : quest.getEnemies())
        {
            if (enemy.getPosition()
                    .equals(Position
                            .getPositionFromTileAndSquarePosition(tile_x,
                                                                  tile_y,
                                                                  square_x,
                                                                  square_y))
                && enemy.isAlive())
            {
                return IOColor.FONT_RED.getStartSequenz()
                       + IOutputStrings.CONSOLE_MAPOUTPUT_ENEMY
                       + IOColor.FONT_RED.getEndSequenz()
                       + " ";
            }
        }

        return quest.getGameBoard().getTiles()[tile_x][tile_y]
                .getSquares()[square_x][square_y].getSymbol() + " ";

    }

    /**
     * JsonMap for getting a gameboard form json file.
     */
    private class JsonMap implements ISizedObject
    {

        private String[][] jsonMap = null;
        private Position startTilePosition = null;

        private ArrayList<JsonEnemy> jsonEnemies = null;

        public String[][] getJsonMap()
        {
            return jsonMap;
        }

        public void setJsonMap(String[][] jsonMap)
        {
            this.jsonMap = jsonMap;
        }

        public char getCharAtPosition(int x, int y)
        {
            return jsonMap[x][y].charAt(0);
        }

        public char getCharAtPosition(Position position)
        {
            return getCharAtPosition(position.getX(), position.getY());
        }

        public Position getStartTilePosition()
        {
            return this.startTilePosition;
        }

        public ArrayList<Enemy> getEnemies()
        {
            ArrayList<Enemy> enemies = new ArrayList<>();

            for (JsonEnemy jsonEnemy : this.jsonEnemies)
            {
                enemies.add(new Enemy(jsonEnemy.getEnemyType(),
                                      jsonEnemy.getPosition()));
            }

            return enemies;
        }

        public int getColumns()
        {
            return jsonMap.length;
        }

        public int getLines()
        {
            return jsonMap[0].length;
        }

        @Override
        public int getWidth()
        {
            return this.jsonMap.length;
        }

        @Override
        public int getHeight()
        {
            return this.jsonMap[0].length;
        }

        /**
         * Enemy Class in json format.
         */
        private class JsonEnemy
        {

            private EEnemy enemyType;
            private Position position;

            public JsonEnemy(EEnemy enemyType)
            {
                this.enemyType = enemyType;
            }

            public EEnemy getEnemyType()
            {
                return this.enemyType;
            }

            public Position getPosition()
            {
                return position;
            }
        }

    }
}
