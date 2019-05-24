package model.map;

import model.misc.Position;

/**
 *
 * @author Markus Mogck
 */
public class GameBoard implements ISizedObject
{

    private Tile[][] tiles;

    public GameBoard(int width, int height)
    {
        this.tiles = new Tile[width][height];
    }

    public Tile[][] getTiles()
    {
        return tiles;
    }

    public Tile getTileAtTilePosition(Position tilePosition)
    {
        return getTileAtTilePosition(tilePosition.getX(), tilePosition.getY());
    }

    public Tile getTileAtTilePosition(int x, int y)
    {
        return this.tiles[x][y];
    }

    public void setTiles(Tile[][] tiles)
    {
        this.tiles = tiles;
    }

    public void setTileAtTilePosition(Position tilePosition, Tile newTile)
    {
        setTileAtTilePosition(tilePosition.getX(),
                              tilePosition.getY(),
                              newTile);
    }

    public void setTileAtTilePosition(int x, int y, Tile newTile)
    {
        this.tiles[x][y] = newTile;
    }

    public Square getSquareFromTileAndSquarePosition(Position tilePosition,
                                                     Position squarePosition)
    {
        return this.tiles[tilePosition.getX()][tilePosition.getY()]
                .getSquareAtSquarePosition(squarePosition);
    }
    
    public Square getSquareFromTileAndSquarePosition(int tile_x,
                                                     int tile_y,
                                                     int square_x,
                                                     int square_y)
    {
        return this.tiles[tile_x][tile_y]
                .getSquareAtSquarePosition(square_x, square_y);
    }

    public Square getSquareFromPosition(Position position)
    {
        return getSquareFromTileAndSquarePosition(position.toTilePosition(),
                                                  position.toSquarePosition());
    }

    @Override
    public int getWidth()
    {
        return tiles.length;
    }

    @Override
    public int getHeight()
    {
        return tiles[0].length;
    }
}
