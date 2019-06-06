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

    public static void initializeGUI(String[] args)
    {
        initializeAllLoaders();
        launch(args);
    }

    private static void initializeAllLoaders()
    {
        initializeHomeScreenLoader();
        initializeIngameLoader();
        initializeOptionsLoader();
        initializeQuestSelectionLoader();
    }

    private static void initializeHomeScreenLoader()
    {
        try
        {
            setHomeScreeLoader(
                    new FXMLLoader((new File(
                                    IFileConstants.FILE_PATH_FXML_HOMESCREEN)
                                    .toURI()
                                    .toURL())));
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ViewController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private static void initializeOptionsLoader()
    {
        try
        {
            setOptionsLoader(
                    new FXMLLoader((new File(
                                    IFileConstants.FILE_PATH_FXML_OPTIONS)
                                    .toURI()
                                    .toURL())));
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ViewController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private static void initializeQuestSelectionLoader()
    {
        try
        {
            setQuestSelectionLoader(
                    new FXMLLoader((new File(
                                    IFileConstants.FILE_PATH_FXML_QUESTSELECTION)
                                    .toURI()
                                    .toURL())));
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ViewController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private static void initializeIngameLoader()
    {
        try
        {
            setIngameLoader(
                    new FXMLLoader((new File(
                                    IFileConstants.FILE_PATH_FXML_INGAME)
                                    .toURI()
                                    .toURL())));
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ViewController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Starts the GUI and also distrinutes the different stages that are needed
     * by the other 'screens' to change from one to the other.
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
