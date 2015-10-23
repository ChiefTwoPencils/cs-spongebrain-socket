package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action to display information about the game.
 * @author Robert Wilk
 *         Created on 3/6/2015.
 */
public class AboutAction
  extends AbstractAction {

    /**
     * The single instance of AboutAction.
     */
    private static final AboutAction ourInstance = new AboutAction();

    /**
     * Returns the single instance.
     * @return The instance.
     */
    public static AboutAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with its control name and mnemonic key.
     */
    private AboutAction() {
        super(Command.ABOUT);
        putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }

    /**
     * Shows a JOptionPane displaying information about the
     * game.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String ABOUT = "Pylon Racer\n" +
          "Author: Robert Wilk\n" +
          "Course: CSc 133\n" +
          "Version: 0.2";
        JOptionPane.showMessageDialog(null, ABOUT);
    }
}
