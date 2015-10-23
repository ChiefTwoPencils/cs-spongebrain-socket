package utilities;

import java.awt.*;
import java.awt.geom.Area;

/**
 * @author Robert Wilk
 *         Created on 3/27/2015.
 */
public interface ICollider {

    /**
     * Determines if the object collides with
     * the other
     * @param other The other ICollider.
     * @return Did they collide?
     */
    boolean collidesWith(ICollider other);

    /**
     * Handles all collisions tracked by the collider.
     */
    void handleCollisions();

    /**
     * Handles a specific collision of two colliders.
     * @param other The other collder.
     */
    void handleCollision(ICollider other);

    /**
     * Determines if the collider has handled all of its
     * current collisions.
     * @return Done colliding?
     */
    boolean collided();

    /**
     * Sets the collided state.
     * @param collided Done colliding?
     */
    void collided(boolean collided);

    /**
     * Adds the collision to the list of current collisions.
     * @param other The other collider.
     */
    void trackCollision(ICollider other);

    /**
     * Gets the bounding shape of the collider.
     * @return The bounding shape.
     */
    Shape boundingShape();

    /**
     * Gets the collision key of the collider.
     * @return The key.
     */
    String getCollisionKey();

    /**
     * Constructs a collision string to be used to map the specific collision
     * to the appropriate response.
     * @param a The first collider.
     * @param b The second collider.
     * @return The collision key.
     */
    static String getCollisionString(ICollider a, ICollider b) {
        return a.getCollisionKey() + b.getCollisionKey();
    }
}
