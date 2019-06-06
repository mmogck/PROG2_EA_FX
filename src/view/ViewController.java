/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resources.gameconstants.IFileConstants;
import view.fxmlcontroller.FXML_HomeScreenController;
import view.fxmlcontroller.FXML_OptionsController;
import view.fxmlcontroller.FXML_QuestSelectionController;

/**
 * Starts the GUI from the HomeScreen.
 * @author jonas
 */
public class ViewControllerHomeScreen extends Application {
    
 
        
   private static FXMLLoader loaderHomeScreen = null;
   private static FXMLLoader loaderOptions = null;
   private static FXMLLoader loaderQuestSelection = null;
   private static FXMLLoader loaderIngame = null;

    public static void initializeGUI(String[] args)
    {
        launch(args);
    }
    /**
     * Starts the GUI and also distrinutes the different stages that are needed
     * by the other 'screens' to change from one to the other.
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        setHomeScreeLoader(new FXMLLoader((new File(IFileConstants.FILE_PATH_FXML_HOMESCREEN)
                                  .toURI()
                                  .toURL())));
        
        Parent rootHomeScreen = loaderHomeScreen.load();
        Scene sceneHomeScreen = new Scene(rootHomeScreen);
        
        setOptionsLoader(new FXMLLoader((new File(IFileConstants.FILE_PATH_FXML_OPTIONS)
                                  .toURI()
                                  .toURL())));
        
        Parent rootOptions = loaderOptions.load();
        Scene sceneOptions = new Scene(rootOptions);
        
        setQuestSelectionLoader(new FXMLLoader((new File(IFileConstants.FILE_PATH_FXML_QUESTSELECTION)
                                   .toURI()
                                   .toURL())));
        
        Parent rootQuestSelection = loaderQuestSelection.load();
        Scene sceneQuestSelection = new Scene(rootQuestSelection);
        
        setIngameLoader(new FXMLLoader((new File(IFileConstants.FILE_PATH_FXML_INGAME)
                                   .toURI()
                                   .toURL())));
        
        Parent rootIngame = loaderIngame.load();
        Scene sceneIngame = new Scene(rootIngame);      
        
        FXML_HomeScreenController HomeScreenController = (FXML_HomeScreenController) loaderHomeScreen.getController();
        HomeScreenController.setOptionsScene(sceneOptions);
        HomeScreenController.setQuestSelctionScene(sceneQuestSelection);
        
        FXML_OptionsController OptionsController = (FXML_OptionsController) loaderOptions.getController();
        OptionsController.setHomeScreenScene(sceneHomeScreen);
        
        FXML_QuestSelectionController questSelectionController = (FXML_QuestSelectionController) loaderQuestSelection.getController();
        questSelectionController.setIngameScene(sceneIngame);
        
        
       

        stage.setScene(sceneHomeScreen);
        stage.show();
    }


    public static void setHomeScreeLoader(FXMLLoader loader)
    {
        ViewControllerHomeScreen.loaderHomeScreen = loader;
    }
    
    public static void setOptionsLoader(FXMLLoader loader)
    {
        ViewControllerHomeScreen.loaderOptions = loader;
    }
    
    public static void setQuestSelectionLoader(FXMLLoader loader)
    {
        ViewControllerHomeScreen.loaderQuestSelection = loader;
    }
    
    public static void setIngameLoader(FXMLLoader loader)
    {
        ViewControllerHomeScreen.loaderIngame = loader;
    }
  
}
