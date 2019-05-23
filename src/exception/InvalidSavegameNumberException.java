package exception;

/**
 * Exception if a number used for savegamemanagement is not valid.
 * 
 * @author Markus Mogck
 */
public class InvalidSavegameNumberException extends Exception
{
    public InvalidSavegameNumberException(String message)
    {
        super(message);
    }
}
