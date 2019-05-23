package view;

/**
 * @package control
 * @file IOColor.java
 * @author Markus Mogck
 */
public enum IOColor
{
    FONT_RED("\u001B[31m", "\033[0m"),
    FONT_GREEN("\u001B[32m", "\033[0m"),
    FONT_BLUE("\u001B[34m", "\033[0m"),
    FONT_YELLOW("\u001B[33m", "\033[0m"),
    FONT_PURPLE("\u001B[35m", "\033[0m"),
    FONT_CYAN("\u001B[36m", "\033[0m"),
    FONT_WHITE("\u001B[37m", "\033[0m"),
    FONT_BLACK("\u001B[30m", "\033[0m"),
    
    BACKGROUND_RED("\u001B[41m","\u001B[0m"),
    BACKGROUND_GREEN("\u001B[42m","\u001B[0m"),
    BACKGROUND_BLUE("\u001B[44m","\u001B[0m"),
    BACKGROUND_YELLOW("\u001B[43m","\u001B[0m"),
    BACKGROUND_PURPLE("\u001B[45m","\u001B[0m"),
    BACKGROUND_CYAN("\u001B[46m","\u001B[0m"),
    BACKGROUND_WHITE("\u001B[47m","\u001B[0m"),
    BACKGROUND_BLACK("\u001B[40m","\u001B[0m");

    private final String startSequenz;
    private final String endSequenz;

    IOColor (String startSequenz, String endSequenz)
    {
        this.startSequenz = startSequenz;
        this.endSequenz = endSequenz;
    }

    public String getStartSequenz()
    {
        return startSequenz;
    }

    public String getEndSequenz()
    {
        return endSequenz;
    }
}
