package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static utilities.ISteerable.Direction.LEFT;

/**
 * Action for steering the player's car left.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class SteerCarLeftAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of SteerCarLeftAction.
     */
    private static final SteerCarLeftAction ourInstance = new SteerCarLeftAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static SteerCarLeftAction getInstance() {
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
     * A do nothing constructor.
     */
    private SteerCarLeftAction() {
    }

    /**
     * Alerts the game world the player's car has requested a change
     * in its steering direction to the left.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.steerCar(LEFT);
    }
}
