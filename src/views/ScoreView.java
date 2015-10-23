package views;

import utilities.IGameWorld;
import utilities.IObserver;
import utilities.Observable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * View for the graphical representation of the game
 * state.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class ScoreView
  extends JPanel
  implements IObserver {

    /**
     * The game world.
     */
    private IGameWorld gameWorld;

    /**
     * Constructs the view to its initial state and
     * makes it visible.
     * @param gameWorld
     */
    public ScoreView(IGameWorld gameWorld) {
        this.gameWorld = gameWorld;
        int HGAP = 40;
        int VGAP = 10;
        setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
        TitledBorder titledBorder = new TitledBorder("Score View");
        titledBorder.setTitleColor(Color.WHITE);
        setBorder(titledBorder);
        setBackground(Color.DARK_GRAY);

        String[] labels = {
          "Time: ",
          "Lives Left: ",
          "Highest Player Pylon: ",
          "Player Fuel Remaining: ",
          "Player Damage Level: ",
          "Sound: "
        };
        for (String s : labels) {
            JLabel label = new JLabel(s);
            label.setForeground(Color.WHITE);
            add(label);
        }
        setVisible(true);
    }

    /**
     * Updates the labels to reflect the current values of
     * from the game state.
     * @param observable The observed object.
     * @param arg Arguments for the update.
     */
    @Override
    public void update(Observable observable, Object arg) {
        Component[] labels = getComponents();
        String[] states = gameWorld.getGameStates();
        for (int i = 0; i < labels.length; ++i)
            ((JLabel) labels[i]).setText(states[i]);
    }
}
