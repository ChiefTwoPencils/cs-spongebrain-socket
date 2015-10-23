package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

/**
 * Action to delete selected objects from the game world.
 * @author Robert Wilk
 *         Created on 4/18/2015.
 */
public class DeleteSelectedAction
  extends AbstractAction {
    /**
     * The single instance of DeleteSelectionAction.
     */
    private static DeleteSelectedAction ourInstance = new DeleteSelectedAction();
    /**
     * The function reference to be called upon trigger.
     */
    private Consumer<Void> consumer = null;

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static DeleteSelectedAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with the control's name.
     */
    private DeleteSelectedAction() {
        super(Command.DELETE_SELECTED);
    }

    /**
     * Sets the function to be invoked when triggered.
     * @param consumer The function.
     */
    public void setTargetMethod(Consumer<Void> consumer) {
        this.consumer = consumer;
    }

    /**
     * Invokes the functional target.
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        consumer.accept(null);
    }
}
