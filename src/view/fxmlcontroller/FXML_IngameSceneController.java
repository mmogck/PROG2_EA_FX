package view.fxmlcontroller;

import control.IOController;
import control.gamemanagement.QuestController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import view.ESprites;
import view.viewmodels.FXGameboard;

/**
 * FXML Controller class
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
    private Label label_info ;
    @FXML
    private ImageView imageview_hero;
    @FXML
    private TextField textfield_hero;
    @FXML
    private TextField textfield_hp;
    @FXML
    private TextField textfield_mp;
    
    @FXML
    private TextField textfield_activephase;
    @FXML
    private Button button_nextphase;

    ////////////////////////////////////////////////////////////////////////
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeGameBoard();
    }

    private void initializeGameBoard()
    {
        fxgameboard = new FXGameboard();

        anchorpane_gameboard.getChildren().add(fxgameboard);
    }
    
    ////////////////////////////////////////////////////////////////////////

    public void printGameBoard(Quest quest)
    {
        fxgameboard.printTiles(quest);
    }

    public void printInfoText(String text)
    {
        label_info.setText(text);
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
}
