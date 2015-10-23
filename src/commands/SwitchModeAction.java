package commands;

import utilities.IGame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Robert Wilk
 *         Created on 3/26/2015.
 */
public class SwitchModeAction
extends AbstractAction {

    /**
     * The game.
     */
    private IGame game;
    /**
     * The single instance of SwitchModeAction.
     */
    private static SwitchModeAction ourInstance = new SwitchModeAction();
    /**
     * String for the play state.
     */
    private static final String PLAY = "Play";
    /**
     * String for the pause state.
     */
    private static final String PAUSE = "Pause";
    /**
     * Is the current state 'Paused'?
     */
    private static boolean paused = false;

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static SwitchModeAction getInstance() {
        return ourInstance;
    }

    /**
     * Sets the target for the action.
     * @param game The target.
     */
    public void setTarget(IGame game) {
        this.game = game;
    }

    /**
     * Constructs the action with the control's name.
     */
    private SwitchModeAction() { super(Command.MODE); }

    /**
     * Switches the game mode to either play or pause based on the current game state.
     * It swaps the button text to reflect the change.
     * @param e The button which invoked the action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (paused) {
            button.setText(PAUSE);
            game.play();
        } else {
            button.setText(PLAY);
            game.pause();
        }
        paused = !paused;
    }
}
