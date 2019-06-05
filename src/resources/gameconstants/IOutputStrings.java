package resources.gameconstants;

/**
 * 
 * @author Markus Mogck
 */
public interface IOutputStrings
{
    //Darstellungen für die Karte in der Konsole
    public final char CONSOLE_MAPOUTPUT_HERO = 'H';
    public final char CONSOLE_MAPOUTPUT_ENEMY = 'E';
    public final char CONSOLE_MAPOUTPUT_ROCK = 'R';
    public final char CONSOLE_MAPOUTPUT_FIELD = 'F';
    public final char CONSOLE_MAPOUTPUT_TREE = 'T';
    public final char CONSOLE_MAPOUTPUT_WATER = 'W';
    
    //Nachrichten, wenn das Programm etwas getan hat
    public final String MESSAGE_SAVEGAME_CREATED = "Savegame wurde erstellt.";
    public final String MEESAGE_SAVEGAME_SELECTED
                        = "Savegame wurde ausgewählt.";

    //Nachrichten für Informationsausgaben
    public final String MESSAGE_LVL = "Level:\t\t";
    public final String MESSAGE_EXP = "Erfahrung:\t";
    public final String MESSAGE_HERO_POS = "Position vom Held beträgt: ";
    public final String MESSAGE_MONSTER_POS = "Position vom Monster beträgt: ";
    public final String MESSAGE_BLOCK = "Block wurde entdeckt an Position: ";
    public final String MESSAGE_ACTIONPOINTS = "Action-Points left: ";

    //Nachrichten, wenn eine Eingabe erwartet wird
    public final String MESSAGE_INPUT = "Eingabe tätigen:";
    public final String MESSAGE_SELECTSAVEGAME
                        = "Spielstand wählen (1, 2, 3):";
    public final String MESSAGE_SELECTQUESTTOPLAY
                        = "Quest Auswählen (1, 2, 3, 4, 5):";
    public final String MESSAGE_SELECT_HERO_X_POS
                        = "Bitte X-Position vom Helden wählen:";
    public final String MESSAGE_SELECT_HERO_Y_POS
                        = "Bitte Y-Position vom Helden wählen:";
    
    //Text fuer Buttons
    public final String BUTTON_ACTION_ATTACK = "Angreifen";
    public final String BUTTON_ACTION_MOVE = "Bewegen";

    //Nachrichten, wenn Fehler aufgetreten sind
    public final String ERROR_UNEXPECTED_ERROR
                        = "Es ist ein unerwarteter Fehler aufgetreten.";
    public final String ERROR_WRONGINPUT = "Eingabe ungueltig.";
    public final String ERROR_NO_SAVEGAME_SELECTED
                        = "Kein Spielstand ausgewählt.";
    public final String ERROR_QUEST_NOT_UNLOCKED
                        = "Diese Quest ist noch nicht freigeschaltet.";
    public final String ERROR_POS_NOT_REACHABLE
                        = "Held kann die Position nicht erreichen.";
    public final String ERROR_ATTACK_FAILED = "Angriff fehlgeschlagen.";

    //Nachrichten, wenn Ausnahmen aufgetreten sind
    public final String MESSAGE_INVALIDQUESTNUMBEREXCEPTION
                        = "Quest-Nummer nicht erlaubt.";
    public final String MESSAGE_INVALIDSAVEGAMENUMBEREXCEPTION
                        = "Spielstandnummer nicht erlaubt.";

    //Befehle
    public final String COMMAND_EXIT = "~exit";
    public final String COMMAND_SELECTSAVEGAME = "~selectSavegame";
    public final String COMMAND_SAVEGAME = "~saveGame";
    public final String COMMAND_SELECTQUEST = "~selectQuest";
    public final String COMMAND_SHOWSTATS = "~showStats";
    public final String COMMAND_SHOWQUESTS = "~showQuests";
    public final String COMMAND_EXITQUEST = "~exitQuest";
    public final String COMMAND_TEST = "~test";

    public final String COMMAND_STARTHEROPHASE = "~startHeroPhase";
    public final String COMMAND_MOVEPLAYER = "~movePlayer";
    public final String COMMAND_ = "";
    
    //Placeholder
    public final String PLACEHOLDER_FILLED = "---";
    public final String PLACEHOLDER_EMPTY = "";
}
