package commands;

import model.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action to control the game world's sound state.
 * Action to set whether the game sound is on or off.
 * @author Robert Wilk
 *         Created on 3/6/2015.
 */
public class SetSoundAction
  extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The checkbox associated with the action.
     */
    private JCheckBoxMenuItem checkBox;
    /**
     * The single instance of SetSoundAction.
     */
    private static final SetSoundAction ourInstance = new SetSoundAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static SetSoundAction getInstance() {
        return ourInstance;
    }

    /**
     * Sets the target for the action.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        if (checkBox != null)
            checkBox.setState(gameWorld.isSoundOn());
    }

    /**
     * Sets the component associated with the action.
     * @param component The component.
     */
    public void setComponent(JComponent component) {
        checkBox = (JCheckBoxMenuItem) component;
        if (gameWorld != null)
            checkBox.setState(gameWorld.isSoundOn());
    }

    /**
     * Constructs the action with the control's name and mnemonic key.
     */
    private SetSoundAction() {
        super(Command.SOUND);
        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
    }

    /**
     * Updates the state of the game sound in the game world to reflect
     * the state of the check box in the menu.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWorld.setSoundOn(checkBox.isSelected());
        MusicStateAction.getInstance().actionPerformed(null);
    }
}
