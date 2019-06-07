package model.map;

import java.util.ArrayList;
import model.misc.Position;

/**
 * Class of the gameboard. The gameboard only contains scenery objects like
 * fields, rocks or water. These scenery objects are the squares in a tile.
 *
 * @author Markus Mogck
 */
public class GameBoard implements ISizedObject
{

    private Tile[][] tiles;

    /**
     * Constructor of the gameboard. Sets up a square gameboard.
     *
     * @param width horizontal tiles
     * @param height vertical tiles
     */
    public GameBoard(int width, int height)
    {
        this.tiles = new Tile[width][height];
    }

    /**
     * Returns all tiles.
     *
     * @return array of tiles
     */
    public Tile[][] getTiles()
    {
        return this.tiles;
    }
    
    public ArrayList<Tile> getTilesList()
    {
        ArrayList<Tile> tileList = new ArrayList<>();
        
        for (Tile[] tilesarray : this.tiles)
        {
            for (Tile tile : tilesarray)
            {
                tileList.add(tile);
            }
        }
        
        return tileList;
    }

    /**
     * Returns a particular tile.
     *
     * @param tilePosition tile position
     * @return Tile at tileposition
     */
    public Tile getTileAtTilePosition(Position tilePosition)
    {
        return getTileAtTilePosition(tilePosition.getX(),
                                     tilePosition.getY());
    }

    /**
     * Returns a particular tile.
     *
     * @param x x value tile position
     * @param y y value of the tile position
     * @return Tile at tileposition
     */
    public Tile getTileAtTilePosition(int x, int y)
    {
        return this.tiles[x][y];
    }

    /**
     * Sets the tiles.
     *
     * @param tiles
     */
    public void setTiles(Tile[][] tiles)
    {
        this.tiles = tiles;
    }

    /**
     * Sets a particular tile.
     *
     * @param tilePosition tile position
     * @param newTile new tile
     */
    public void setTileAtTilePosition(Position tilePosition, Tile newTile)
    {
        setTileAtTilePosition(tilePosition.getX(),
                              tilePosition.getY(),
                              newTile);
    }

    /**
     * Sets a particular tile.
     *
     * @param x x value of new tile position
     * @param y y value of new tile position
     * @param newTile
     */
    public void setTileAtTilePosition(int x, int y, Tile newTile)
    {
        this.tiles[x][y] = newTile;
    }

    /**
     * Returns the particular square at the given position.
     *
     * @param tilePosition tile position
     * @param squarePosition square position
     * @return Square at given position
     */
    public Square getSquareFromTileAndSquarePosition(Position tilePosition,
                                                     Position squarePosition)
    {
        return this.tiles[tilePosition.getX()][tilePosition.getY()]
                .getSquareAtSquarePosition(squarePosition);
    }

    /**
     * Returns the particular square at the given position.
     *
     * @param tile_x x value of tile position
     * @param tile_y y value of tile position
     * @param square_x x value of square position
     * @param square_y yvalue of square position
     * @return Square at given position
     */
    public Square getSquareFromTileAndSquarePosition(int tile_x,
                                                     int tile_y,
                                                     int square_x,
                                                     int square_y)
    {
        return this.tiles[tile_x][tile_y]
                .getSquareAtSquarePosition(square_x, square_y);
    }

    /**
     * Returns the particular square at the given position.
     *
     * @param position square and tile independent position
     * @return Square at given position
     */
    public Square getSquareFromPosition(Position position)
    {
        return getSquareFromTileAndSquarePosition(position.toTilePosition(),
                                                  position.toSquarePosition());
    }

    @Override
    public int getWidth()
    {
        return this.tiles.length;
    }

    @Override
    public int getHeight()
    {
        return this.tiles[0].length;
    }
}
