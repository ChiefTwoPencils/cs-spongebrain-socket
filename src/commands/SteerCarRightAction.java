package commands;

import model.GameWorld;
import utilities.ISteerable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action for steering the player's car right.
 * @author Robert Wilk
 *         Created on 3/5/2015.
 */
public class SteerCarRightAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of SteerCarRightAction.
     */
    private static final SteerCarRightAction ourInstance = new SteerCarRightAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static SteerCarRightAction getInstance() {
        return ourInstance;
    }

    /**
     * Sets the target of the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * A do nothing constructor.
     */
    private SteerCarRightAction() {
    }

    /**
     * Alerts the game world the player's car has requested a change
     * in its steering direction to the right.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.steerCar(ISteerable.Direction.RIGHT);
    }
}
