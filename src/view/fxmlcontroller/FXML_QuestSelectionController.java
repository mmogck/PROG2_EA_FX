/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fxmlcontroller;

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
public class FXML_QuestSelectionController implements Initializable {

    private Scene ingameScene;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void setIngameScene(Scene scene)
    {
        this.ingameScene = scene;
    }
    
    @FXML
    public void openIngameScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(ingameScene);
          
    }
    
}
