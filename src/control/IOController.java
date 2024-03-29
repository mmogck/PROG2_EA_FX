package control;

import control.gamemanagement.QuestController;
import control.ingamemanagement.MoveController;
import control.map.MapController;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import model.figure.Hero;
import model.gamemanagement.EClickOnGameboardStatus;
import model.ingamemanagement.Quest;
import model.misc.EDifficulty;
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
        return ViewController.getLoaderIngame().getController();
    }

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

    public static void printIngameGui(Quest quest)
    {
        MapController.resetMarks(quest.getGameBoard());

        IOController.setupGuiForPhase(quest.getGameLoop().getActivePhase());

        IOController.printGameBoard(quest);

        IOController.printHeroInfo(quest.getActiveHero());
        IOController
                .printActivePhase(quest.getGameLoop().getActivePhase(),
                                  quest.getGameLoop().getHeroActionPoints());
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

    public static void printActivePhase(String activePhase, int actionPoints)
    {
        getIngameSceneController().printPhaseInfo(activePhase, actionPoints);
    }

    public static void printHeroInfo(Hero activeHero)
    {
        getIngameSceneController().printHeroInfo(activeHero);
    }

    public static void setupGuiForPhase(String activePhase)
    {
        if (activePhase.equals(IGameConstants.HERO_PHASE))
        {
            getIngameSceneController().setHeroControls(false, true);

            switch (getIngameSceneController().getEClickOnGameBoardStatus())
            {
                case NEW_POSITION:
                    initializeHeroMovement();
                    break;
                case NEW_ATTACK:
                    break;
                case NEW_ACTION:
                    break;
            }
        } else
        {
            getIngameSceneController().setHeroControls(true, false);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    public static void getPositionInput(Position position)
    {
        processPositionInput(position,
                             getIngameSceneController()
                             .getEClickOnGameBoardStatus());
    }

    public static void processPositionInput(Position position,
                                            EClickOnGameboardStatus status)
    {
        switch (status)
        {
            case NEW_POSITION:
                MoveController.startHeroMovement(
                        QuestController
                        .getActiveQuest()
                        .getActiveHero(),
                        QuestController
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
                                        QuestController.getActiveQuest());
                break;

            default:
                break;
        }
    }

    public static void initializeHeroMovement()
    {
        MoveController
                .initializeHeroMovement(QuestController
                        .getActiveQuest()
                        .getActiveHero(),
                                        QuestController
                                        .getActiveQuest());
    }

    public static void resetHeroMovement()
    {
        MoveController.resetHeroMovement(
                QuestController.getActiveQuest());
    }

    public static void nextPhase()
    {
        QuestController.getActiveQuest().getGameLoop().nextPhase();
    }

    ///////////////////////////////////////////////////////////////////////////
    public static void selectSavegame()
    {

    }

    public static void deleteSavegame()
    {

    }

    public static void startQuest()
    {
        QuestController.startQuest(1, EDifficulty.MEDIUM);
    }

    public static void endQuest(boolean successfully)
    {
        QuestController.getActiveQuest().end(successfully);
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
}
