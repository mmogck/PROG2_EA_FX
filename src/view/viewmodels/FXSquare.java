package view.viewmodels;

import javafx.scene.layout.StackPane;
import view.ESprites;

/**
 *
 * @author Markus Mogck
 */
public class FXSquare extends StackPane
{
    private FXImageView rock;
    private FXImageView field;
    private FXImageView water;
    private FXImageView tree;
    private FXImageView hero;
    private FXImageView enemy;
    private FXImageView marked;

    private boolean squaremarked;

    public FXSquare()
    {
        initialize();
    }

    private void initialize()
    {
        //vlt mit oservable list auf bestimmte Elemente zugreifen
        
        this.getChildren().add(new FXImageView(ESprites.ROCK.getImage()));
        this.getChildren().add(new FXImageView(ESprites.FIELD.getImage()));
        this.getChildren().add(new FXImageView(ESprites.WATER.getImage()));
        this.getChildren().add(new FXImageView(ESprites.TREE.getImage()));

        this.getChildren().add(new FXImageView(ESprites.HERO.getImage()));
        this.getChildren().add(new FXImageView(ESprites.ENEMY.getImage()));
        this.getChildren().add(new FXImageView(ESprites.MARKED.getImage()));
    }

    public void setMarked(boolean marked)
    {
        if (marked)
        {

        } else
        {

        }

        this.squaremarked = marked;
    }
}
