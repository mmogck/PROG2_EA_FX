package view.viewmodels;

import control.IOController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.misc.Position;

/**
 * 
 * @author Markus Mogck
 */
public class FXImageView extends ImageView
{
    private Position position;
    
    public FXImageView(Image image, Position position)
    {
        super(image);
        
        this.position = position;
        
        this.setFitWidth(24);
        this.setFitHeight(24);
       
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                IOController.getPositionInput(position);
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
