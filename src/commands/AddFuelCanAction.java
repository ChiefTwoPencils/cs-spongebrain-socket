package commands;

import utilities.IGameWorld;
import utilities.IObserver;
import utilities.Location2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 * Action to add a FuelCan to the game world.
 * @author Robert Wilk
 *         Created on 4/15/2015.
 */
public class AddFuelCanAction
extends AbstractAction
implements MouseListener {

    /**
     * The game world.
     */
    private IGameWorld gameWorld;
    /**
     * The single instance of AddFuelCanAction.
     */
    private static final AddFuelCanAction instance = new AddFuelCanAction();
    /**
     * Should the action track the mouse?
     */
    private boolean track = false;

    /**
     * Constructs the action with the control's name.
     */
    private AddFuelCanAction() {
        super(Command.ADD_FUEL);
    }

    /**
     * Sets the target of the action.
     * @param gameWorld The target.
     */
    public void setTarget(IGameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Returns the single instance.
     * @return The instance.
     */
    public static AddFuelCanAction getInstance() {
        return instance;
    }

    /**
     * Tells the action to begin tracking the mouse location.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        track = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Prompts the user to input the desired size of the fuel can to be placed
     * at the location of the mouse press.
     * @param e Mouse pressed event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (enabled) {
            if (track) {
                final String prompt = "Please enter the size of the Fuel Can [1 - 3];\n";
                String input;
                int size;
                while (true) {
                    input = JOptionPane.showInputDialog(null, prompt, Command.ADD_FUEL, OK_CANCEL_OPTION);
                    if (input == null) {
                        return;
                    }
                    try {
                        size = Integer.parseInt(input);
                        if (size < 1 || size > 3) {
                            throw new NumberFormatException();
                        }
                        gameWorld.addFuelCan(new Location2D(e.getX(), e.getY()), size);
                        track = false;
                        break;
                    } catch (NumberFormatException nfe) {
                        JOptionPane
                          .showMessageDialog(null, "Sorry that was an incorrect size!");
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
