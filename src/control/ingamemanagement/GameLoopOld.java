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
public class GameLoopOld extends Thread
{

    private final Quest activeQuest;

    private String activePhase;
    private boolean foundNewTile;
    private int heroActionPoints;

    public GameLoopOld(Quest quest)
    {
        this.activeQuest = quest;

        this.activePhase = IGameConstants.HERO_PHASE;
        this.heroActionPoints = IGamebalanceConstants.HERO_ACTION_POINTS;
        this.foundNewTile = false;
        
        //executePhase(); mit button
    }

    @Override
    public void run()
    {
        gameloop();
    }

    private void gameloop()
    {
        do
        {
            IOController.printHeroInfo(this.getActiveQuest().getActiveHero());

            switch (getNextPhase())
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

            showGameBoard();

        } while (!this.activeQuest.isQuestObjectiveAchieved());

    }

    private void executePhase()
    {
        if (!this.activeQuest.isQuestObjectiveAchieved())
        {
            IOController.printHeroInfo(this.getActiveQuest().getActiveHero());

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

            showGameBoard();
        }
    }

    private void showGameBoard()
    {
        IOController.printGameBoard(this.activeQuest);
    }

    private void startHeroPhase()
    {
        this.activeQuest.getActiveHero().move(this.activeQuest);
    }

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

    private void startEventPhase()
    {
        //do event stuff
        this.foundNewTile = false;
    }

    private void startEnemyPhase()
    {
        for (Enemy enemy : this.activeQuest.getEnemies())
        {
            System.out.println(enemy.toString());
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
    }

    public void nextPhase()
    {
        getNextPhase();
        executePhase();
    }

    private String getNextPhase()
    {
        switch (activePhase)
        {
            case IGameConstants.HERO_PHASE:

                if (heroActionPoints > 0)
                {
                    this.heroActionPoints--;
                    return returnPhase(IGameConstants.HERO_PHASE);
                } else
                {
                    this.heroActionPoints
                    = IGamebalanceConstants.HERO_ACTION_POINTS;
                    return returnPhase(IGameConstants.EXPLORATION_PHASE);
                }
            case IGameConstants.EXPLORATION_PHASE:
                return returnPhase(IGameConstants.EVENT_PHASE);
            case IGameConstants.EVENT_PHASE:
                return returnPhase(IGameConstants.ENEMY_PHASE);
            case IGameConstants.ENEMY_PHASE:
                //nächsten lebenden Helden waehlen
                do
                {
                    this.activeQuest.setActiveHero(nextHero());
                } while (this.activeQuest
                        .getActiveHero().getHealthPoints() <= 0);
                this.heroActionPoints--;
                return returnPhase(IGameConstants.HERO_PHASE);
        }
        return null;
    }

    private String returnPhase(String phase)
    {
        //Action-Points + 1, da der aktuelle schon abgezogen wurde
        IOController.printActivePhase(phase, this.heroActionPoints + 1);
        this.activePhase = phase;
        return phase;
    }

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

    public Quest getActiveQuest()
    {
        return activeQuest;
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
