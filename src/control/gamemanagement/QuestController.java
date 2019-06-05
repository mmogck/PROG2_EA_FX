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

/**
 * Holds the active Quest.
 *
 * @author Markus Mogck
 */
public class QuestController
{

    private static GameLoop activeGameLoop = null;

    public static void startQuest(int questNumber)
    {
        Quest quest = new Quest(questNumber, initializeHeroes());

        activeGameLoop = new GameLoop(quest);
        
        activeGameLoop.firstExecutePhase();
    }

    public static Hero[] initializeHeroes()
    {
        //Werte vom Held anpassen !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        Hero[] heroesToSort = new Hero[4];
        heroesToSort[0] = new Hero(100, 5, 5, 3, new Position(11, 11), new Equipment());
        heroesToSort[1] = new Hero(100, 5, 5, 4, new Position(11, 13), new Equipment());
        heroesToSort[2] = new Hero(100, 5, 5, 5, new Position(13, 11), new Equipment());
        heroesToSort[3] = new Hero(100, 5, 5, 6, new Position(13, 13), new Equipment());

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

    public static String getFilePathToQuestMap(int questNumber)
    {
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

    public static Map<Integer, Boolean> getUnlockedQuests(Savegame savegame)
    {
        HashMap<Integer, Boolean> unlockedQuestsMap = new HashMap<>();
        for (int i = 1; i <= IGameConstants.QUEST_COUNT; i++)
        {
            unlockedQuestsMap.put(i, isQuestUnlocked(i, savegame));
        }
        return unlockedQuestsMap;
    }

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
