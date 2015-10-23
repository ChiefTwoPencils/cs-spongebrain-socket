package utilities;

import java.awt.*;

/**
 * Interface for all objects which can be drawn.
 * @author Robert Wilk
 *         Created on 3/22/2015.
 */
public interface IDrawable {
    /**
     * Draws the implementer.
     * @param g The graphics used to draw the object.
     */
    void draw(Graphics2D g);
}
