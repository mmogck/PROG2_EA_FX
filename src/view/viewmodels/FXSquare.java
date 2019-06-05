package view.viewmodels;

import control.IOController;
import java.util.HashSet;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.figure.Enemy;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import model.misc.Position;
import view.ESprites;

/**
 *
 * @author Markus Mogck
 */
public class FXSquare extends StackPane
{

    private final Position position;

    private FXImageView rock;
    private FXImageView field;
    private FXImageView water;
    private FXImageView tree;
    private FXImageView hero;
    private FXImageView enemy;
    private FXImageView marked;
    private FXImageView empty;

    public FXSquare(Position position)
    {
        this.position = position;

        initialize();
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                IOController.getPositionInput(position);
            }
        });
    }

    private void initialize()
    {
        //vlt mit oservable list auf bestimmte Elemente zugreifen
        this.rock = new FXImageView(ESprites.ROCK.getImage());
        this.field = new FXImageView(ESprites.FIELD.getImage());
        this.water = new FXImageView(ESprites.WATER.getImage());
        this.tree = new FXImageView(ESprites.TREE.getImage());

        this.hero = new FXImageView(ESprites.HERO.getImage());
        this.enemy = new FXImageView(ESprites.ENEMY.getImage());

        this.marked = new FXImageView(ESprites.MARKED.getImage());
        this.empty = new FXImageView(ESprites.EMPTY.getImage());

        this.getChildren().addAll(rock,
                                  field,
                                  water,
                                  tree,
                                  hero,
                                  enemy,
                                  marked,
                                  empty);
    }

    public void print(Quest quest)
    {
        printTerrain(quest);
        printEnemy(quest);
        printHero(quest);
        printMarked(quest);
    }
    
    public void printAsNotVisible()
    {
        this.empty.toFront();
    }

    private void printTerrain(Quest quest)
    {
        switch (quest.getGameBoard().getSquareFromPosition(position).getESquare())
        {
            case FIELD:
                this.field.toFront();
                break;

            case ROCK:
                this.rock.toFront();
                break;

            case WATER:
                this.water.toFront();
                break;

            case TREE:
                this.tree.toFront();
                break;

            default:
                break;
        }
    }

    private void printEnemy(Quest quest)
    {
        for (Enemy enemyFromQuest : quest.getEnemies())
        {
            if (enemyFromQuest.getPosition().equals(this.position))
            {
                this.enemy.toFront();
            }
        }
    }

    private void printHero(Quest quest)
    {
        for (Hero heroFromQuest : quest.getHeroes())
        {
            if (heroFromQuest.getPosition().equals(this.position))
            {
                this.hero.toFront();
            }
        }
    }

    private void printMarked(Quest quest)
    {
        if (quest.getGameBoard().getSquareFromPosition(position).isMarked())
        {
            this.marked.toFront();
        } else
        {
            this.marked.toBack();
        }
    }

    public Position getPosition()
    {
        return position;
    }

    private Set<FXImageView> getTerrainFXImageViews()
    {
        HashSet<FXImageView> hashSet = new HashSet<>();

        hashSet.add(rock);
        hashSet.add(field);
        hashSet.add(water);
        hashSet.add(tree);

        return hashSet;
    }

    public Set<FXImageView> getFXImageViews()
    {
        HashSet<FXImageView> hashSet = new HashSet<>();

        hashSet.add(rock);
        hashSet.add(field);
        hashSet.add(water);
        hashSet.add(tree);

        hashSet.add(enemy);
        hashSet.add(hero);

        hashSet.add(marked);
        hashSet.add(empty);

        return hashSet;
    }
}
