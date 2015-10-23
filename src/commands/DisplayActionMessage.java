package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action to display a message for features not yet implemented.
 * @author Robert Wilk
 *         Created on 3/5/2015.
 */
public class DisplayActionMessage
  extends AbstractAction {

    /**
     * The single instance of DisplayActionMessage.
     */
    private static final DisplayActionMessage instance = new DisplayActionMessage();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static DisplayActionMessage getInstance() { return instance; }

    /**
     * Shows the message dialogue.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane
          .showMessageDialog(null, "This command is not yet implemented; check back later!");
    }
}
