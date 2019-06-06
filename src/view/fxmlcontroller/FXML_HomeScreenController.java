/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author jonas
 */
public class FXML_HomeScreenController implements Initializable {
    
    private Scene optionsScene;
    private Scene questSelectionScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

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
   
   @FXML
   public void openOptionsScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(optionsScene);
    }
    @FXML
    public void openQuestSelctionScene(ActionEvent event)
    {
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(questSelectionScene);
    }
    
}

    