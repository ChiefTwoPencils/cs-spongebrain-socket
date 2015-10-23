package utilities;

import java.awt.*;

/**
 * Public interface for all game objects.
 *
 * @author Robert Wilk
 */
public interface IGameObject {

/*
    *//**
     * All game objects have a location and a means to get
     * its value.
     *
     * @return The location of the game object.
     *//*
    public Location2D getLocation();*/

    /**
     * All game objects have a color and a means to get
     * its value.
     *
     * @return The color of the game object.
     */
    public Color getColor();

    /**
     * @see Object#toString()
     */
    @Override
    public String toString();
}
