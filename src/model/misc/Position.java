package model.misc;

import resources.gameconstants.IGameConstants;

/**
 * Class for absolute Position. Variables 'x' and 'y' are not depending on Tiles
 * and Squares.
 *
 * @author Markus Mogck
 */
public class Position
{

    private int x;
    private int y;

    /**
     * Constructor of position with its x and y value.
     *
     * @param x
     * @param y
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of position with an given position.
     *
     * @param position
     */
    public Position(Position position)
    {
        this(position.getX(), position.getY());
    }

    /**
     * Converts position to tile position.
     *
     * @return
     */
    public Position toTilePosition()
    {
        return Position.getTilePositionFromPosition(this);
    }

    /**
     * Converts position to square position.
     *
     * @return
     */
    public Position toSquarePosition()
    {
        return getSquarePositionFromPosition(this);
    }

    /**
     * Returns square and tile independent position form tile and square
     * position.
     *
     * @param tilePosition
     * @param squarePosition
     * @return
     */
    public static Position
            getPositionFromTileAndSquarePosition(Position tilePosition,
                                                 Position squarePosition)
    {
        return getPositionFromTileAndSquarePosition(tilePosition.getX(),
                                                    tilePosition.getY(),
                                                    squarePosition.getX(),
                                                    squarePosition.getY());
    }

    /**
     * Returns square and tile independent position form tile and square
     * position.
     *
     * @param tile_x x value of tile position
     * @param tile_y y value of tile position
     * @param square_x x value of square position
     * @param square_y y value of square position
     * @return
     */
    public static Position getPositionFromTileAndSquarePosition(int tile_x,
                                                                int tile_y,
                                                                int square_x,
                                                                int square_y)
    {
        return new Position((tile_x * 5) + square_x,
                            (tile_y * 5) + square_y);
    }

    /**
     * Converts a position to its tile position.
     *
     * @param position
     * @return
     */
    public static Position getTilePositionFromPosition(Position position)
    {
        return getTilePositionFromPosition(position.getX(), position.getY());
    }

    /**
     * Converts a position to its tile position.
     *
     * @param x
     * @param y
     * @return
     */
    public static Position getTilePositionFromPosition(int x, int y)
    {
        return new Position((int) x / IGameConstants.GAMEBOARD_TILES_WIDTH,
                            (int) y / IGameConstants.GAMEBOARD_TILES_HEIGHT);
    }

    /**
     * Converts a position to its square position.
     *
     * @param position
     * @return
     */
    public static Position getSquarePositionFromPosition(Position position)
    {
        return Position.getSquarePositionFromPosition(
                position.getX(), position.getY());
    }

    /**
     * Converts a position to its square position.
     *
     * @param x
     * @param y
     * @return
     */
    public static Position getSquarePositionFromPosition(int x, int y)
    {
        Position tilePosition = getTilePositionFromPosition(x, y);

        return new Position(x - (tilePosition.getX()
                                 * IGameConstants.GAMEBOARD_SQUARES_WIDTH),
                            y - (tilePosition.getY()
                                 * IGameConstants.GAMEBOARD_SQUARES_HEIGHT));
    }

    /**
     * Returns a String with x and y value of the position.
     *
     * @return
     */
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
