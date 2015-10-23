package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action that represents a racer colliding with a pylon along
 * the track.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class CollideWithPylonAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of CollideWithPylonAction.
     */
    private static final CollideWithPylonAction ourInstance = new CollideWithPylonAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static CollideWithPylonAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with the control's name.
     */
    private CollideWithPylonAction() {
        super(Command.COLLIDE_PYLON);
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Alerts the game world that a car has collided with a pylon.
     * @param e The event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWorld.isSoundOn());
            //SnippetPlayer.play(Pylon.COLLISION_KEY);
    }
}
