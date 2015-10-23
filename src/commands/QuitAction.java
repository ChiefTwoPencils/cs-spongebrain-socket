package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action to verify and handle if the user wants to quit the game.
 * @author Robert Wilk
 *         Created on 3/4/2015.
 */
public class QuitAction
  extends AbstractAction {

    /**
     * The single instance of QuitAction.
     */
    private static final QuitAction quitAction = new QuitAction();

    /**
     * Constructs the action with the control's name and mnemonic key.
     */
    private QuitAction() {
        super("Quit");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static QuitAction getInstance() {
        return quitAction;
    }

    /**
     * Shows a JOptionPane to confirm the user wants to quit. If so,
     * the action closes the game.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int dialogueButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane
          .showConfirmDialog(null, "Are you sure you want to end the game?", "Confirm Quit", dialogueButton);
        if (result == JOptionPane.YES_OPTION)
            System.exit(0);
    }
}
