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
public class FXML_OptionsController implements Initializable
{

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    /**
     * Switches from the Options to the HomeScreen.
     *
     * @param event
     */
    @FXML
    public void openHomeScreenScene(ActionEvent event)
    {
        try
        {
            Stage primaryStage
                  = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(
                    new Scene(ViewController.getLoaderHomeScreen().load()));
        } catch (IOException ex)
        {
            Logger.getLogger(FXML_OptionsController.class.getName())
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
