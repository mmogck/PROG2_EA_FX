package model.map;

import model.misc.Position;

/**
 * Class for the Tiles. A tile is part of the gameboard and contains scenery
 * squares.
 *
 * @author Markus Mogck
 */
public class Tile implements ISizedObject
{

    private Square[][] squares;
    private boolean visible;

    /**
     * Constructor of a tile.
     *
     * @param width horizontal squares
     * @param height vertical squares
     * @param visible is tile visible?
     */
    public Tile(int width, int height, boolean visible)
    {
        this.squares = new Square[width][height];
        this.visible = visible;
    }

    /**
     * Returns all squares in tile
     *
     * @return array of squares
     */
    public Square[][] getSquares()
    {
        return squares;
    }

    /**
     * Returns the square at a particular position in tile.
     *
     * @param squarePosition square position
     * @return
     */
    public Square getSquareAtSquarePosition(Position squarePosition)
    {
        return getSquareAtSquarePosition(squarePosition.getX(),
                                         squarePosition.getY());
    }

    /**
     * Returns the square at a particular position in tile.
     *
     * @param x x value of square position
     * @param y y value of square position
     * @return
     */
    public Square getSquareAtSquarePosition(int x, int y)
    {
        return squares[x][y];
    }

    /**
     * Sets all squares
     *
     * @param squares
     */
    public void setSquares(Square[][] squares)
    {
        this.squares = squares;
    }

    /**
     * Sets the square at a particular position.
     *
     * @param newSquare new square
     * @param position new position
     */
    public void setSquareAt(Square newSquare, Position position)
    {
        setSquareAt(newSquare, position.getX(), position.getY());
    }

    /**
     * Sets the square at a particular position.
     *
     * @param newSquare new square
     * @param x x value of the position
     * @param y y value of the position
     */
    public void setSquareAt(Square newSquare, int x, int y)
    {
        this.squares[x][y] = newSquare;
    }

    /**
     * Returns the visibility of the square
     *
     * @return
     */
    public boolean isVisible()
    {
        return this.visible;
    }

    /**
     * Sets the squares visibility
     *
     * @param visible
     */
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    /**
     * Returns the tile as a multiline string.
     *
     * @return String
     */
    @Override
    public String toString()
    {
        String string = "";
        for (int i = 0; i < this.getHeight(); i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if (this.visible)
                {
                    string += (this.squares[j][i].getSymbol() + " ");
                } else
                {
                    string += " ? ";
                }
            }
            string += "\n";
        }

        return string;
    }

    @Override
    public int getWidth()
    {
        return squares.length;
    }

    @Override
    public int getHeight()
    {
        return squares[0].length;
    }
}
