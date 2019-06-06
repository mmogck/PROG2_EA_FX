package view.fxmlcontroller;

import control.IOController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewController;

/**
 *
 * @author jonas
 */
public class FXML_QuestSelectionController implements Initializable
{

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    /**
     * Switches from the QuestSelection to an actual Quest.
     *
     * @param event
     */
    @FXML
    public void openIngameScene(ActionEvent event)
    {
        try
        {
            Stage primaryStage
                  = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(
                    new Scene(ViewController.getLoaderIngame().load()));
        } catch (IOException ex)
        {
            Logger.getLogger(FXML_QuestSelectionController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Closes the open Window.
     *
     * @param event
     */
    @FXML
    private void closeTheWindow(ActionEvent event)
    {
        IOController.closeGame();
    }

}
