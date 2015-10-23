package commands;

import model.Bird;
import model.GameWorld;
import utilities.SnippetPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action which represents a collision between the player's
 * car and a bird in the game world.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class CollideWithBirdAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**'
     * The single instance of CollideWithBirdAction.
     */
    private static final CollideWithBirdAction ourInstance = new CollideWithBirdAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static CollideWithBirdAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with the control's name.
     */
    private CollideWithBirdAction() {
        super(Command.COLLIDE_BIRD);
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Alerts the game world of a collision between the player's
     * car and a bird in the game world.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWorld.isSoundOn())
            SnippetPlayer.play(Bird.COLLISION_KEY);
    }
}
