package model;

import utilities.ICollider;
import utilities.Location2D;

import java.awt.*;

/**
 * Abstraction for all game objects which are located in a fixed
 * position.
 * @author Robert Wilk
 */
public abstract class AbstractFixedGameObject
  extends AbstractGameObject {

    /**
     * Basic constructor for all game objects; all game objects
     * have a color and location.
     * @param color    The color of the game object.
     * @param location The location of the game object.
     */
    protected AbstractFixedGameObject(Color color, Location2D location) {
        super(color, location);
    }

    /* (non-Javadoc)
         * @see model.AbstractGameObject#setLocation(a1.Location2D)
         */
    @Override
    protected void setLocation(Location2D location) {
        throw new UnsupportedOperationException("Fixed game objects can't be moved!");
    }
/*
    *//* (non-Javadoc)
     * @see model.AbstractGameObject#setLocation(double, double)
     *//*
    @Override
    protected void setLocation(double x, double y) {
        throw new UnsupportedOperationException("Fixed game objects can't be moved!");
    }*/

    @Override
    public void trackCollision(ICollider other) {
        if (!(other instanceof AbstractFixedGameObject))
            other.trackCollision(this);
    }
}
