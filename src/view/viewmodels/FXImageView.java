package view.viewmodels;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.gameconstants.IGuiConstants;

/**
 * Adapted ImageView for printing sprites.
 *
 * @author Markus Mogck
 */
public class FXImageView extends ImageView
{

    public FXImageView(Image image)
    {
        super(image);

        this.setFitWidth(IGuiConstants.FXIMAGEVIEW_SIZE);
        this.setFitHeight(IGuiConstants.FXIMAGEVIEW_SIZE);
    }

    /**
     * Sets the ImageView visible.
     */
    public void show()
    {
        this.setVisible(true);
    }

    /**
     * Sets the ImageView invisible.
     */
    public void hide()
    {
        this.setVisible(false);
    }
}
