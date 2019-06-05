package control;

import control.gamemanagement.QuestController;
import control.ingamemanagement.MoveController;
import javafx.application.Platform;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.misc.Position;
import view.IOColor;
import view.ViewController;
import view.fxmlcontroller.FXML_IngameSceneController;

/**
 * Class for all Methods giving an output or requiring an input.
 *
 * @author Markus Mogck
 */
public class IOController
{

//    public static void printMessage(String text)
//    {
//        printMessage(text, IOColor.FONT_BLACK);
//    }
//    public static void printMessage(String text, IOColor color)
//    {
//        IOConsoleController.printlnColor(color, text);
//    }
//    public static void printGameBoard(Quest quest)
//    {
//        MapController.printGameBoardToConsole(quest);
//    }
    public static void printMessage(String text)
    {
        printMessage(text, IOColor.FONT_BLACK);
    }

    public static void printMessage(String text, IOColor color)
    {
        FXML_IngameSceneController controller
                                   = ViewController.getLoader().getController();
        controller.printInfoText(text);
    }

    public static void printGameBoard(Quest quest)
    {
        FXML_IngameSceneController controller
                                   = ViewController.getLoader().getController();
        Platform.runLater(
                () ->
                {
                    controller.printGameBoard(quest);
                }
        );
    }

//    public static void printActivePhase(String activePhase, int actionPoints)
//    {
//
//        if (activePhase.equals(IGameConstants.HERO_PHASE))
//        {
//            IOConsoleController.printlnColor(IOColor.FONT_PURPLE,
//                                             activePhase);
//            IOConsoleController.printlnColor(IOColor.FONT_PURPLE,
//                                             IOutputStrings.MESSAGE_ACTIONPOINTS
//                                             + actionPoints);
//        } else
//        {
//            IOConsoleController.printlnColor(IOColor.FONT_PURPLE,
//                                             activePhase);
//        }
//
//    }
    public static void printActivePhase(String activePhase, int actionPoints)
    {
        FXML_IngameSceneController controller
                                   = ViewController.getLoader().getController();
        controller.printPhaseInfo(activePhase, actionPoints);
    }

//    public static void printHeroInfo(Hero activeHero)
//    {
//        IOConsoleController.printlnColor(IOColor.FONT_BLUE,
//                                         activeHero.toString());
//    }
    public static void printHeroInfo(Hero activeHero)
    {
        FXML_IngameSceneController controller
                                   = ViewController.getLoader().getController();
        controller.printHeroInfo(activeHero);
    }

    ///////////////////////////////////////////////////////////////////////////
    private ClickOnGameBoardStatus clickOnGameBoardStatus;

    private enum ClickOnGameBoardStatus
    {

        NEW_POSITION,
        NEW_ATTACK,
        NEW_ACTION,
        GET_INFO;
    }

    public static Position getPositionInput(Position position)
    {
        return position;
    }

    public static void processPositionInput(Position position)
    {
        
    }

    private ClickOnGameBoardStatus getClickOnGameBoardStatus()
    {
        return null;
    }

    public static void nextPhase()
    {
        QuestController.getActiveGameLoop().nextPhase();
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

    public static void closeGame()
    {
        System.exit(0);
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
