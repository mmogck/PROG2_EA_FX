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

    /**
     * Moves an enemy to its focused hero.
     *
     * @param enemy enemy to be moved
     * @param quest active quest
     */
    public static void moveEnemyToFocusedHero(Enemy enemy,
                                              Quest quest)
    {
        enemy.setPosition(getPositionAtFocusedHero(enemy.getFocusedHero(),
                                                   enemy,
                                                   quest));
    }

    /**
     * Returns the new position of the enemy to be moved. Therefor the Positions
     * on the right, left, bottom and top of the focused hero are checked for
     * obstacles and the first possible position gets returned.
     *
     * @param focusedHero
     * @param enemy enemy to be moved
     * @param quest active quest
     * @return
     */
    private static Position getPositionAtFocusedHero(Hero focusedHero,
                                                     Enemy enemy,
                                                     Quest quest)
    {
        int x = focusedHero.getPosition().getX();
        int y = focusedHero.getPosition().getY();

        if (checkPossiblePosition(new Position(x + 1, y), enemy, quest))
        {
            return new Position(x + 1, y);
        } else if (checkPossiblePosition(new Position(x - 1, y),
                                         enemy, quest))
        {
            return new Position(x - 1, y);
        } else if (checkPossiblePosition(new Position(x, y + 1),
                                         enemy, quest))
        {
            return new Position(x, y + 1);
        } else if (checkPossiblePosition(new Position(x, y - 1),
                                         enemy, quest))
        {
            return new Position(x, y - 1);
        }

        return enemy.getPosition();
    }

    /**
     * New possible position for the enemy is checked for obstacles.
     *
     * @param position position to be checked
     * @param activeEnemy active enemy
     * @param quest active quest
     * @return
     */
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

        reachable = checkHeroes(quest, position, reachable);

        reachable = checkEnemies(quest, position, activeEnemy, reachable);

        return reachable;
    }

    /**
     * Checks if new position of enemy is blocked by a hero.
     *
     * @param quest active quest
     * @param position new position
     * @param reachable current state of reachability
     * @return
     */
    private static boolean checkHeroes(Quest quest, Position position,
                                       boolean reachable)
    {
        boolean check = reachable;
        for (Hero hero : quest.getHeroes())
        {
            if (check)
            {
                check = !position.equals(hero.getPosition());
            }
        }
        return check;
    }

    /**
     * Checks if new position of enemy is blocked by a hero.
     *
     * @param quest active quest
     * @param position new position
     * @param activeEnemy active enemy
     * @param reachable current state of reachability
     * @return
     */
    private static boolean checkEnemies(Quest quest, Position position,
                                        Enemy activeEnemy, boolean reachable)
    {
        boolean check = reachable;
        for (Enemy enemy : quest.getEnemies())
        {
            if (check && enemy != activeEnemy)
            {
                check = !position.equals(enemy.getPosition());
            }
        }
        return check;
    }

    /**
     * If an new tile gets explored, all enemies on this tile are getting active
     * and focus a hero.
     *
     * @param quest active quest
     * @param tilePosition new tile
     */
    public static void setEnemiesActiveAndGiveFocusedHero(Quest quest,
                                                          Position tilePosition)
    {
        for (Enemy enemy : getAllEnemiesAtTile(tilePosition, quest))
        {
            enemy.setActive(true);
            enemy.setFocusedHero(quest.getActiveHero());
        }
    }

    /**
     * Returns all enemies on a tile.
     *
     * @param tilePosition
     * @param quest
     * @return Array List with all enemies at this tile
     */
    private static ArrayList<Enemy> getAllEnemiesAtTile(Position tilePosition,
                                                        Quest quest)
    {
        ArrayList<Enemy> enemiesAtTile = new ArrayList<>();

        for (Enemy enemy : quest.getEnemies())
        {
            if (Position.getTilePositionFromPosition(enemy.getPosition())
                    .equals(tilePosition))
            {
                enemiesAtTile.add(enemy);
            }
        }

        return enemiesAtTile;
    }
}
