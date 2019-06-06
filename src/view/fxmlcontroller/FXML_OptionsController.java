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
public class FXML_OptionsController implements Initializable{
    
    private Scene HomeScreenScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void setHomeScreenScene(Scene scene)
    {
        this.HomeScreenScene = scene;
    }
    
    @FXML
    public void openHomeScreenScene(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(HomeScreenScene);
    }
    
}
