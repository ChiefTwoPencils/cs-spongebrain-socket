package model;

import utilities.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Abstraction for all game objects.
 * @author Robert Wilk
 */
public abstract class AbstractGameObject
  implements IGameObject, IDrawable, ICollider, ITransform {

    /**
     * The translation matrix.
     */
    private AffineTransform translation;
    /**
     * The rotation matrix.
     */
    private AffineTransform rotation;
    /**
     * The scale matrix.
     */
    private AffineTransform scale;
    /**
     * The color of the game object.
     */
    private Color color;
    /**
     * The 2-dimensional location of the game object.
     */
    private Location2D location;
    /**
     * Has the game object handled all collisions for an iteration.
     */
    private boolean collided = false;
    /**
     * List of lists of current and previous collisions.
     */
    protected ArrayList<ArrayList<ICollider>> collisions = new ArrayList<>(2);
    /**
     * Index to the current collisions list.
     */
    public final int CURRENT = 0;
    /**
     * Index to the previous collisions list.
     */
    public final int PREVIOUS = 1;

    /**
     * Constructs a game object with the minimum attributes.
     * @param color The color of the object.
     * @param location The location of the object.
     */
    protected AbstractGameObject(Color color, Location2D location) {
        translation = new AffineTransform();
        rotation = new AffineTransform();
        scale = new AffineTransform();
        this.color = color;
        this.location = location;
        collisions.add(CURRENT, new ArrayList<>());
        collisions.add(PREVIOUS, new ArrayList<>());
    }

    /**
     * Method to get the color of the game object.
     * @return The color of the game object.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This is method is <i>not</i> intended to be invoked by <i>any</i>
     * child class users directly. To change the color of a game object which
     * implements {@link utilities.IColorable} use {@link utilities.IColorable#changeColor(Color)}
     * to do so. This method will throw {@link UnsupportedOperationException}
     * if invoked on a game object with a static color.
     * @param color The color of the object.
     */
    void setColor(Color color) {
        this.color = color;
    }

    /**
     * Method to get a clone of the 2-dimensional location of the game object.
     * @return The 2-dimensional location of the game object.
     */
    public Location2D getLocation() {
        return location.clone();
    }

    /**
     * This is method is <i>not</i> intended to be invoked by <i>any</i>
     * child class users directly. To change the location of a game object
     * which implements {@link utilities.IMovable} use {@link utilities.IMovable#move(int)}
     * to do so. This method will throw {@link UnsupportedOperationException}
     * if invoked on a game object with a fixed location.
     * @param location The location of the object.
     */
    void setLocation(Location2D location) {
        this.location = location.clone();
    }
/*

    */
/**
     * This is method is <i>not</i> intended to be invoked by <i>any</i>
     * child class users directly. To change the location of a game object which
     * implements {@link utilities.IMovable} use {@link utilities.IMovable#move(int)}
     * to do so. This method will throw {@link UnsupportedOperationException}
     * if invoked on a game object with a fixed location.
     * @param x The x-axis value
     * @param y The y-axis value
     *//*

    void setLocation(double x, double y) {
        location.setX(x);
        location.setY(y);
    }
*/

    @Override
    public boolean collidesWith(ICollider other) {
        Rectangle me = boundingShape().getBounds();
        Rectangle them = other.boundingShape().getBounds();
        return !((((me.x + me.width) < them.x) || (me.x > (them.x + them.width))) ||
          ((them.y > (me.y + me.height)) || (me.y > (them.y + them.height))));
    }

    @Override
    public boolean collided() {
        return collided;
    }

    @Override
    public void collided(boolean collided) {
        this.collided = collided;
    }

    @Override
    public void trackCollision(ICollider other) {
        collisions.get(CURRENT).add(other);
    }

    @Override
    public void handleCollision(ICollider other) {
        Collider.collide(this, other);
    }

    @Override
    public AffineTransform scale() {
        return scale;
    }

    @Override
    public AffineTransform rotation() {
        return rotation;
    }

    @Override
    public AffineTransform translation() {
        return translation;
    }

    @Override
    public void scale(double x, double y) {
        scale.scale(x, y);
    }

    @Override
    public void rotate(double radians) {
        rotation.rotate(radians);
    }

    @Override
    public void rotate(double theta, double x, double y) {
        rotation.rotate(theta, x, y);
    }

    @Override
    public void translate(double x, double y) {
        translation.translate(x, y);
    }
}
