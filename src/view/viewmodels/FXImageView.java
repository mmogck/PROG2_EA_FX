package view.viewmodels;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author Markus Mogck
 */
public class FXImageView extends ImageView
{
    public FXImageView(Image image)
    {
        super(image);
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println("test");
            }
        });
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
