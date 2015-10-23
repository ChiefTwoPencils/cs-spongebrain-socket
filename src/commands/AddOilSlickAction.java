package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action to add an oil slick to the game world.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class AddOilSlickAction
extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of AddOilSlickAction.
     */
    private static final AddOilSlickAction ourInstance = new AddOilSlickAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static AddOilSlickAction getInstance() {
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
     * Constructs the action with the control's name and mnemonic key.s
     */
    private AddOilSlickAction() {
        super(Command.ADD_SLICK);
        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
    }

    /**
     * Adds an oil slick to the game world based on the rules
     * of the world.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.addOilSlick();
    }
}
