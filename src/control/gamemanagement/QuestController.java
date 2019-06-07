package control.gamemanagement;

import control.ingamemanagement.GameLoop;
import java.util.HashMap;
import java.util.Map;
import model.figure.EHero;
import model.figure.Enemy;
import model.figure.Hero;
import model.gamemanagement.Savegame;
import model.ingamemanagement.PlayerHeap;
import model.ingamemanagement.Quest;
import model.map.Tile;
import model.misc.Dice;
import model.misc.EDifficulty;
import resources.gameconstants.IFileConstants;
import resources.gameconstants.IGameConstants;

/**
 * Holds the gameloop of the active quest.
 *
 * @author Markus Mogck
 */
public class QuestController
{

    private static Quest activeQuest = null;

    private static Hero[] selectedHeroes = null;

    /**
     * Starts specific quest by its number.
     *
     * @param questNumber Number of the quest to be started.
     * @param difficulty
     */
    public static void startQuest(int questNumber, EDifficulty difficulty)
    {
        activeQuest = new Quest(questNumber,
                                difficulty,
                                initializeHeroes(),
                                new GameLoop());
        
        activeQuest.getGameLoop().setActiveQuest(activeQuest);
        
        activeQuest.getGameLoop().firstExecutePhase();
    }

    /**
     * Initializes the heroes with their stats and equipment.
     *
     * @return Array with the heroes
     */
    public static Hero[] initializeHeroes()
    {
        //Anpassungsmoeglichkeit durch Spieler hinzufuegen

        Hero[] heroesToSort = new Hero[IGameConstants.HERO_COUNT];
        heroesToSort[0] = new Hero(EHero.HERO1);
        heroesToSort[1] = new Hero(EHero.HERO2);
        heroesToSort[2] = new Hero(EHero.HERO3);
        heroesToSort[3] = new Hero(EHero.HERO4);

        /*
         * Sortieren der Helden nach Bewegungspunkten (spaeter durch
         * Initiative) und Rueckgabe an die Quest
         */
        PlayerHeap playerHeap = new PlayerHeap(IGameConstants.HERO_COUNT);

        for (int i = 0; i < heroesToSort.length; i++)
        {
            playerHeap.add(heroesToSort[i]);
        }

        for (int i = 0; i < heroesToSort.length; i++)
        {
            heroesToSort[i] = (Hero) playerHeap.get();
        }

        return heroesToSort;
    }

    public static void selectHero(EHero heroType)
    {

    }

    public static void removeHero(EHero heroType)
    {

    }

    /**
     * Method for ending the Quest. Saves progress if quest ended successfully.
     *
     * @param successfully
     */
    public static void endQuest(boolean successfully)
    {
        if (successfully)
        {
            //
        } else
        {
            //
        }

        activeQuest = null;
    }

    public static boolean isQuestObjectiveAchieved(Quest quest)
    {
        switch (quest.getQuestNumber())
        {
            case 1:
                return isAllTilesExplored(quest);
            case 2:
                return isAllEnemiesDead(quest);
            case 3:
                return isAllEnemiesDead(quest);
            case 4:
                return isAllEnemiesDead(quest);
            case 5:
                return isAllEnemiesDead(quest);
            default:
                return isAllEnemiesDead(quest);
        }
    }

    private static boolean isAllEnemiesDead(Quest quest)
    {
        boolean allEnemiesDead = true;

        for (Enemy enemy : quest.getEnemies())
        {
            if (allEnemiesDead && !enemy.isAlive())
            {
                allEnemiesDead = false;
            }
        }
        return allEnemiesDead;
    }

    private static boolean isAllTilesExplored(Quest quest)
    {
        boolean allTilesExplored = true;

        for (Tile tile : quest.getGameBoard().getTilesList())
        {
            if (allTilesExplored && !tile.isVisible())
            {
                allTilesExplored = false;
            }
        }

        return allTilesExplored;
    }

    /**
     * Returns a random Map for a specific quest (by number).
     *
     * @param questNumber
     * @return Filepath as String
     */
    public static String getFilePathToQuestMap(int questNumber)
    {
        switch (questNumber)
        {
            case IGameConstants.QUEST_1_INT:
                return getRandomMap(IFileConstants.FILE_PATH_Q1_M1,
                                    IFileConstants.FILE_PATH_Q1_M2,
                                    IFileConstants.FILE_PATH_Q1_M3);
            case IGameConstants.QUEST_2_INT:
                return getRandomMap(IFileConstants.FILE_PATH_Q2_M1,
                                    IFileConstants.FILE_PATH_Q2_M2,
                                    IFileConstants.FILE_PATH_Q2_M3);
            case IGameConstants.QUEST_3_INT:
                return getRandomMap(IFileConstants.FILE_PATH_Q3_M1,
                                    IFileConstants.FILE_PATH_Q3_M2,
                                    IFileConstants.FILE_PATH_Q3_M3);
            case IGameConstants.QUEST_4_INT:
                return getRandomMap(IFileConstants.FILE_PATH_Q4_M1,
                                    IFileConstants.FILE_PATH_Q4_M2,
                                    IFileConstants.FILE_PATH_Q4_M3);
            case IGameConstants.QUEST_5_INT:
                return getRandomMap(IFileConstants.FILE_PATH_Q5_M1,
                                    IFileConstants.FILE_PATH_Q5_M2,
                                    IFileConstants.FILE_PATH_Q5_M3);
        }
        return null;
    }

    /**
     * Returns filepath to one of the given filepaths.
     *
     * @param filepathMap1
     * @param filepathMap2
     * @param filepathMap3
     * @return one random filepath of the given ones
     */
    private static String getRandomMap(String filepathMap1,
                                       String filepathMap2,
                                       String filepathMap3)
    {
        //getRandomDiceInteger von 1 bis 3 (da 3 Maps pro Quest verfuegbar)
        switch (Dice.getRandomInteger(1, 3))
        {
            case 1:
                return filepathMap1;
            case 2:
                return filepathMap2;
            case 3:
                return filepathMap3;
            default:
                return filepathMap1;
        }
    }

    /**
     * Returns a map with quest as key and the status as value. If the status is
     * true, the quest is completed.
     *
     * @param savegame
     * @return
     */
    public static Map<Integer, Boolean> getUnlockedQuests(Savegame savegame)
    {
        HashMap<Integer, Boolean> unlockedQuestsMap = new HashMap<>();
        for (int i = 1; i <= IGameConstants.QUEST_COUNT; i++)
        {
            unlockedQuestsMap.put(i, isQuestUnlocked(i, savegame));
        }
        return unlockedQuestsMap;
    }

    /**
     * Method for checking if a quest is unlocked. For a quest to be unlocked
     * the previous quest must be completed.
     *
     * @param number
     * @param savegame
     * @return
     */
    public static boolean isQuestUnlocked(int number, Savegame savegame)
    {
        {
            if (number != 1)
            {
                return isQuestBeforeDone(number, savegame);
            } else
            {
                //Quest 1 ist immer freigeschaltet.
                return true;
            }
        }
    }

    /**
     * Checks wether the previous quest is done.
     *
     * @param number current quest number
     * @param savegame active savegame
     * @return true if previous quest is done
     */
    private static boolean isQuestBeforeDone(int number, Savegame savegame)
    {
        //Einer der Schwierigkeitsstufen wurde geschafft
        boolean questDone = false;

        for (Map.Entry<String, Boolean> entry : savegame
                .getQuestProgress()
                //Nummer der vorherigen Quest
                .get(number - 1)
                .entrySet())
        {
            if (entry.getValue() == true)
            {
                questDone = true;
            }
        }

        return questDone;
    }

    public static Quest getActiveQuest()
    {
        return activeQuest;
    }

    public Hero[] getSelectedHeroes()
    {
        return selectedHeroes;
    }

    public void setSelectedHeroes(Hero[] playerHeap)
    {
        this.selectedHeroes = playerHeap;
    }
}
