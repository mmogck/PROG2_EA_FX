package resources.gameconstants;

import model.misc.Position;

/**
 * Interface with Constants for the Game.
 *
 * @author Markus Mogck
 */
public interface IGameConstants
{

    //Groeßen der Spielkarte

    public final int GAMEBOARD_TILES_WIDTH = 5;
    public final int GAMEBOARD_TILES_HEIGHT = 5;
    public final int GAMEBOARD_SQUARES_WIDTH = 5;
    public final int GAMEBOARD_SQUARES_HEIGHT = 5;

    //Konstanten der GameLoop
    public final String HERO_PHASE = "Hero-Phase";
    public final String EXPLORATION_PHASE = "Exploration-Phase";
    public final String EVENT_PHASE = "Event-Phase";
    public final String ENEMY_PHASE = "Enemy-Phase";

    //Symbole (Character) der einzelnen Umgebungsobjekte
    public final char ROCK_CHAR = 'R';
    public final char FIELD_CHAR = 'F';
    public final char TREE_CHAR = 'T';
    public final char WATER_CHAR = 'W';

    //Konstanten fuer die Speicherstände
    public final String DIFFICULTY_EASY = "Einfach";
    public final String DIFFICULTY_MEDIUM = "Mittel";
    public final String DIFFICULTY_HARD = "Schwer";

    public final int QUEST_COUNT = 5;

    public final int QUEST_1_INT = 1;
    public final int QUEST_2_INT = 2;
    public final int QUEST_3_INT = 3;
    public final int QUEST_4_INT = 4;
    public final int QUEST_5_INT = 5;

    //Konstanten für den Würfel
    public final int DICE_MIN_VALUE = 1;
    public final int DICE_MAX_VALUE = 20;

    //Sontige Konstanten
    public final int HERO_COUNT = 4;
    public final Position START_TILE_POSITIOM = new Position(2, 2);
}
