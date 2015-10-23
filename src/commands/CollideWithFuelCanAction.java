package commands;

import model.FuelCan;
import model.GameWorld;
import utilities.SnippetPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action that represents the player's car picking up a fuel
 * can.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class CollideWithFuelCanAction
extends AbstractAction {

    /**
     * The game world.
     */
    private GameWorld gameWorld = null;
    /**
     * The single instance of CollideWithFuelCanAction.
     */
    private static final CollideWithFuelCanAction ourInstance = new CollideWithFuelCanAction();

    /**
     * Returns the instance.
     * @return The instance.
     */
    public static CollideWithFuelCanAction getInstance() {
        return ourInstance;
    }

    /**
     * Constructs the action with the control's name and mnemonic key.
     */
    private CollideWithFuelCanAction() {
        super(Command.FUEL_PICKUP);
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
    }

    /**
     * Sets the target for the action.
     * @param gameWorld
     */
    public void setTarget(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * Alerts the game world that the player's car has picked up
     * some fuel.
     * @param e The event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWorld.isSoundOn())
            SnippetPlayer.play(FuelCan.COLLISION_KEY);
    }
}
