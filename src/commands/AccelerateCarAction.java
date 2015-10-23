package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to accelerate the player's car.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class AccelerateCarAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld;
    /**
     * The single instance of AccelerateCarAction.
     */
    private static final AccelerateCarAction ourInstance = new AccelerateCarAction();

    /**
     * Returns the single instance.
     * @return The instance.
     */
    public static AccelerateCarAction getInstance() {
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
    private AccelerateCarAction() { }

    /**
     * Tells the game world to accelerate the player's car.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.accelerateCar();
    }
}
