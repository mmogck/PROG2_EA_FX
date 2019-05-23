package model.map;

import model.misc.Position;

/**
 * Class for the Tiles.
 *
 * @author Markus Mogck
 */
public class Tile implements ISizedObject
{
    private Square[][] squares;
    private boolean visible;

    public Tile(int width, int height, boolean visible)
    {
        this.squares = new Square[width][height];
        this.visible = visible;
    }

    public Square[][] getSquares()
    {
        return squares;
    }

    public Square getSquareAtSquarePosition(Position squarePosition)
    {
        return getSquareAtSquarePosition(squarePosition.getX(),
                                         squarePosition.getY());
    }

    public Square getSquareAtSquarePosition(int x, int y)
    {
        return squares[x][y];
    }

    public void setSquares(Square[][] squares)
    {
        this.squares = squares;
    }

    public void setSquareAt(Square newSquare, Position position)
    {
        setSquareAt(newSquare, position.getX(), position.getY());
    }

    public void setSquareAt(Square newSquare, int x, int y)
    {
        this.squares[x][y] = newSquare;
    }

    public boolean isVisible()
    {
        return this.visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

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
