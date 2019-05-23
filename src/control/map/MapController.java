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
import model.figure.Enemy;
import model.figure.EnemyEnum;
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

    public static void printGameBoardToConsole(Quest quest)
    {
        String output = "";

        //Höhe der Tiles (y)
        for (int i = 0; i < IGameConstants.GAMEBOARD_TILES_HEIGHT; i++)
        {
            String[] lines
                     = getEmptyStringArray(IGameConstants.GAMEBOARD_TILES_HEIGHT);

            //Breite der Tiles (x)
            for (int j = 0; j < IGameConstants.GAMEBOARD_TILES_WIDTH; j++)
            {
                //Höhe der Squares (y)
                for (int k = 0; k < IGameConstants.GAMEBOARD_SQUARES_HEIGHT; k++)
                {
                    //Breite der Squares (x)
                    for (int l = 0;
                         l < IGameConstants.GAMEBOARD_SQUARES_WIDTH;
                         l++)
                    {

                        if (quest.getGameBoard().getTileAtTilePosition(j, i).isVisible())
                        {
                            lines[k] += getStringForGameBoard(quest,
                                                              j, i, l, k);
                        } else
                        {
                            lines[k] += "? ";
                        }
                    }
                }
            }
            //Ausgabe der Zeilen für die erste Tile-Reihe
            for (String line : lines)
            {
                output += (line + "\n");
            }
        }

        System.out.println(output);
    }

    public static void resetMarks(GameBoard gameBoard)
    {
        for (int i = 0; i < IGameConstants.GAMEBOARD_TILES_HEIGHT; i++)
        {
            for (int j = 0; j < IGameConstants.GAMEBOARD_TILES_WIDTH; j++)
            {
                for (int k = 0; k < IGameConstants.GAMEBOARD_SQUARES_HEIGHT;
                     k++)
                {
                    for (int l = 0;
                         l < IGameConstants.GAMEBOARD_SQUARES_WIDTH;
                         l++)
                    {
                        gameBoard.getTileAtTilePosition(j, i)
                                .getSquareAtSquarePosition(l, k)
                                .setMarked(false);
                    }
                }
            }
        }
    }

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

    public static boolean isPositionAtNewTileEdge(Position position)
    {
        return ((position.toSquarePosition().getX() == 0)
                || (position.toSquarePosition().getX()
                    == IGameConstants.GAMEBOARD_SQUARES_WIDTH - 1))
               || ((position.toSquarePosition().getY() == 0)
                   || (position.toSquarePosition().getY()
                       == IGameConstants.GAMEBOARD_SQUARES_HEIGHT - 1));
    }

    private static Position getNewFoundTilePosition(GameBoard gameBoard,
                                                    Position position)
    {
        if (position.toSquarePosition().getX() == 0)
        {
            return Position.getTileFromPosition(
                    position.getX() - 1,
                    position.getY());
        } else if (position.toSquarePosition().getX()
                   == IGameConstants.GAMEBOARD_SQUARES_WIDTH - 1)
        {
            return Position.getTileFromPosition(
                    position.getX() + 1,
                    position.getY());
        } else if (position.toSquarePosition().getY() == 0)
        {
            return Position.getTileFromPosition(
                    position.getX(),
                    position.getY() - 1);
        } else if (position.toSquarePosition().getY()
                   == IGameConstants.GAMEBOARD_SQUARES_HEIGHT - 1)
        {
            return Position.getTileFromPosition(
                    position.getX(),
                    position.getY() + 1);
        }

        return null;
    }

    public static GameBoard getNewGameBoard(String filepath)
    {
        return createGameBoardFromJsonMap(getJsonMapFromJsonFile(filepath));
    }

    public static ArrayList<Enemy> getEnemysFromJsonMap(String filepath)
    {
        return getEnemysFromJsonMap(getJsonMapFromJsonFile(filepath));
    }

    private static ArrayList<Enemy> getEnemysFromJsonMap(JsonMap jsonMap)
    {
        return jsonMap.getEnemies();
    }

    private static GameBoard createGameBoardFromJsonMap(JsonMap jsonMap)
    {
        GameBoard gameBoard
                  = new GameBoard(IGameConstants.GAMEBOARD_TILES_WIDTH,
                                  IGameConstants.GAMEBOARD_TILES_HEIGHT);

        //Y-Wert der Tiles
        for (int i = 0; i < gameBoard.getHeight(); i++)
        {
            //X-Wert der Tiles
            for (int j = 0; j < gameBoard.getWidth(); j++)
            {
                //Neues Tile auf dem GameBoard platzieren
                gameBoard.setTileAtTilePosition(j, i,
                                                getNewTileFromJsonMap(
                                                        jsonMap, j, i));
            }
        }

        return gameBoard;
    }

    private static Tile getNewTileFromJsonMap(JsonMap jsonMap,
                                              int tile_x, int tile_y)
    {
        Tile newTile
             = new Tile(IGameConstants.GAMEBOARD_SQUARES_WIDTH,
                        IGameConstants.GAMEBOARD_SQUARES_HEIGHT,
                        isTileStartTile(new Position(tile_x, tile_y), jsonMap));

        //Y-Wert der Squares
        for (int k = 0; k < newTile.getHeight(); k++)
        {
            //X-Wert der Squares
            for (int l = 0; l < newTile.getWidth(); l++)
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
                                                    l, k)
                                    )
                            )
                    ),
                                        l,
                                        k);
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

    private static boolean isTileStartTile(Position tilePosition,
                                           JsonMap jsonMap)
    {
        return tilePosition.equals(jsonMap.getStartTilePosition());
    }

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

    private static Gson getGsonBuilder()
    {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    public static Square getSquareFromChar(char chartoconvert)
            throws InvalidSquareCharacterException
    {
        switch (chartoconvert)
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
                throw new InvalidSquareCharacterException();
        }
    }

    private static String[] getEmptyStringArray(int lenght)
    {
        String[] lines = new String[lenght];

        for (int m = 0; m < lines.length; m++)
        {
            lines[m] = "";
        }

        return lines;
    }

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

        private class JsonEnemy
        {
            private EnemyEnum enemyType;
            private Position position;

            public JsonEnemy(EnemyEnum enemyType)
            {
                this.enemyType = enemyType;
            }

            public EnemyEnum getEnemyType()
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
