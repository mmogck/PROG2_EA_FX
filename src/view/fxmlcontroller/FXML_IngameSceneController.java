package view.fxmlcontroller;

import control.IOController;
import control.gamemanagement.QuestController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IOutputStrings;
import view.ESprites;
import view.viewmodels.FXGameboard;

/**
 * FXML Controller class for the ingame scene.
 *
 * @author Markus Mogck
 */
public class FXML_IngameSceneController implements Initializable
{

    @FXML
    private AnchorPane anchorpane_root;
    @FXML
    private AnchorPane anchorpane_gameboard;

    private FXGameboard fxgameboard;

    @FXML
    private TextArea textarea_info;
    @FXML
    private ImageView imageview_hero;
    @FXML
    private TextField textfield_hero;
    @FXML
    private TextField textfield_hp;
    @FXML
    private TextField textfield_mp;

    @FXML
    private AnchorPane anchorpane_hero;
    @FXML
    private AnchorPane anchorpane_controls;

    @FXML
    private TextField textfield_activephase;
    @FXML
    private TextField textfield_actionpoints;
    @FXML
    private Button button_nextphase;

    @FXML
    private ToggleButton togglebutton_action;

    ////////////////////////////////////////////////////////////////////////
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeGameBoard();
        initializeGUI();
    }

    private void initializeGameBoard()
    {
        fxgameboard = new FXGameboard();

        anchorpane_gameboard.getChildren().add(fxgameboard);
    }

    private void initializeGUI()
    {
        anchorpane_hero.setBackground(
                new Background(
                        new BackgroundFill(Color.LIGHTBLUE,
                                           CornerRadii.EMPTY,
                                           Insets.EMPTY)));

        anchorpane_controls.setBackground(
                new Background(
                        new BackgroundFill(Color.LIGHTGREY,
                                           CornerRadii.EMPTY,
                                           Insets.EMPTY)));
    }

    ////////////////////////////////////////////////////////////////////////
    public void printGameBoard(Quest quest)
    {
        fxgameboard.printTiles(quest);
    }

    public void printInfoText(String text)
    {
        printInfoText(text, Color.BLACK);
    }

    public void printInfoText(String text, Color color)
    {
        Text textToAdd = new Text(text);
        textToAdd.setFill(color);
        
        textarea_info.setText(
                textarea_info.getText()
                + "\n"
                + textToAdd.getText());
        textarea_info.setScrollTop(Double.MAX_VALUE);
    }

    public void printHeroInfo(Hero activeHero)
    {
        imageview_hero.setImage(ESprites.HERO.getImage());
        textfield_hero.setText("");
        textfield_hp.setText(Integer.toString(activeHero.getHealthPoints()));
        textfield_mp.setText(Integer.toString(activeHero.getMovementPoints()));
    }

    public void printQuestInfo(Quest quest)
    {

    }

    public void printPhaseInfo(String activePhase, int actionPoints)
    {
        textfield_activephase.setText(activePhase);

        if (!activePhase.equals(IGameConstants.HERO_PHASE))
        {
            textfield_actionpoints.setText(IOutputStrings.PLACEHOLDER_FILLED);
            textfield_actionpoints.setDisable(true);
        } else
        {
            textfield_actionpoints.setText(Integer.toString(actionPoints));
            textfield_actionpoints.setDisable(false);
        }
    }

    ////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleMenuFileCloseAction(ActionEvent event)
    {
        IOController.closeGame();
    }

    @FXML
    private void handleMenuFileNewQuestAction(ActionEvent event)
    {
        QuestController.startQuest(1);
    }

    @FXML
    private void handleMenuGameflowNextPhaseAction(ActionEvent event)
    {
        IOController.nextPhase();
    }

    ////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleButtonNextPhaseAction(ActionEvent event)
    {
        IOController.nextPhase();
    }

    @FXML
    private void handleToggleButtonAction(ActionEvent event)
    {
        if (togglebutton_action.isSelected())
        {
            IOController.resetHeroMovement();
            togglebutton_action.setText(IOutputStrings.BUTTON_ACTION_ATTACK);
        } else
        {
            IOController.initializeHeroMovement();
            togglebutton_action.setText(IOutputStrings.BUTTON_ACTION_MOVE);
        }
    }

    ////////////////////////////////////////////////////////////////////////
    public boolean isActionMove()
    {
        return !togglebutton_action.isSelected();
    }
}
