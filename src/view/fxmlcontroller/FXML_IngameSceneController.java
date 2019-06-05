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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.figure.Enemy;
import model.figure.Figure;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.map.Square;
import model.misc.Position;
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
    private AnchorPane anchorpane_info;

    @FXML
    private TextField textfield_activephase;
    @FXML
    private TextField textfield_actionpoints;
    @FXML
    private Button button_nextphase;
    @FXML
    private ToggleButton togglebutton_action;

    @FXML
    private ImageView imageview_info;
    @FXML
    private TextField textfield_info_field;
    @FXML
    private TextField textfield_info_impediment;
    @FXML
    private TextField textfield_info_hp;
    @FXML
    private TextField textfield_info_mp;
    @FXML
    private TextField textfield_info_damage;

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
        anchorpane_info.setBackground(
                new Background(
                        new BackgroundFill(Color.LIGHTGREEN,
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

    public void printFieldInfo(Position position, Quest quest)
    {
        for (Enemy enemy : quest.getEnemies())
        {
            if (enemy.getPosition().equals(position))
            {
                printFieldInfoFigure(enemy);
                return;
            }
        }

        for (Hero hero : quest.getHeroes())
        {
            if (hero.getPosition().equals(position))
            {
                printFieldInfoFigure(hero);
                return;
            }
        }

        printFieldInfoSquare(quest.getGameBoard()
                .getSquareFromPosition(position));
    }

    private void printFieldInfoSquare(Square square)
    {
        printFieldInfoText(square.getESquare().toString(),
                           Integer.toString(square.getImpediment()),
                           IOutputStrings.PLACEHOLDER_FILLED,
                           IOutputStrings.PLACEHOLDER_FILLED,
                           IOutputStrings.PLACEHOLDER_FILLED);
        printFieldInfoImage(square.getESquare().getSprite());
        activateSquareInfo();
    }

    private void printFieldInfoFigure(Figure figure)
    {
        printFieldInfoText(figure.getClass().getSimpleName(),
                           IOutputStrings.PLACEHOLDER_FILLED,
                           Integer.toString(figure.getHealthPoints()),
                           Integer.toString(figure.getMovementPoints()),
                           Integer.toString(figure.getAttackPower()));
        printFieldInfoImage(figure.getSprite());
        activateFigureInfo();
    }

    private void printFieldInfoText(String field,
                                    String impediment,
                                    String hp,
                                    String mp,
                                    String damage)
    {
        textfield_info_field.setText(field);
        textfield_info_impediment.setText(impediment);
        textfield_info_hp.setText(hp);
        textfield_info_mp.setText(mp);
        textfield_info_damage.setText(damage);
    }

    private void activateSquareInfo()
    {
        textfield_info_field.setDisable(false);
        textfield_info_impediment.setDisable(false);
        textfield_info_hp.setDisable(true);
        textfield_info_mp.setDisable(true);
        textfield_info_damage.setDisable(true);
    }

    private void activateFigureInfo()
    {
        textfield_info_field.setDisable(false);
        textfield_info_impediment.setDisable(true);
        textfield_info_hp.setDisable(false);
        textfield_info_mp.setDisable(false);
        textfield_info_damage.setDisable(false);
    }

    private void printFieldInfoImage(Image image)
    {
        imageview_info.setImage(image);
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
