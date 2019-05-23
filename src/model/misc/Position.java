package model.misc;

import resources.gameconstants.IGameConstants;

/**
 * Class for absolute Position. Variables 'x' and 'y' are not depending on Tiles
 * and Squares.
 *
 * @author Markus Mogck
 */
public class Position implements Comparable<Position>
{

    private int x;
    private int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Position(Position position)
    {
        this(position.getX(), position.getY());
    }

    public Position toTilePosition()
    {
        return getTileFromPosition(this);
    }

    public Position toSquarePosition()
    {
        return getSquareFromPosition(this);
    }

    public static Position getPositionFromTileAndSquarePosition(Position tilePosition,
                                                                Position squarePosition)
    {
        return getPositionFromTileAndSquarePosition(tilePosition.getX(),
                                                    tilePosition.getY(),
                                                    squarePosition.getX(),
                                                    squarePosition.getY());
    }

    public static Position getPositionFromTileAndSquarePosition(int tile_x,
                                                                int tile_y,
                                                                int square_x,
                                                                int square_y)
    {
        return new Position((tile_x * 5) + square_x,
                            (tile_y * 5) + square_y);
    }

    public static Position getTileFromPosition(Position position)
    {
        return getTileFromPosition(position.getX(), position.getY());
    }

    public static Position getTileFromPosition(int x, int y)
    {
        return new Position((int) x / IGameConstants.GAMEBOARD_TILES_WIDTH,
                            (int) y / IGameConstants.GAMEBOARD_TILES_HEIGHT);
    }

    public static Position getSquareFromPosition(Position position)
    {
        return getSquareFromPosition(position.getX(), position.getY());
    }

    public static Position getSquareFromPosition(int x, int y)
    {
        Position tilePosition = getTileFromPosition(x, y);

        return new Position(x - (tilePosition.getX()
                                 * IGameConstants.GAMEBOARD_SQUARES_WIDTH),
                            y - (tilePosition.getY()
                                 * IGameConstants.GAMEBOARD_SQUARES_HEIGHT));
    }

    @Override
    public String toString()
    {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x)
        {
            return false;
        }
        if (this.y != other.y)
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Position o)
    {
        if ((x == o.getX()) && (y == o.getY()))
        {
            return 0;
        } else if ((x < o.getX()) || (y < o.getY()))
        {
            return -1;
        } else
        {
            return 1;
        }
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
