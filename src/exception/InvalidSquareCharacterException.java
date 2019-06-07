package exception;

/**
 * Exception for characters which aren't a symbol for a square.
 *
 * @author Markus Mogck
 */
public class InvalidSquareCharacterException extends Exception
{
    public InvalidSquareCharacterException(String message)
    {
        super(message);
    }
}
