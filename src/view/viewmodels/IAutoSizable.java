package view.viewmodels;

import javafx.scene.layout.Pane;

/**
 * Interface for viewmodels which should adapt their size to the parents size.
 * 
 * @author Markus Mogck
 */
public interface IAutoSizable
{
    public static void adaptSize(Pane pane)
    {
        Pane parent = (Pane) pane.getParent();

        pane.prefWidthProperty().bind(parent.prefWidthProperty());
        pane.prefHeightProperty().bind(parent.prefHeightProperty());
    }
}
