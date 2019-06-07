package control.ingamemanagement;

import control.IOController;
import control.map.MapController;
import model.figure.Enemy;
import model.figure.Hero;
import model.ingamemanagement.Quest;
import resources.gameconstants.IGameConstants;
import resources.gameconstants.IGamebalanceConstants;

/**
 * Class of the Gameloop which handles the procedure of the game.
 *
 * @author Markus Mogck
 */
public class GameLoop extends Thread
{
    private Quest activeQuest;
    
    private String activePhase;
    private boolean foundNewTile;
    private int heroActionPoints;

    /**
     * Constructor of GameLoop class.
     */
    public GameLoop()
    {
        this.activePhase = IGameConstants.HERO_PHASE;
        this.heroActionPoints = IGamebalanceConstants.HERO_ACTION_POINTS;
        this.foundNewTile = false;
    }

    /**
     * Starts the first phase of a game. This is an extra method because the
     * first execute must wait for the gui to be loaded.
     */
    public void firstExecutePhase()
    {
        IOController.printHeroInfo(this.activeQuest.getActiveHero());
        IOController.printActivePhase(this.activePhase, this.heroActionPoints);

        //getNextPhase();
        executePhase();
    }

    /**
     * Executes the acitve phase and starts special actions for each phase. Also
     * the gameboard and quest-informations are getting printed to the scene
     * before and after the phase.
     */
    private void executePhase()
    {
        if (!this.activeQuest.isQuestObjectiveAchieved())
        {
            IOController.printIngameGui(this.activeQuest);

            switch (this.activePhase)
            {
                case IGameConstants.HERO_PHASE:
                    startHeroPhase();
                    break;
                case IGameConstants.EXPLORATION_PHASE:
                    startExplorationPhase();
                    break;
                case IGameConstants.EVENT_PHASE:
                    startEventPhase();
                    break;
                case IGameConstants.ENEMY_PHASE:
                    startEnemyPhase();
                    break;

                default:
                    break;
            }
        } else
        {
            //Beendent die aktuelle Quest erfolgreich
            IOController.endQuest(true);
        }
    }

    /**
     * Method for the Hero-Phase.
     */
    private void startHeroPhase()
    {

    }

    /**
     * Method for the Exploration-Phase. New found tiles are getting shown.
     */
    private void startExplorationPhase()
    {
        this.foundNewTile
        = MapController.isPositionAtNewTileEdge(
                        this.activeQuest.getActiveHero().getPosition());

        MapController
                .exploreNewFoundTileVisibleIfFound(
                        this.activeQuest,
                        this.activeQuest.getActiveHero().getPosition());
    }

    /**
     * Method for the Event-Phase.
     */
    private void startEventPhase()
    {
        //do event stuff
        this.foundNewTile = false;
    }

    /**
     * Method for the Enemy-Phase. Enemies are moving and attacking.
     */
    private void startEnemyPhase()
    {
        for (Enemy enemy : this.activeQuest.getEnemies())
        {
            //Gegner ist auf Held fokussiert, lebt und ist aktiv
            if (enemy.getFocusedHero() == this.activeQuest.getActiveHero()
                && enemy.isAlive()
                && enemy.isActive())
            {
                //Bewegung zum Held
                enemy.move(this.activeQuest);
                //Angriff auf Held
                enemy.attack(enemy.getFocusedHero());
            }
        }

        IOController.printIngameGui(this.activeQuest);
    }

    /**
     * Next phase is getting initialized and executed.
     */
    public void nextPhase()
    {
        getNextPhase();
        executePhase();
    }

    /**
     * Sets up the following phase.
     */
    private void getNextPhase()
    {
        switch (activePhase)
        {
            case IGameConstants.HERO_PHASE:
                this.heroActionPoints--;

                if (heroActionPoints > 0)
                {
                    returnPhase(IGameConstants.HERO_PHASE);
                } else
                {
                    this.heroActionPoints
                    = IGamebalanceConstants.HERO_ACTION_POINTS;
                    returnPhase(IGameConstants.EXPLORATION_PHASE);
                }
                break;
            case IGameConstants.EXPLORATION_PHASE:
                returnPhase(IGameConstants.EVENT_PHASE);
                break;
            case IGameConstants.EVENT_PHASE:
                returnPhase(IGameConstants.ENEMY_PHASE);
                break;
            case IGameConstants.ENEMY_PHASE:
                //nächsten lebenden Helden waehlen
                do
                {
                    this.activeQuest.setActiveHero(nextHero());
                } while (!this.activeQuest.getActiveHero().isAlive());

                returnPhase(IGameConstants.HERO_PHASE);
                break;
        }
    }

    /**
     * Sets the following phase.
     *
     * @param phase
     */
    private void returnPhase(String phase)
    {
        //Action-Points + 1, da der aktuelle schon abgezogen wurde
        IOController.printActivePhase(phase, this.heroActionPoints + 1);
        this.activePhase = phase;
    }

    /**
     * Method for finding the next hero in queue.
     *
     * @return next hero
     */
    private Hero nextHero()
    {
        //Suche nach aktuellem Held
        for (int i = 0; i < this.activeQuest.getHeroes().length; i++)
        {
            if (this.activeQuest.getActiveHero()
                == this.activeQuest.getHeroes()[i])
            {
                //Naechsten Helden, außer Held ist letzter, dann erster Held
                if (this.activeQuest.getHeroes()[i]
                    != this.activeQuest
                        .getHeroes()[this.activeQuest.getHeroes().length - 1])
                {
                    return this.activeQuest.getHeroes()[i + 1];
                } else
                {
                    return this.activeQuest.getHeroes()[0];
                }
            }
        }
        return this.activeQuest.getHeroes()[0];
    }

    //Getter and Setter
    public Quest getActiveQuest()
    {
        return activeQuest;
    }
    
    public void setActiveQuest(Quest quest)
    {
        this.activeQuest = quest;
    }

    public String getActivePhase()
    {
        return activePhase;
    }

    public boolean isFoundNewTile()
    {
        return foundNewTile;
    }

    public int getHeroActionPoints()
    {
        return heroActionPoints;
    }
}
