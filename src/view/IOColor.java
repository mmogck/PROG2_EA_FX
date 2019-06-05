package view;

import javafx.scene.paint.Color;

/**
 * Enum with colors and ther String sequences for the console output.
 *
 * @author Markus Mogck
 */
public enum IOColor
{

    FONT_RED("\u001B[31m", "\033[0m", Color.RED),
    FONT_GREEN("\u001B[32m", "\033[0m", Color.GREEN),
    FONT_BLUE("\u001B[34m", "\033[0m", Color.BLUE),
    FONT_YELLOW("\u001B[33m", "\033[0m", Color.YELLOW),
    FONT_PURPLE("\u001B[35m", "\033[0m", Color.PURPLE),
    FONT_CYAN("\u001B[36m", "\033[0m", Color.CYAN),
    FONT_WHITE("\u001B[37m", "\033[0m", Color.WHITE),
    FONT_BLACK("\u001B[30m", "\033[0m", Color.BLACK),
    BACKGROUND_RED("\u001B[41m", "\u001B[0m", Color.BLACK),
    BACKGROUND_GREEN("\u001B[42m", "\u001B[0m", Color.BLACK),
    BACKGROUND_BLUE("\u001B[44m", "\u001B[0m", Color.BLACK),
    BACKGROUND_YELLOW("\u001B[43m", "\u001B[0m", Color.BLACK),
    BACKGROUND_PURPLE("\u001B[45m", "\u001B[0m", Color.BLACK),
    BACKGROUND_CYAN("\u001B[46m", "\u001B[0m", Color.BLACK),
    BACKGROUND_WHITE("\u001B[47m", "\u001B[0m", Color.BLACK),
    BACKGROUND_BLACK("\u001B[40m", "\u001B[0m", Color.BLACK);

    private final String startSequenz;
    private final String endSequenz;
    private final Color color;

    IOColor(String startSequenz, String endSequenz, Color color)
    {
        this.startSequenz = startSequenz;
        this.endSequenz = endSequenz;
        this.color = color;
    }

    public String getStartSequenz()
    {
        return startSequenz;
    }

    public String getEndSequenz()
    {
        return endSequenz;
    }

    public Color getColor()
    {
        return color;
    }
}
