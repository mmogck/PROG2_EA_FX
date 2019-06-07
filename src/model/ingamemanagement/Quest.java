package model.ingamemanagement;

import control.gamemanagement.QuestController;
import control.ingamemanagement.GameLoop;
import control.map.MapController;
import java.util.ArrayList;
import model.figure.Enemy;
import model.figure.Hero;
import model.map.GameBoard;
import model.misc.EDifficulty;

/**
 * Quest Class with all the important informations about the Quest. This class
 * can be sent to other computers playing in the same multiplayer lobby.
 *
 * @author Markus Mogck
 */
public class Quest
{

    private final int questNumber;
    private final EDifficulty difficulty;
    private final String filePathToMap;

    private final GameLoop gameLoop;

    private final GameBoard gameBoard;

    private Hero activeHero;

    private Hero[] heroes;
    private ArrayList<Enemy> enemies;

    public Quest(int questNumber, EDifficulty difficulty,
                 Hero[] heroes, GameLoop gameLoop)
    {
        this.questNumber = questNumber;
        this.difficulty = difficulty;
        this.filePathToMap = QuestController.getFilePathToQuestMap(questNumber);

        this.gameLoop = gameLoop;

        this.gameBoard = MapController.getNewGameBoard(this.filePathToMap);

        this.heroes = heroes;
        this.enemies = MapController.getEnemysFromJsonMap(this.filePathToMap);

        this.activeHero = heroes[0];
    }

    /**
     * Method to check if quest objective is achieved.
     *
     * @return true if quest objective is achieved
     */
    public boolean isQuestObjectiveAchieved()
    {
        return QuestController.isQuestObjectiveAchieved(this);
    }

    /**
     * Calls Method to end the active quest.
     *
     * @param successfully true if quest objective is achieved
     */
    public void end(boolean successfully)
    {
        QuestController.endQuest(successfully);
    }

    //Getter and Setter
    public int getQuestNumber()
    {
        return this.questNumber;
    }

    public EDifficulty getDifficulty()
    {
        return this.difficulty;
    }

    public String getFilePathToMap()
    {
        return this.filePathToMap;
    }

    public GameLoop getGameLoop()
    {
        return this.gameLoop;
    }

    public GameBoard getGameBoard()
    {
        return this.gameBoard;
    }

    public Hero[] getHeroes()
    {
        return this.heroes;
    }

    public ArrayList<Enemy> getEnemies()
    {
        return this.enemies;
    }

    public Hero getActiveHero()
    {
        return this.activeHero;
    }

    public void setActiveHero(Hero activeHero)
    {
        this.activeHero = activeHero;
    }
}
