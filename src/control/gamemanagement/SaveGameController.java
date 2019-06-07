package control.gamemanagement;

import control.IOController;
import exception.InvalidSavegameNumberException;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gamemanagement.Savegame;
import resources.gameconstants.IFileConstants;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IOutputStrings;
import view.IOColor;

/**
 * Manages saving and loading savegames and holds the active Savegame.
 *
 * @author Markus Mogck
 */
public class SaveGameController
{

    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    private static Savegame activeSavegame = null;

    /**
     * Selects and loads a savegame by its number. If there is no savegame for
     * the number a new one gets created.
     *
     * @param number Savegame number
     */
    public static void selectSavegame(int number)
    {
        if (isFileEmpty(new File(getFilePathToSavegameFile(number))))
        {
            IOController.printMessage(IOutputStrings.MESSAGE_SAVEGAME_CREATED,
                                      IOColor.FONT_GREEN);
            activeSavegame = createNewSavegame(number);
            saveGame(activeSavegame);
        } else
        {
            IOController.printMessage(IOutputStrings.MEESAGE_SAVEGAME_SELECTED,
                                      IOColor.FONT_GREEN);
            activeSavegame = loadGame(number);
        }
    }

    /**
     * Creates a new savegame.
     *
     * @param number
     * @return new savegame
     */
    private static Savegame createNewSavegame(int number)
    {
        HashMap<String, Boolean> difficultyMap = new HashMap<>();
        difficultyMap.put(IGameConstants.DIFFICULTY_EASY, false);
        difficultyMap.put(IGameConstants.DIFFICULTY_MEDIUM, false);
        difficultyMap.put(IGameConstants.DIFFICULTY_HARD, false);

        TreeMap<Integer, HashMap<String, Boolean>> questProgress
                                                   = new TreeMap<>();

        questProgress.put(IGameConstants.QUEST_1_INT, difficultyMap);
        questProgress.put(IGameConstants.QUEST_2_INT, difficultyMap);
        questProgress.put(IGameConstants.QUEST_3_INT, difficultyMap);
        questProgress.put(IGameConstants.QUEST_4_INT, difficultyMap);
        questProgress.put(IGameConstants.QUEST_5_INT, difficultyMap);

        return new Savegame(number, 0, 0, questProgress);
    }

    /**
     * Saves the currently loaded savegame.
     *
     * @param savegame
     */
    public static void saveGame(Savegame savegame)
    {
        try
        {
            fos = new FileOutputStream(
                    getFilePathToSavegameFile(savegame.getNumber()));
            oos = new ObjectOutputStream(fos);

            oos.writeObject(savegame);

        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                fos.close();
                oos.close();
            } catch (IOException ex)
            {
                Logger.getLogger(SaveGameController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Loads a savegame from its savegame file.
     *
     * @param filePath filepath to the savegame file
     * @return
     */
    public static Savegame loadGame(String filePath)
    {
        try
        {
            fis = new FileInputStream(filePath);
            ois = new ObjectInputStream(fis);

            return (Savegame) ois.readObject();

        } catch (EOFException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                fis.close();
                ois.close();
            } catch (IOException ex)
            {
                Logger.getLogger(SaveGameController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Loads a savegame from its savegame number.
     *
     * @param number savegame number
     * @return Savagame
     */
    public static Savegame loadGame(int number)
    {
        return loadGame(getFilePathToSavegameFile(number));
    }

    /**
     * Checks wether a savegame file is empty or not.
     *
     * @param file file to be checked
     * @return true if file is empty
     */
    private static boolean isFileEmpty(File file)
    {
        FileReader fr = null;
        try
        {
            File test = file;
            fr = new FileReader(test);
            if (fr.read() == -1)
            {
                fr.close();
                return true;
            }
            fr.close();
            return false;
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally
        {
            try
            {
                fr.close();
            } catch (IOException ex)
            {
                Logger.getLogger(SaveGameController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Returns the filepath to the savegame file by its number.
     *
     * @param number
     * @return String (filepath)
     */
    private static String getFilePathToSavegameFile(int number)
    {
        try
        {
            switch (number)
            {
                case 1:
                    return IFileConstants.FILE_PATH_SAVEGAME_1;
                case 2:
                    return IFileConstants.FILE_PATH_SAVEGAME_2;
                case 3:
                    return IFileConstants.FILE_PATH_SAVEGAME_3;
                default:
                    throw new InvalidSavegameNumberException(
                            IOutputStrings.EXCEPTION_INVALIDSAVEGAMENUMBER);
            }
        } catch (Exception ex)
        {
            Logger.getLogger(SaveGameController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Getter and Setter
    public static Savegame getActiveSavegame()
    {
        return activeSavegame;
    }

    public static void setActiveSavegame(Savegame activeSavegame)
    {
        SaveGameController.activeSavegame = activeSavegame;
    }
}
