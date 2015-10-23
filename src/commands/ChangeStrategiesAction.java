package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to change the strategies of strategic game objects.
 * @author Robert Wilk
 *         Created on 3/4/2015.
 */
public class ChangeStrategiesAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of ChangeStrategiesAction.
     */
    private static final ChangeStrategiesAction ourInstance = new ChangeStrategiesAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static ChangeStrategiesAction getInstance() {
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
    private ChangeStrategiesAction() {
        super(Command.CHANGE_STRATEGIES);
    }

    /**
     * Tells the game world to change the strategies of game
     * objects which implement a strategy.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.changeStrategies();
    }
}
