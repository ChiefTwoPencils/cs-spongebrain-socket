package commands;

import model.Car;
import model.GameWorld;
import utilities.SnippetPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action which represents a collision between the player's
 * car and a non-player car.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class CollideWithCarAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of CollideWithCarAction.
     */
    private static final CollideWithCarAction ourInstance = new CollideWithCarAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static CollideWithCarAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with the control's name.
     */
    private CollideWithCarAction() {
        super(Command.COLLIDE_NPC);
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Alerts the game world of a collision between the player's car
     * and a non-player car.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWorld.isSoundOn())
            SnippetPlayer.play(Car.COLLISION_KEY);
    }
}
