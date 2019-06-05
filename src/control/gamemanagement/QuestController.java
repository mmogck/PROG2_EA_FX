package control.gamemanagement;

import control.ingamemanagement.GameLoop;
import java.util.HashMap;
import java.util.Map;
import model.figure.Hero;
import model.gamemanagement.Savegame;
import model.ingamemanagement.PlayerHeap;
import model.ingamemanagement.Quest;
import model.items.Equipment;
import model.misc.Dice;
import model.misc.Position;
import resources.gameconstants.IFileConstants;
import resources.gameconstants.IGameConstants;
import view.ESprites;

/**
 * Holds the gameloop of the active quest.
 *
 * @author Markus Mogck
 */
public class QuestController
{

    private static GameLoop activeGameLoop = null;

    /**
     * Starts specific quest by its number.
     *
     * @param questNumber Number of the quest to be started.
     */
    public static void startQuest(int questNumber)
    {
        Quest quest = new Quest(questNumber, initializeHeroes());

        activeGameLoop = new GameLoop(quest);

        activeGameLoop.firstExecutePhase();
    }

    /**
     * Initializes 4 heroes with their stats and equipment.
     *
     * @return Array with the heroes
     */
    public static Hero[] initializeHeroes()
    {
        //Werte vom Held anpassen !!!!!!!!!!!!!

        Hero[] heroesToSort = new Hero[4];
        heroesToSort[0] = new Hero(100, 5, 5, 3, new Position(11, 11),
                                   new Equipment(), ESprites.HERO.getImage());
        heroesToSort[1] = new Hero(100, 5, 5, 4, new Position(11, 13),
                                   new Equipment(), ESprites.HERO.getImage());
        heroesToSort[2] = new Hero(100, 5, 5, 5, new Position(13, 11),
                                   new Equipment(), ESprites.HERO.getImage());
        heroesToSort[3] = new Hero(100, 5, 5, 6, new Position(13, 13),
                                   new Equipment(), ESprites.HERO.getImage());

        PlayerHeap heap = new PlayerHeap(4);

        for (int i = 0; i < heroesToSort.length; i++)
        {
            heap.add(heroesToSort[i]);
        }

        for (int i = 0; i < heroesToSort.length; i++)
        {
            heroesToSort[i] = (Hero) heap.get();
        }

        return heroesToSort;
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
            //Erfahrung, Gegenstände, Spielstand aktualisieren
        } else
        {

        }

        activeGameLoop = null;
    }

    /**
     * Returns a random Map for a specific quest (by number).
     *
     * @param questNumber
     * @return Filepath as String
     */
    public static String getFilePathToQuestMap(int questNumber)
    {
        //getRandomDiceInteger von 1 bis 3 (da 3 Maps pro Quest verfuegbar)
        switch (questNumber)
        {
            case IGameConstants.QUEST_1_INT:
                switch (Dice.getRandomInteger(1, 3))
                {
                    case 1:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 2:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 3:
                        return IFileConstants.FILE_PATH_Q1_M1;
                }
                break;
            case IGameConstants.QUEST_2_INT:
                switch (Dice.getRandomInteger(1, 3))
                {
                    case 1:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 2:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 3:
                        return IFileConstants.FILE_PATH_Q1_M1;
                }
                break;
            case IGameConstants.QUEST_3_INT:
                switch (Dice.getRandomInteger(1, 3))
                {
                    case 1:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 2:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 3:
                        return IFileConstants.FILE_PATH_Q1_M1;
                }
                break;
            case IGameConstants.QUEST_4_INT:
                switch (Dice.getRandomInteger(1, 3))
                {
                    case 1:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 2:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 3:
                        return IFileConstants.FILE_PATH_Q1_M1;
                }
                break;
            case IGameConstants.QUEST_5_INT:
                switch (Dice.getRandomInteger(1, 3))
                {
                    case 1:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 2:
                        return IFileConstants.FILE_PATH_Q1_M1;
                    case 3:
                        return IFileConstants.FILE_PATH_Q1_M1;
                }
                break;
        }
        return null;
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
                //Einer der Schwierigkeitsstufen wurde geschafft
                boolean questDone = false;
                int previousQuestNumber = number - 1;

                for (Map.Entry<String, Boolean> entry : savegame
                        .getQuestProgress()
                        .get(previousQuestNumber)
                        .entrySet())
                {
                    if (entry.getValue() == true)
                    {
                        questDone = true;
                    }
                }

                return questDone;
            } else
            {
                //Quest 1 ist immer freigeschaltet.
                return true;
            }
        }
    }

    public static GameLoop getActiveGameLoop()
    {
        return activeGameLoop;
    }

    public static void setActiveGameLoop(GameLoop activeGameLoop)
    {
        QuestController.activeGameLoop = activeGameLoop;
    }
}
