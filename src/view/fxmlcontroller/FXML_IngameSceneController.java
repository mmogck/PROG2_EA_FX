package view.fxmlcontroller;

import control.IOController;
import control.gamemanagement.QuestController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.ingamemanagement.Quest;
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
        
        Quest quest = new Quest(1, QuestController.initializeHeroes());
        printGameBoard(quest);
    }
    
    public void printGameBoard(Quest quest)
    {
        fxgameboard.printTiles(quest);
    }

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

}
