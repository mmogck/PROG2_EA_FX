package control.ingamemanagement;

import control.IOController;
import control.map.MapController;
import model.figure.Enemy;
import model.figure.Figure;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.map.GameBoard;
import model.misc.Position;
import resources.gameconstants.IOutputStrings;
import view.IOColor;

/**
 * Class for calculating movement radius and moving figures.
 *
 * @author Markus Mogck
 */
public class MoveController
{

    /**
     * Initializes the hero movement. For that the movement radius gets
     * calculated and shown.
     *
     * @param hero active hero
     * @param quest active quest
     */
    public static void initializeHeroMovement(Hero hero,
                                              Quest quest)
    {
        showMovementRadius(quest, hero);
        IOController.printGameBoard(quest);
    }

    /**
     * Starts the hero movement. For that the new position specified by the user
     * gets checked. If its OK the hero moves to the new position.
     *
     * @param hero hero to be moved
     * @param quest active quest
     * @param position position input
     */
    public static void startHeroMovement(Hero hero,
                                         Quest quest,
                                         Position position)
    {
        if (isNewPositionOk(quest.getGameBoard(), position))
        {
            moveFigure(hero, position);
            resetHeroMovement(quest);
        } else
        {
            IOController.printMessage(IOutputStrings.ERROR_POS_NOT_REACHABLE,
                                      IOColor.FONT_RED);
        }

    }

    /**
     * Resets the hero movement, For that the marks are getting resetted.
     *
     * @param quest active quest
     */
    public static void resetHeroMovement(Quest quest)
    {
        MapController.resetMarks(quest.getGameBoard());
        IOController.printGameBoard(quest);
    }

    /**
     * Checks if the given position is marked.
     *
     * @param gameBoard active gameboard
     * @param position position to be checked
     * @return true - position is marked / ok
     */
    private static boolean isNewPositionOk(GameBoard gameBoard,
                                           Position position)
    {
        return gameBoard.getSquareFromPosition(position).isMarked();
    }

    /**
     * Moves the given figure.
     *
     * @param figure figure to be moved
     * @param newPosition new position
     */
    private static void moveFigure(Figure figure, Position newPosition)
    {
        figure.setPosition(newPosition);
    }

    /**
     * Calculates the movement radius of a figure. This is the root of a
     * rercursive method.
     *
     * @param quest active quest
     * @param figure figure whose radius should be checked
     */
    private static void showMovementRadius(Quest quest,
                                           Figure figure)
    {
        checkNextSquares(quest,
                         figure.getPosition(),
                         figure.getMovementPoints());
    }

    /**
     * Checks a single Square if its still in movement radius. Things that are
     * getting checked are if its already checked, if its blocked, if its
     * visible and if there are enough movementpoints left.
     *
     * @param quest active quest
     * @param position position to be checked
     * @param movementPoints movementpoints left at this position
     */
    private static void checkSquare(Quest quest,
                                    Position position,
                                    int movementPoints)
    {
        GameBoard gameBoard = quest.getGameBoard();
        Position tilePosition = Position.getTileFromPosition(position);
        Position squarePosition = Position.getSquareFromPosition(position);

        if (!isSquareArlreadyChecked(gameBoard, movementPoints,
                                     tilePosition, squarePosition))
        {
            if (isSquareNotBlocked(quest, position))
            {
                if (isTileVisible(gameBoard, tilePosition))
                {
                    if (isMovementPointsOk(gameBoard, tilePosition,
                                           squarePosition, movementPoints))
                    {
                        setTrueAndCheckNext(quest, position, tilePosition,
                                            squarePosition, movementPoints);
                    }
                }
            }
        }
    }

    /**
     * After a square got checked and is reachable its getting marked and the
     * movementpoints to reach this square are getting saved. This is necessary
     * because the same square could be reached from different directions and we
     * want to check the next squares with the least movementpoints needed.
     *
     * @param quest active quest
     * @param position current position
     * @param tilePosition current tile position
     * @param squarePosition current square position
     * @param movementPoints movementpoints that are left
     */
    private static void setTrueAndCheckNext(Quest quest,
                                            Position position,
                                            Position tilePosition,
                                            Position squarePosition,
                                            int movementPoints)
    {
        //Berechnen der neuen Bewegungspunkte
        int newMovementPoints = getNewMovementPoints(movementPoints,
                                                     quest.getGameBoard(),
                                                     tilePosition,
                                                     squarePosition);

        //Aktuelles Square markieren
        quest.getGameBoard().getSquareFromTileAndSquarePosition(tilePosition,
                                                                squarePosition)
                .setMarked(true);

        //Bewegungspunkte, um auf aktuelles Square zu gelangen aendern
        quest.getGameBoard().getSquareFromTileAndSquarePosition(tilePosition,
                                                                squarePosition)
                .setMovementPointsToSubtract(newMovementPoints);

        //Naechste, umliegende Squares ueberpruefen
        checkNextSquares(quest, position, newMovementPoints);
    }

    /**
     * If a square got checked and was reachable, the 4 next squares on top,
     * right, bottom and left are getting checked wether they are reachable.
     *
     * @param quest active quest
     * @param currentPosition current position
     * @param movementPoints movementpoints that are left
     */
    private static void checkNextSquares(Quest quest,
                                         Position currentPosition,
                                         int movementPoints)
    {
        //Pruefen der Positionen rechts, links, unten, oben
        checkSquare(quest,
                    new Position(currentPosition.getX() + 1,
                                 currentPosition.getY()),
                    movementPoints);
        checkSquare(quest,
                    new Position(currentPosition.getX() - 1,
                                 currentPosition.getY()),
                    movementPoints);
        checkSquare(quest,
                    new Position(currentPosition.getX(),
                                 currentPosition.getY() + 1),
                    movementPoints);
        checkSquare(quest,
                    new Position(currentPosition.getX(),
                                 currentPosition.getY() - 1),
                    movementPoints);
    }

    /**
     * Checks wether a square is already checked and the movementpoints to reach
     * this square were less before.
     *
     * @param gameBoard active gameboard
     * @param movementPoints movementpoints that
     * @param tilePosition tileposition to be checked
     * @param squarePosition squareposition to be checked
     * @return true if checked with less movementpoints
     */
    private static boolean isSquareArlreadyChecked(GameBoard gameBoard,
                                                   int movementPoints,
                                                   Position tilePosition,
                                                   Position squarePosition)
    {
        int newMovementPoints = getNewMovementPoints(movementPoints,
                                                     gameBoard,
                                                     tilePosition,
                                                     squarePosition);

        return gameBoard.getSquareFromTileAndSquarePosition(tilePosition,
                                                            squarePosition)
                .isMarked()
               && (newMovementPoints
                   < gameBoard
                   .getSquareFromTileAndSquarePosition(tilePosition,
                                                       squarePosition)
                   .getMovementPointsToSubtract());

    }

    /**
     * Checks wether a square is blocked by enemy or hero.
     *
     * @param quest active quest
     * @param position position to be checked
     * @return true if square is NOT blocked
     */
    private static boolean isSquareNotBlocked(Quest quest,
                                              Position position)
    {
        boolean blocked = false;

        for (Hero hero : quest.getHeroes())
        {
            if (!blocked
                && hero.isAlive()
                && position.equals(hero.getPosition())
                && !position.equals(quest.getActiveHero().getPosition()))
            {
                blocked = true;
            }
        }
        for (Enemy enemy : quest.getEnemies())
        {
            if (!blocked
                && enemy.isAlive()
                && position.equals(enemy.getPosition()))
            {
                blocked = true;
            }
        }

        return !blocked;
    }

    /**
     * Checks wether a tile is already visible.
     *
     * @param gameBoard current gameboard
     * @param tilePosition tileposition to be checked
     * @return true if visible
     */
    private static boolean isTileVisible(GameBoard gameBoard,
                                         Position tilePosition)
    {
        return gameBoard.getTileAtTilePosition(tilePosition).isVisible();
    }

    /**
     * Checks wether there are enough movementpoints left to reach the given
     * position.
     *
     * @param gameBoard current gameboard
     * @param tilePosition tileposition to check
     * @param squarePosition squareposition to check
     * @param movementPoints movementpoints that are left
     * @return
     */
    private static boolean isMovementPointsOk(GameBoard gameBoard,
                                              Position tilePosition,
                                              Position squarePosition,
                                              int movementPoints)
    {
        return gameBoard.getSquareFromTileAndSquarePosition(tilePosition,
                                                            squarePosition)
                .getImpediment() <= movementPoints;
    }

    /**
     * Returns the new movement points after going to a ne square.
     *
     * @param movementPoints current movementpoints
     * @param gameBoard active gameboard
     * @param tilePosition current tile position
     * @param squarePosition current square position
     * @return int value of new movementpoints
     */
    private static int getNewMovementPoints(int movementPoints,
                                            GameBoard gameBoard,
                                            Position tilePosition,
                                            Position squarePosition)
    {
        return movementPoints
               - gameBoard
                .getSquareFromTileAndSquarePosition(tilePosition,
                                                    squarePosition)
                .getImpediment();
    }
}
