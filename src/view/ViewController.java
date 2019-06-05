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
    private static FXMLLoader loader = null;

    public static void initializeGUI(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        setLoader(new FXMLLoader((new File(IFileConstants.FILE_PATH_FXML_INGAME)
                                  .toURI()
                                  .toURL())));

        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static FXMLLoader getLoader()
    {
        return loader;
    }

    public static void setLoader(FXMLLoader loader)
    {
        ViewController.loader = loader;
    }
}
