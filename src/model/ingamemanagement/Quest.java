package model.ingamemanagement;

import control.gamemanagement.QuestController;
import control.map.MapController;
import java.util.ArrayList;
import model.figure.Enemy;
import model.figure.Hero;
import model.map.GameBoard;

/**
 * Quest Class with all the important informations about the Quest. This class
 * can be sent to other computers playing in the same multiplayer lobby.
 *
 * @author Markus Mogck
 */
public class Quest
{

    private int questNumber;
    private final String filePathToMap;

    private GameBoard gameBoard;

    private Hero activeHero;

    private Hero[] heroes;
    private ArrayList<Enemy> enemies;

    public Quest(int questNumber, Hero[] heroes)
    {
        this.questNumber = questNumber;
        this.filePathToMap = QuestController.getFilePathToQuestMap(questNumber);

        this.gameBoard = MapController.getNewGameBoard(this.filePathToMap);

        this.heroes = heroes;
        this.enemies = MapController.getEnemysFromJsonMap(this.filePathToMap);

        this.activeHero = heroes[0];
    }

    /**
     * Method to check if quest objective is achieved.
     *
     * @return true is quest objective is achieved
     */
    public boolean isQuestObjectiveAchieved()
    {
        return false;
    }

    //Getter and Setter
    public int getQuestNumber()
    {
        return questNumber;
    }

    public void setQuestNumber(int questNumber)
    {
        this.questNumber = questNumber;
    }

    public GameBoard getGameBoard()
    {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    public Hero[] getHeroes()
    {
        return heroes;
    }

    public void setHeroes(Hero[] heroes)
    {
        this.heroes = heroes;
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies)
    {
        this.enemies = enemies;
    }

    public Hero getActiveHero()
    {
        return activeHero;
    }

    public void setActiveHero(Hero activeHero)
    {
        this.activeHero = activeHero;
    }
}
