package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action for changing the color of all game objects which are "color-able".
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class GenerateNewColorsAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of GenerateNewColorsAction.
     */
    private static final GenerateNewColorsAction ourInstance = new GenerateNewColorsAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static GenerateNewColorsAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with the control's name and mnemonic key.
     */
    private GenerateNewColorsAction() {
        super(Command.NEW_COLORS);
        putValue(MNEMONIC_KEY, KeyEvent.VK_N);
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Tells the game world to generate new colors for all the game objects
     * which are color-able.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.generateNewColors();
    }
}
