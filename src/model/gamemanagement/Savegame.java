package model.gamemanagement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Savegame class for saving game progress.
 *
 * @author Markus Mogck
 */
public class Savegame implements Serializable
{

    private final int number;
    private int level;
    private int experience;
    private TreeMap<Integer, HashMap<String, Boolean>> questProgress;

    public Savegame(int number,
                    int level,
                    int experience,
                    TreeMap<Integer, HashMap<String, Boolean>> questProgress)
    {
        this.number = number;
        this.level = level;
        this.experience = experience;
        this.questProgress = questProgress;
    }

    public float getDifficultyMultiplier()
    {
        return 0.7f + (level * 0.1f);
    }

    public int getNumber()
    {
        return this.number;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getExperience()
    {
        return experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public Map<Integer, HashMap<String, Boolean>> getQuestProgress()
    {
        return questProgress;
    }

    public void setQuestProgress(
            TreeMap<Integer, HashMap<String, Boolean>> questProgress)
    {
        this.questProgress = questProgress;
    }
}
