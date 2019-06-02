package view.fxmlcontroller;

import control.IOController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import view.viewmodels.FXGameboard;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_IngameSceneController implements Initializable
{

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
    }

    @FXML
    private void handleMenuFileCloseAction(ActionEvent event)
    {
        IOController.closeGame();
    }

}
