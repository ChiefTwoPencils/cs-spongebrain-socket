package commands;

import controller.Game;
import controller.GameOverException;
import controller.LifeLostException;
import model.GameWorld;
import utilities.SnippetPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to advance the game time by a tick.
 * @author Robert Wilk
 *         Created on 3/1/2015.
 */
public class AdvanceGameTimeAction
  extends AbstractAction {

    /**
     * Amount of elapsed time since the last tick.
     */
    private int elapsedTime;
    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of AdvanceGameTimeAction.
     */
    private static final AdvanceGameTimeAction ourInstance = new AdvanceGameTimeAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static AdvanceGameTimeAction getInstance() {
        return ourInstance;
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Constructs the action with the control's name.
     */
    private AdvanceGameTimeAction() {
        super(Command.TICK);
    }

    /**
     * Tells the game world to advance the game clock and update
     * the game objects. It handles LifeLost and GameOver exceptions
     * thrown from the game world.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            gameWorld.advanceGameTime(elapsedTime);
        } catch (LifeLostException lle) {
            if (gameWorld.isSoundOn())
                SnippetPlayer.play(Game.YOU_DIED);
            JOptionPane.showMessageDialog(
              null,
              lle.getMessage(),
              "You died!",
              JOptionPane.INFORMATION_MESSAGE
            );
        } catch (GameOverException goe) {
            if (gameWorld.isSoundOn())
                SnippetPlayer.play(Game.YOU_DIED);
            JOptionPane.showMessageDialog(
              null,
              goe.getMessage(),
              "Game Over",
              JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }

    /**
     * Sets the time elapsed since the last tick.
     * @param elapsedTime
     */
    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
