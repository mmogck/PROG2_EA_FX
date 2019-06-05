package view.viewmodels;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Markus Mogck
 */
public class FXImageView extends ImageView
{
    public FXImageView(Image image)
    {
        super(image);
        
        this.setFitWidth(24);
        this.setFitHeight(24);
    }
    
    public void show()
    {
        this.setVisible(true);
    }
    
    public void hide()
    {
        this.setVisible(false);
    }
}
