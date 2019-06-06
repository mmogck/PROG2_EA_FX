package view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.gameconstants.IFileConstants;

/**
 * Starts the GUI from the HomeScreen.
 *
 * @author Jonas Ulrich
 */
public class ViewController extends Application
{

    private static FXMLLoader loaderHomeScreen = null;
    private static FXMLLoader loaderOptions = null;
    private static FXMLLoader loaderQuestSelection = null;
    private static FXMLLoader loaderIngame = null;

    /**
     * Initializes all loaders for the Scenes and start the GUI Thread.
     *
     * @param args Application Parameters
     */
    public static void initializeGUI(String[] args)
    {
        initializeAllLoaders();
        launch(args);
    }

    /**
     * Sets up all FXMLLoaders.
     */
    private static void initializeAllLoaders()
    {
        loaderHomeScreen = initializeLoader(
                IFileConstants.FILE_PATH_FXML_HOMESCREEN);
        loaderOptions = initializeLoader(
                IFileConstants.FILE_PATH_FXML_OPTIONS);
        loaderQuestSelection = initializeLoader(
                IFileConstants.FILE_PATH_FXML_QUESTSELECTION);
        loaderIngame = initializeLoader(
                IFileConstants.FILE_PATH_FXML_INGAME);
    }

    /**
     * Initializes an FXMLLoader.
     *
     * @param filepathToFxmlFile filepath to fxml file.
     * @return new FXMLLoader
     */
    private static FXMLLoader initializeLoader(String filepathToFxmlFile)
    {
        try
        {
            return new FXMLLoader((new File(filepathToFxmlFile)
                                   .toURI()
                                   .toURL()));
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ViewController.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Starts the GUI.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setScene(new Scene(loaderHomeScreen.load()));
        stage.show();
    }

    public static void setHomeScreeLoader(FXMLLoader loader)
    {
        ViewController.loaderHomeScreen = loader;
    }

    public static void setOptionsLoader(FXMLLoader loader)
    {
        ViewController.loaderOptions = loader;
    }

    public static void setQuestSelectionLoader(FXMLLoader loader)
    {
        ViewController.loaderQuestSelection = loader;
    }

    public static void setIngameLoader(FXMLLoader loader)
    {
        ViewController.loaderIngame = loader;
    }

    public static FXMLLoader getLoaderHomeScreen()
    {
        return loaderHomeScreen;
    }

    public static FXMLLoader getLoaderOptions()
    {
        return loaderOptions;
    }

    public static FXMLLoader getLoaderQuestSelection()
    {
        return loaderQuestSelection;
    }

    public static FXMLLoader getLoaderIngame()
    {
        return loaderIngame;
    }

}
