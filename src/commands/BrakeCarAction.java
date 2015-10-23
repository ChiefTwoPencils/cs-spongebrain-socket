package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to brake the player's car.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class BrakeCarAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of BrakeCarAction.
     */
    private static final BrakeCarAction ourInstance = new BrakeCarAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static BrakeCarAction getInstance() {
        return ourInstance;
    }

    /**
     * A do nothing constructor.
     */
    private BrakeCarAction() {
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Tells the game world to slow the player's car down.
     * @param e The event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.brakeCar();
    }
}
