package model;

import utilities.ICollider;
import utilities.IDamage;
import utilities.Location2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A game representation of a flying bird.
 * @author Robert Wilk
 */
public class Bird
extends AbstractMovableGameObject
implements IDamage {

    /**
     * The key used to determine a collision response by
     * the Collider.
     */
    public static final String COLLISION_KEY = "Bird";
    /**
     * The size of the bird.
     */
    private final int size;
    /**
     * The amount of damage done in a collision.
     */
    private final int damage;

    /**
     * In addition to the data taken by the super
     * {@link AbstractMovableGameObject}
     * class, this constructor takes the size of the bird.
     *
     * @param size The size of the bird.
     */
    public Bird(int size, Color color, Location2D location, int speed, int heading, int damage) {
        super(color,
          location,
          speed,
          heading
        );
        this.size = size;
        this.damage = damage;

        translate(-size, -size);
    }

    /**
     * @return The size of the bird.
     */
    int getSize() {
        return size;
    }

    /**
     * Birds cannot have their color changed after instantiation.
     * @param color The color of the object.
     */
    @Override
    public void setColor(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform saved = g.getTransform();
        g.transform(translation());
        g.setColor(getColor());
        g.drawOval(0, 0, getSize(), getSize());
        g.drawRect(0, 0, getSize(), getSize());
        g.setTransform(saved);
    }

    @Override
    public void move(int elapsedTime) {
        super.move(elapsedTime);
    }

    @Override
    public int damage() {
        return damage;
    }

    @Override
    public void handleCollisions() {
        ArrayList<ICollider> c = collisions.get(CURRENT);
        ArrayList<ICollider> p = collisions.get(PREVIOUS);

        for (ICollider e : c) {
            if (p.contains(e)) {
                p.remove(e);
            } else {
                handleCollision(e);
            }
        }
        collisions.clear();
        p.clear();
        collisions.add(CURRENT, p);
        collisions.add(PREVIOUS, c);
    }

    @Override
    public void trackCollision(ICollider other) {
        if (other instanceof IDamage) {
            collisions.get(CURRENT).add(other);
        }
    }

    @Override
    public String getCollisionKey() { return COLLISION_KEY; }

    @Override
    public Shape boundingShape() {
        return new Rectangle(
          0, //(int) translation().getTranslateX(),
          0, //(int) translation().getTranslateY(),
          size,
          size
        );
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        return new StringBuilder("Bird: loc=")
          .append(df.format(getLocation().getX()))
          .append(",")
          .append(df.format(getLocation().getY()))
          .append(" color=[")
          .append(getColor().getRed())
          .append(",")
          .append(getColor().getBlue())
          .append(",")
          .append(getColor().getGreen())
          .append("] heading=")
          .append(getHeading())
          .append(" speed=")
          .append(getSpeed())
          .append(" size=")
          .append(getSize())
          .toString();
    }
}
