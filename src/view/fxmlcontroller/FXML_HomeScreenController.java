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
 * The Controller for the HomeScreen.
 *
 * @author jonas
 */
public class FXML_HomeScreenController implements Initializable
{

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

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

    /**
     * Switches from the HomeScreen to the Options.
     *
     * @param event
     */
    @FXML
    public void openOptionsScene(ActionEvent event)
    {
        try
        {
            Stage primaryStage
                  = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(
                    new Scene(ViewController.getLoaderOptions().load()));
        } catch (IOException ex)
        {
            Logger.getLogger(FXML_HomeScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Switches from the HomeScreen to the QuestSelection.
     *
     * @param event
     */
    @FXML
    public void openQuestSelctionScene(ActionEvent event)
    {
        try
        {
            Stage primaryStage
                  = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(
                    new Scene(ViewController.getLoaderQuestSelection().load()));
        } catch (IOException ex)
        {
            Logger.getLogger(FXML_HomeScreenController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
