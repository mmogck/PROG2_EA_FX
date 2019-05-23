package control;

import java.util.Scanner;
import model.misc.Position;
import resources.gameconstants.IOutputStrings;
import view.IOColor;

/**
 * Managing console in- and outputs.
 *
 * @author Markus Mogck
 */
public class IOConsoleController
{

    private static Scanner scanner = new Scanner(System.in);

    public static void executeScannerCommands()
    {
        printlnColor(IOColor.FONT_BLUE, IOutputStrings.MESSAGE_INPUT);
        switch (getInputLine())
        {
            case IOutputStrings.COMMAND_EXIT:
                System.exit(0);
                break;
            case IOutputStrings.COMMAND_TEST:
                break;

            case IOutputStrings.COMMAND_SELECTSAVEGAME:
                break;
            case IOutputStrings.COMMAND_SAVEGAME:
                break;
            case IOutputStrings.COMMAND_SHOWSTATS:
                break;
            case IOutputStrings.COMMAND_SHOWQUESTS:
                break;
            case IOutputStrings.COMMAND_SELECTQUEST:
                break;
            case IOutputStrings.COMMAND_EXITQUEST:
                break;
                
            case IOutputStrings.COMMAND_MOVEPLAYER:
                break;

            default:
                printlnError(IOutputStrings.ERROR_WRONGINPUT);
                break;
        }
    }
    
    public static Position getNewPositionInput()
    {
        printlnColor(IOColor.FONT_BLUE, IOutputStrings.MESSAGE_SELECT_HERO_X_POS);
        int x = getInputInt();
        printlnColor(IOColor.FONT_BLUE, IOutputStrings.MESSAGE_SELECT_HERO_Y_POS);
        int y = getInputInt();
        
        return new Position(x, y);
    }

    public static void printlnColor(IOColor color, String text)
    {
        System.out.println(color.getStartSequenz()
                           + text
                           + color.getEndSequenz());
    }

    public static void printlnError(String text)
    {
        System.out.println(IOColor.FONT_RED.getStartSequenz()
                           + text
                           + IOColor.FONT_RED.getEndSequenz());
    }

    public static String getInputLine()
    {
        return scanner.nextLine();
    }

    public static int getInputInt()
    {
        return scanner.nextInt();
    }
}
