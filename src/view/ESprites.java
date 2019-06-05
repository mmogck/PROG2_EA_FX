package view;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import resources.gameconstants.IFileConstants;

/**
 *
 * @author Markus Mogck
 */
public enum ESprites
{
    ROCK(IFileConstants.FILE_PATH_SPRITE_ROCK),
    FIELD(IFileConstants.FILE_PATH_SPRITE_FIELD),
    WATER(IFileConstants.FILE_PATH_SPRITE_WATER),
    TREE(IFileConstants.FILE_PATH_SPRITE_TREE),
    HERO(IFileConstants.FILE_PATH_SPRITE_HERO),
    ENEMY(IFileConstants.FILE_PATH_SPRITE_ENEMY),
    MARKED(IFileConstants.FILE_PATH_SPRITE_MARKED),
    EMPTY(IFileConstants.FILE_PATH_SPRITE_EMPTY);

    private final String filepath;

    ESprites(String filepath)
    {
        this.filepath = filepath;
    }
    
    public String getFilePath()
    {
        return this.filepath;
    }
    
    public Image getImage()
    {
        try
        {
            return new Image(
                    new File(this.filepath).toURI().toURL().toString());
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ESprites.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public URL getURLasURL()
    {
        try
        {
            return new File(this.filepath).toURI().toURL();
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ESprites.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getURLasString()
    {
        try
        {
            return new File(this.filepath).toURI().toURL().toString();
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(ESprites.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
