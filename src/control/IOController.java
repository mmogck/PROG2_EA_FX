package control;

import control.ingamemanagement.MoveController;
import control.map.MapController;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.misc.Position;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IOutputStrings;
import view.IOColor;

/**
 * Class for all Methods giving an output or requiring an input.
 *
 * @author Markus Mogck
 */
public class IOController
{

    //Methode für Informationsausgaben, kann für Konsole und GUI
    //verwendet werden
    public static void printMessage(String text)
    {
        printMessage(text, IOColor.FONT_BLACK);
    }

    public static void printMessage(String text, IOColor color)
    {
        IOConsoleController.printlnColor(color, text);
    }
    
    ///////////////////////////////////////////////////////////////////////////

    public static void printActivePhase(String activePhase, int actionPoints)
    {

        if (activePhase.equals(IGameConstants.HERO_PHASE))
        {
            IOConsoleController.printlnColor(IOColor.FONT_PURPLE,
                                             activePhase);
            IOConsoleController.printlnColor(IOColor.FONT_PURPLE,
                                             IOutputStrings.MESSAGE_ACTIONPOINTS
                                             + actionPoints);
        } else
        {
            IOConsoleController.printlnColor(IOColor.FONT_PURPLE,
                                             activePhase);
        }

    }
    
    public static void printHeroInfo(Hero activeHero)
    {
        IOConsoleController.printlnColor(IOColor.FONT_BLUE,
                                         activeHero.toString());
    }

    ///////////////////////////////////////////////////////////////////////////
    
    public static void selectSavegame()
    {

    }

    public static void deleteSavegame()
    {

    }

    public static void showOptions()
    {

    }

    public static void closeOptions()
    {

    }

    public static void showGameBoard(Quest quest)
    {
        MapController.printGameBoardToConsole(quest);
    }

    public static void showStats()
    {

    }

    public static void movePlayer(Hero hero, Quest quest)
    {
        MoveController.initializeHeroMovement(hero, quest);
    }

    public static Position getNewPositionInput()
    {
        return IOConsoleController.getNewPositionInput();
    }

    //.....
}
