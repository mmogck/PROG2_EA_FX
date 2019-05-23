package control.ingamemanagement;

import control.IOController;
import control.map.MapController;
import model.figure.Enemy;
import model.figure.Figure;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.map.GameBoard;
import model.misc.Position;

/**
 * Class for calculating movement radius and moving figures.
 *
 * @author Markus Mogck
 */
public class MoveController
{

    public static void initializeHeroMovement(Hero hero,
                                              Quest quest)
    {
        showMovementRadius(quest, hero);
        IOController.showGameBoard(quest);
        moveFigure(hero, getNewHeroPosition(quest.getGameBoard()));
        MapController.resetMarks(quest.getGameBoard());
    }

    private static Position getNewHeroPosition(GameBoard gameBoard)
    {
        int i = 0;
        Position newPosition = null;
        do
        {
            newPosition = IOController.getNewPositionInput();
            i++;
        } while (i < 3 && !isNewPositionOk(gameBoard, newPosition));
        return newPosition;
    }

    private static boolean isNewPositionOk(GameBoard gameBoard,
                                           Position position)
    {
        return gameBoard.getSquareFromPosition(position).isMarked();
    }

    private static void moveFigure(Figure figure, Position newPosition)
    {
        figure.setPosition(newPosition);
    }

    private static void showMovementRadius(Quest quest,
                                           Figure figure)
    {
        checkNextSquares(quest,
                         figure.getPosition(),
                         figure.getMovementPoints());
    }

    private static void checkSquare(Quest quest,
                                    Position position,
                                    int movementPoints)
    {
        GameBoard gameBoard = quest.getGameBoard();
        Position tilePosition = Position.getTileFromPosition(position);
        Position squarePosition = Position.getSquareFromPosition(position);

        if (!isSquareArlreadyChecked(gameBoard, movementPoints, tilePosition, squarePosition))
        {
            if (isSquareNotBlocked(quest, position))
            {
                if (isTileVisible(gameBoard, tilePosition))
                {
                    if (isMovementPointsOk(gameBoard,
                                           tilePosition,
                                           squarePosition,
                                           movementPoints))
                    {
                        setTrueAndCheckNext(quest,
                                            position,
                                            tilePosition,
                                            squarePosition,
                                            movementPoints);
                    }
                }
            }
        }
        //System.out.println(position);
    }

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

    private static boolean isSquareNotBlocked(Quest quest,
                                              Position position)
    {
        boolean blocked = false;

        for (Hero hero : quest.getHeroes())
        {
            if (!blocked
                && position.equals(hero.getPosition())
                && !position.equals(quest.getActiveHero().getPosition()))
            {
                blocked = true;
            }
        }
        for (Enemy enemy : quest.getEnemies())
        {
            if (!blocked
                && position.equals(enemy.getPosition()))
            {
                blocked = true;
            }
        }

        return !blocked;
    }

    private static boolean isTileVisible(GameBoard gameBoard,
                                         Position tilePosition)
    {
        return gameBoard.getTileAtTilePosition(tilePosition).isVisible();
    }

    private static boolean isMovementPointsOk(GameBoard gameBoard,
                                              Position tilePosition,
                                              Position squarePosition,
                                              int movementPoints)
    {
        return gameBoard.getSquareFromTileAndSquarePosition(tilePosition,
                                                            squarePosition)
                .getImpediment() <= movementPoints;
    }

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
