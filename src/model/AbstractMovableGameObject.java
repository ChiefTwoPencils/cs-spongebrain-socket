package model;

import utilities.ICollider;
import utilities.IMovable;
import utilities.Location2D;

import java.awt.*;

/**
 * Abstraction for all game objects which can be moved.
 * @author Robert Wilk
 */
public abstract class AbstractMovableGameObject
  extends AbstractGameObject
  implements IMovable, ICollider {

    /**
     * A heading value which equates to "straight" ahead.
     */
    static final int BASE_ANGLE = 90;
    /**
     * The heading of the game object.
     */
    private int heading;
    /**
     * The speed of the game object.
     */
    private int speed;

    /**
     * In addition to the data taken by the super
     * {@link AbstractGameObject#AbstractGameObject(Color, Location2D, Shape)}
     * class, this constructor takes the speed and heading of the
     * movable object.
     *
     * @param speed   The initial speed of the object.
     * @param heading The initial heading of the object.
     */
    protected AbstractMovableGameObject(Color color,
                                        Location2D location,
                                        int speed,
                                        int heading) {
        super(color, location);
        this.speed = speed;
        this.heading = heading;
    }

    /**
     * @return The heading of the object.
     */
    int getHeading() {
        return heading;
    }

    /**
     * @return The speed of the object.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param heading The heading of the object.
     */
    void setHeading(int heading) {
        this.heading = heading;
    }

    /**
     * @param speed The speed of the object.
     */
    void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Provides the default move behavior of movable game objects.
     */
    @Override
    public void move(int elapsedTime) {
        double radians = Math.toRadians(BASE_ANGLE - getHeading());
        // Equation taken from assignment 1 requirements document.
        double distance = ((double) (getSpeed() * elapsedTime)) / 1000.0;
        double dx = Math.cos(radians) * distance;
        double dy = Math.sin(radians) * distance;
        translate(dx, dy);
    }
}
