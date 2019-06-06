package control;

import view.ViewControllerIngame;
import view.ViewControllerHomeScreen;

/**
 * Main Class of the game. Initializes the GUI.
 *
 * @author Markus Mogck
 */
public class Main
{

    /**
     * Main method for starting the GUI of the application.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ViewControllerHomeScreen.initializeGUI(args);
    }
}
