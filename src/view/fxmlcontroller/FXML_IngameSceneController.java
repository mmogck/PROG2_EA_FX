package view.fxmlcontroller;

import control.IOController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.figure.Enemy;
import model.figure.Figure;
import model.figure.Hero;
import model.gamemanagement.EClickOnGameboardStatus;
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

    private ToggleGroup togglegroup_action;
    @FXML
    private ToggleButton togglebutton_action;
    @FXML
    private ToggleButton togglebutton_move;
    @FXML
    private ToggleButton togglebutton_attack;

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
        initializeToggleGroup();
    }

    /**
     * Initializes the Gameboard. Adds tiles, squares and sprites.
     */
    private void initializeGameBoard()
    {
        fxgameboard = new FXGameboard();

        anchorpane_gameboard.getChildren().add(fxgameboard);
    }

    /**
     * Initilaizes some GUI elements.
     */
    private void initializeGUI()
    {
        setColoredBackground(anchorpane_hero, Color.LIGHTBLUE);
        setColoredBackground(anchorpane_controls, Color.LIGHTGREY);
        setColoredBackground(anchorpane_info, Color.LIGHTGREEN);
    }

    /**
     * Sets a background color to a pane.
     *
     * @param pane pane to get background color
     * @param color background color
     */
    private void setColoredBackground(Pane pane, Color color)
    {
        pane.setBackground(new Background(
                new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Initializes the toggle group for the toggle buttons which control the
     * actions.
     */
    private void initializeToggleGroup()
    {
        togglegroup_action = new ToggleGroup();

        togglebutton_move.setToggleGroup(togglegroup_action);
        togglebutton_attack.setToggleGroup(togglegroup_action);
        togglebutton_action.setToggleGroup(togglegroup_action);

        togglegroup_action.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov,
                 Toggle toggle, Toggle new_toggle) ->
                {
                    IOController.resetHeroMovement();
                    //IOController.resetHeroAttack();
                });
    }

    ////////////////////////////////////////////////////////////////////////
    /**
     * Prints all tiles and squares according to the active quest.
     *
     * @param quest active quest.
     */
    public void printGameBoard(Quest quest)
    {
        fxgameboard.printTiles(quest);
    }

    /**
     * Prints a info text to the gui.
     *
     * @param text text to be printet
     */
    public void printInfoText(String text)
    {
        printInfoText(text, Color.BLACK);
    }

    /**
     * Prints a info text to the gui.
     *
     * @param text text to be printet
     * @param color color (not supported yet)
     */
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

    /**
     * Prints all infos about the active hero.
     *
     * @param activeHero active hero
     */
    public void printHeroInfo(Hero activeHero)
    {
        imageview_hero.setImage(ESprites.HERO.getImage());
        textfield_hero.setText(IOutputStrings.PLACEHOLDER_EMPTY);
        textfield_hp.setText(Integer.toString(activeHero.getHealthPoints()));
        textfield_mp.setText(Integer.toString(activeHero.getMovementPoints()));
    }

    public void printQuestInfo(Quest quest)
    {

    }

    /**
     * Prints all infos about the active phase and sets the controls.
     *
     * @param activePhase active phase
     * @param actionPoints actions points if phase is hero phase
     */
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

    /**
     * Prints informations about the selected field to the info section.
     *
     * @param position
     * @param quest
     */
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

    /**
     * Prints informations to info section if field is a square.
     *
     * @param square
     */
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

    /**
     * Prints informations to info section if field is a figure.
     *
     * @param figure
     */
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

    /**
     * Prints text to the info section.
     *
     * @param field
     * @param impediment
     * @param hp
     * @param mp
     * @param damage
     */
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

    /**
     * Activates info section for square info.
     */
    private void activateSquareInfo()
    {
        textfield_info_field.setDisable(false);
        textfield_info_impediment.setDisable(false);
        textfield_info_hp.setDisable(true);
        textfield_info_mp.setDisable(true);
        textfield_info_damage.setDisable(true);
    }

    /**
     * Activates info section for figure info.
     */
    private void activateFigureInfo()
    {
        textfield_info_field.setDisable(false);
        textfield_info_impediment.setDisable(true);
        textfield_info_hp.setDisable(false);
        textfield_info_mp.setDisable(false);
        textfield_info_damage.setDisable(false);
    }

    /**
     * Prints image of the info section.
     *
     * @param image
     */
    private void printFieldInfoImage(Image image)
    {
        imageview_info.setImage(image);
    }

    ////////////////////////////////////////////////////////////////////////
    /**
     * Sets up the Hero Controls
     *
     * @param reset should the buttons be resetted?
     * @param group (de-)activates all toggle buttons
     */
    public void setHeroControls(boolean reset, boolean group)
    {
        if (reset)
        {
            togglebutton_move.setSelected(false);
            togglebutton_attack.setSelected(false);
            togglebutton_action.setSelected(false);
        }

        togglebutton_move.setDisable(!group);
        togglebutton_attack.setDisable(!group);
        togglebutton_action.setDisable(!group);
    }

    ////////////////////////////////////////////////////////////////////////
    //Button actions
    @FXML
    private void handleMenuFileCloseAction(ActionEvent event)
    {
        IOController.closeGame();
    }

    @FXML
    private void handleMenuFileNewQuestAction(ActionEvent event)
    {
        IOController.startQuest();
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

        }
    }

    @FXML
    private void handleToggleButtonMoveAction(ActionEvent event)
    {
        if (togglebutton_move.isSelected())
        {
            IOController.initializeHeroMovement();
        }
    }

    @FXML
    private void handleToggleButtonAttackAction(ActionEvent event)
    {
        if (togglebutton_attack.isSelected())
        {
            //IOController.initializeHeroAttack();
        }
    }

    ////////////////////////////////////////////////////////////////////////
    /**
     * Returns the current state of the status when clicked on the gameboard.
     *
     * @return EClickOnGameboardStatus
     */
    public EClickOnGameboardStatus getEClickOnGameBoardStatus()
    {
        if (togglegroup_action.getSelectedToggle() == null)
        {
            return EClickOnGameboardStatus.GET_INFO;
        } else if (togglegroup_action.getSelectedToggle()
                .equals(togglebutton_action))
        {
            return EClickOnGameboardStatus.NEW_ACTION;
        } else if (togglegroup_action.getSelectedToggle()
                .equals(togglebutton_move))
        {
            return EClickOnGameboardStatus.NEW_POSITION;
        } else if (togglegroup_action.getSelectedToggle()
                .equals(togglebutton_attack))
        {
            return EClickOnGameboardStatus.NEW_ATTACK;
        } else
        {
            return EClickOnGameboardStatus.GET_INFO;
        }
    }
}
