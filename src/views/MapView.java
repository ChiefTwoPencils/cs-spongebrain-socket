package views;

import model.NPCDerbyStrategy;
import model.NPCar;
import utilities.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 * View for the graphical representation of the game map.
 * @author Robert Wilk
 *         Created by on 2/28/2015.
 */
public class MapView
  extends JPanel
  implements IObserver {

    /**
     * The game world.
     */
    private IGameWorld gameWorld;

    /**
     * Constructs the view to its initial state and make
     * it visible.
     * @param gameWorld The game world.
     */
    public MapView(IGameWorld gameWorld) {
        this.gameWorld = gameWorld;
        TitledBorder mapBorder = new TitledBorder("Map View");
        mapBorder.setTitleColor(Color.WHITE);
        setBorder(mapBorder);
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    /**
     * Paints a simple representation of the game map.
     * @param g The graphics object for the view.
     */
    @Override
    protected void paintComponent(Graphics g) {
        IIterable<IDrawable> iterator = gameWorld.getMapViewIterator();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform saved = g2d.getTransform();
        // g.translate(0, this.getHeight());
        // ((Graphics2D) g).scale(1, -1);
        while(iterator.hasNext()) {
            IDrawable drawable = iterator.next();
            if (drawable instanceof NPCar) {
                NPCar npc = (NPCar) drawable;

                if (npc.getStrategy() instanceof NPCDerbyStrategy) {

                }
            }
            //iterator.next().draw((Graphics2D) g);
            drawable.draw(g2d);
        }
        for (Line2D.Double l : NPCDerbyStrategy.lines)
            g2d.draw(l);
        NPCDerbyStrategy.lines.clear();
        g2d.setTransform(saved);
    }

    /**
     * Tells the view to repaint itself.
     * @param observable The observed object.
     * @param arg Arguments for the update.
     */
    @Override
    public void update(Observable observable, Object arg) {
        repaint();
    }
}
