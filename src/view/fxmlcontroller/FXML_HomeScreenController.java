package view.fxmlcontroller;

import control.IOController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Controller for the HomeScreen.
 * 
 * @author jonas
 */
public class FXML_HomeScreenController implements Initializable {
    
    private Scene optionsScene;
    private Scene questSelectionScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
   /**
    * Closes the open Window.
    * @param event 
    */
   @FXML
   private void closeTheWindow(ActionEvent event)
   {
       IOController.closeGame();
   }
   
   public void setOptionsScene(Scene optionsScene)
   {
       this.optionsScene = optionsScene;
   }
   
   public void setQuestSelctionScene(Scene questSelectionScene)
   {
       this.questSelectionScene = questSelectionScene;
   }
   /**
    * Switches from the HomeScreen to the Options.
    * @param event 
    */
   @FXML
   public void openOptionsScene(ActionEvent event) 
   {
        Stage primaryStage; 
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(optionsScene);
    }
   
   /**
    * Switches from the HomeScreen to the QuestSelection. 
    * @param event 
    */
   @FXML
   public void openQuestSelctionScene(ActionEvent event)
   {
        Stage primaryStage;
        primaryStage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(questSelectionScene);
    }
    
}

    