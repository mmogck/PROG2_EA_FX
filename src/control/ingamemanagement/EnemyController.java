package control.ingamemanagement;

import java.util.ArrayList;
import model.figure.Enemy;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.map.ESquare;
import model.misc.Position;

/**
 * Controls the actions of enemies.
 *
 * @author Markus Mogck
 */
public class EnemyController
{

    public static void moveEnemyToFocusedHero(Enemy enemy,
                                              Quest quest)
    {
        enemy.setPosition(getPositionAtFocusedHero(enemy.getFocusedHero(),
                                                   enemy,
                                                   quest));
    }

    private static Position getPositionAtFocusedHero(Hero focusedHero,
                                                     Enemy enemy,
                                                     Quest quest)
    {
        int x = focusedHero.getPosition().getX();
        int y = focusedHero.getPosition().getY();

        if (checkPossiblePosition(new Position(x + 1, y), enemy, quest))
        {
            return new Position(x + 1, y);
        } else if (checkPossiblePosition(new Position(x - 1, y), enemy, quest))
        {
            return new Position(x - 1, y);
        } else if (checkPossiblePosition(new Position(x, y + 1), enemy, quest))
        {
            return new Position(x, y + 1);
        } else if (checkPossiblePosition(new Position(x, y - 1), enemy, quest))
        {
            return new Position(x, y - 1);
        }

        return enemy.getPosition();
    }

    private static boolean checkPossiblePosition(Position position,
                                                 Enemy activeEnemy,
                                                 Quest quest)
    {
        boolean reachable = true;

        if (quest.getGameBoard().getSquareFromPosition(position).getESquare()
            == ESquare.ROCK)
        {
            reachable = false;
        }

        for (Hero hero : quest.getHeroes())
        {
            if (reachable)
            {
                reachable = !position.equals(hero.getPosition());
            }
        }

        for (Enemy enemy : quest.getEnemies())
        {
            if (reachable && enemy != activeEnemy)
            {
                reachable = !position.equals(enemy.getPosition());
            }
        }

        return reachable;
    }

    public static void setEnemiesActiveAndGiveFocusedHero(Quest quest,
                                                          Position tilePosition)
    {
        for (Enemy enemy : getAllEnemiesAtTile(tilePosition, quest))
        {
            enemy.setActive(true);
            enemy.setFocusedHero(quest.getActiveHero());
        }
    }

    private static ArrayList<Enemy> getAllEnemiesAtTile(Position tilePosition,
                                                        Quest quest)
    {
        ArrayList<Enemy> enemiesAtTile = new ArrayList<>();

        for (Enemy enemy : quest.getEnemies())
        {
            if (Position.getTileFromPosition(enemy.getPosition())
                    .equals(tilePosition))
            {
                enemiesAtTile.add(enemy);
            }
        }

        return enemiesAtTile;
    }
}
