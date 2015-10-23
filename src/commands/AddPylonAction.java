package commands;

import model.GameWorld;
import utilities.IGameWorld;
import utilities.Location2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 * Action to add a pylon to the game world.
 * @author Robert Wilk
 *         Created on 4/15/2015.
 */
public class AddPylonAction
extends AbstractAction
implements MouseListener {

    /**
     * The game world.
     */
    private IGameWorld gameWorld;
    /**
     * The single instance of AddPylonAction.
     */
    private static AddPylonAction instance = new AddPylonAction();
    /**
     * Should the action track the mouse?
     */
    private boolean track = false;

    /**
     * Constructs the action with the control's name.
     */
    private AddPylonAction() {
        super(Command.ADD_PYLON);
    }

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static AddPylonAction getInstance() { return instance; }

    /**
     * Sets the target of the game world.
     * @param gameWorld The target.
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Tells the action to start tracking the mouse.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        track = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {    }

    /**
     * Prompts the user for the sequence number for the pylon that is to be
     * placed at the mouse's location.
     * @param e The mouse press event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (enabled) {
            if (track) {
                final String prompt = "Please enter the number of the Pylon [1 - 99];\n";
                String input;
                int number;
                while (true) {
                    input = JOptionPane.showInputDialog(null, prompt, Command.ADD_PYLON, OK_CANCEL_OPTION);
                    if (input == null) {
                        return;
                    }
                    try {
                        number = Integer.parseInt(input);
                        if (number < 1 || number > 99) {
                            throw new NumberFormatException();
                        }
                        gameWorld.addPylon(number, new Location2D(e.getX(), e.getY()));
                        track = false;
                        break;
                    } catch (NumberFormatException nfe) {
                        JOptionPane
                          .showMessageDialog(null, "Sorry that was an incorrect number!");
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
