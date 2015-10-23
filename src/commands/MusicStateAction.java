package commands;

import controller.Game;
import model.GameWorld;
import utilities.IGameWorld;
import utilities.SnippetPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action to keep the background music state correct.
 * @author Robert Wilk
 *         Created on 4/23/2015.
 */
public class MusicStateAction
extends AbstractAction {
    /**
     * The single instance of MusicStateAction.
     */
    private static MusicStateAction ourInstance = new MusicStateAction();
    /**
     * The checkbox associated with the action.
     */
    JCheckBoxMenuItem checkBox;
    /**
     * The game world.
     */
    private IGameWorld gameWorld;

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static MusicStateAction getInstance() {
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
     * Sets the component for the action.
     * @param componenet The component.
     */
    public void setComponenet(JComponent componenet) {
        checkBox = (JCheckBoxMenuItem) componenet;
        if (gameWorld != null)
            checkBox.setState(gameWorld.isSoundOn());
    }

    /**
     * Constructs the action with the control's name and mnemonic key.
     */
    private MusicStateAction() {
        super(Command.MUSIC);
        putValue(MNEMONIC_KEY, KeyEvent.VK_M);
    }

    /**
     * Starts or stops the looping background music depending on the state of
     * its associated component and the game world's sound state.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected() &&
            gameWorld.isSoundOn() &&
            Game.isPlaying()) {
                SnippetPlayer.loop(Game.GAME_BACKGROUND);
        } else {
            SnippetPlayer.stop(Game.GAME_BACKGROUND);
        }
    }
}
