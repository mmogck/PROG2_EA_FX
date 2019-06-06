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
 * Adapted StackPane for the Square.
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

    /**
     * Constructor. Contains setOnMouseClick.
     *
     * @param position
     */
    public FXSquare(Position position)
    {
        this.position = position;

        initialize();

        /**
         * MouseClickEvent to process position input.
         */
        this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                IOController.getPositionInput(position);
            }
        });
    }

    /**
     * Adds all ImageViews to the Stack Pane.
     */
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

    /**
     * Prints the ImageViews in the right order according to the quest.
     *
     * @param quest active quest
     */
    public void print(Quest quest)
    {
        printTerrain(quest);
        printEnemy(quest);
        printHero(quest);
        printMarked(quest);
    }

    /**
     * Prints placeholder for not found tiles / squares.
     */
    public void printAsNotVisible()
    {
        this.empty.toFront();
    }

    /**
     * Prints the terrain / scenery layer.
     *
     * @param quest active quest
     */
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

    /**
     * Prints the enemy layer.
     *
     * @param quest active quest
     */
    private void printEnemy(Quest quest)
    {
        for (Enemy enemyFromQuest : quest.getEnemies())
        {
            if (enemyFromQuest.getPosition().equals(this.position)
                && enemyFromQuest.isAlive())
            {
                this.enemy.toFront();
            }
        }
    }

    /**
     * Prints the hero layer.
     *
     * @param quest active quest
     */
    private void printHero(Quest quest)
    {
        for (Hero heroFromQuest : quest.getHeroes())
        {
            if (heroFromQuest.getPosition().equals(this.position)
                && heroFromQuest.isAlive())
            {
                this.hero.toFront();
            }
        }
    }

    /**
     * Prints the marked layer.
     *
     * @param quest active quest
     */
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

    /**
     * Returns all FXImageViews that are part of the terrain / scenery.
     *
     * @return Set of FXImageViews
     */
    private Set<FXImageView> getTerrainFXImageViews()
    {
        HashSet<FXImageView> hashSet = new HashSet<>();

        hashSet.add(rock);
        hashSet.add(field);
        hashSet.add(water);
        hashSet.add(tree);

        return hashSet;
    }

    /**
     * Returns all FXImageViews of that FXSquare.
     *
     * @return Set of FXImageView
     */
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
