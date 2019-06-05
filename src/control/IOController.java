package control;

import control.gamemanagement.QuestController;
import control.ingamemanagement.MoveController;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.misc.Position;
import resources.gameconstants.IGameConstants;
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

    public static FXML_IngameSceneController getIngameSceneController()
    {
        return ViewController.getLoader().getController();
    }

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
        printMessage(text, Color.BLACK);
    }

    public static void printMessage(String text, IOColor color)
    {
        getIngameSceneController().printInfoText(text, color.getColor());
    }

    public static void printMessage(String text, Color color)
    {
        getIngameSceneController().printInfoText(text, color);
    }

    public static void printGameBoard(Quest quest)
    {
        Platform.runLater(
                () ->
                {
                    getIngameSceneController().printGameBoard(quest);
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
        getIngameSceneController().printPhaseInfo(activePhase, actionPoints);
    }

//    public static void printHeroInfo(Hero activeHero)
//    {
//        IOConsoleController.printlnColor(IOColor.FONT_BLUE,
//                                         activeHero.toString());
//    }
    public static void printHeroInfo(Hero activeHero)
    {
        getIngameSceneController().printHeroInfo(activeHero);
    }

    ///////////////////////////////////////////////////////////////////////////
    private enum ClickOnGameBoardStatus
    {

        NEW_POSITION,
        NEW_ATTACK,
        NEW_ACTION,
        GET_INFO;
    }

    public static void getPositionInput(Position position)
    {
        processPositionInput(position);
    }

    public static void processPositionInput(Position position)
    {
        switch (getClickOnGameBoardStatus())
        {
            case NEW_POSITION:
                MoveController.startHeroMovement(
                        QuestController
                        .getActiveGameLoop()
                        .getActiveQuest()
                        .getActiveHero(),
                        QuestController
                        .getActiveGameLoop()
                        .getActiveQuest(),
                        position);
                break;

            case NEW_ATTACK:

                break;

            case NEW_ACTION:

                break;

            case GET_INFO:
                getIngameSceneController()
                        .printFieldInfo(position,
                                        QuestController
                                        .getActiveGameLoop().getActiveQuest());
                break;

            default:
                break;
        }
    }

    private static ClickOnGameBoardStatus getClickOnGameBoardStatus()
    {
        switch (QuestController.getActiveGameLoop().getActivePhase())
        {
            case IGameConstants.HERO_PHASE:
                if (getIngameSceneController().isActionMove())
                {
                    return ClickOnGameBoardStatus.NEW_POSITION;
                } else
                {
                    return ClickOnGameBoardStatus.NEW_ATTACK;
                }
            default:
                return ClickOnGameBoardStatus.GET_INFO;
        }
    }

    public static void initializeHeroMovement()
    {
        if (getClickOnGameBoardStatus() == ClickOnGameBoardStatus.NEW_POSITION)
        {
            MoveController
                    .initializeHeroMovement(QuestController
                            .getActiveGameLoop()
                            .getActiveQuest()
                            .getActiveHero(),
                                            QuestController
                                            .getActiveGameLoop()
                                            .getActiveQuest());
        }
    }

    public static void resetHeroMovement()
    {
        MoveController.resetHeroMovement(
                QuestController.getActiveGameLoop().getActiveQuest());
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
