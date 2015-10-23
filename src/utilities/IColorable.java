package utilities;

import java.awt.*;

/**
 * Interface for game objects which can have their color changed.
 *
 * @author Robert Wilk
 */
public interface IColorable {
    /**
     * Changes the color of a game object.
     *
     * @param color The new color of the object.
     */
    public void changeColor(Color color);
}
