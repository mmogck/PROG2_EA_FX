package view;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.gameconstants.IFileConstants;

/**
 * Controlls which scene is shown.
 *
 * @author Markus Mogck
 */
public class ViewController extends Application
{
    public static void initializeGUI(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(new File(IFileConstants.FILE_PATH_FXML_INGAME).toURI().toURL());
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
