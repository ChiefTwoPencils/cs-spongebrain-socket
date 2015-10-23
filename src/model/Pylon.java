package model;

import utilities.ICollider;
import utilities.ISelectable;
import utilities.Location2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A game representation of a pylon.
 * @author Robert Wilk
 */
public class Pylon
extends AbstractFixedGameObject
implements ISelectable {

    double t = 2;
    /**
     * The key used to determine a collision response by
     * the Collider.
     */
    public static final String COLLISION_KEY = "Pylon";
    /**
     * The pylon's location along the track.
     */
    private int sequenceNumber;
    /**
     * The radius of the pylon.
     */
    private final double radius;
    /**
     * Is the Pylon selected?
     */
    private boolean selected = false;

    /**
     * In addition to the data taken by the super
     * {@link AbstractFixedGameObject#AbstractFixedGameObject(Color, Location2D, Shape)}
     * class, this constructor takes the sequence number and radius of the pylon.
     *
     * @param sequenceNumber Its postion along the track.
     * @param radius The radius of the pylon.
     */
    public Pylon(int sequenceNumber, double radius, Location2D location, Color color) {
        super(color, location);
        this.sequenceNumber = sequenceNumber;
        this.radius = radius;

        translate(-radius, -radius);
        translate(location.getX(), location.getY());
    }

    /**
     * @return The sequence number of the pylon.
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the pylon's sequence number.
     * @param sequenceNumber The new sequence number.
     */
    void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the radius of the pylon.
     * @return The radius of the pylon.
     */
    double getRadius() {
        return radius;
    }

    /**
     * Gets the diameter of the pylon.
     * @return The diameter.
     */
    double getDiameter() { return 2 * getRadius(); }

    @Override
    public void setColor(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("####.##");
        return new StringBuilder("Pylon: loc=")
          .append(df.format(getLocation().getX()))
          .append(",")
          .append(df.format(getLocation().getY()))
          .append(" color=[")
          .append(getColor().getRed())
          .append(",")
          .append(getColor().getBlue())
          .append(",")
          .append(getColor().getGreen())
          .append("] radius=")
          .append(getRadius())
          .append(" seqNum=")
          .append(getSequenceNumber())
          .toString();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform saved = g.getTransform();
        g.transform(translation());
        int height;
        int width = height = (int) getDiameter();
        g.setColor(getColor());
        g.fillOval(0, 0, width, height);

        g.drawRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(getSequenceNumber()), (int) radius, (int) radius);
        g.setColor(Color.WHITE);
        if(selected)
            g.drawRect(- 5, - 5, width + 10, height + 10);
        g.setTransform(saved);
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean contains(Point point) {
        return betweenX(point.getX()) && betweenY(point.getY());
    }

    /**
     * Determines if an x value is within the pylon.
     * @param x The x value.
     * @return Is the x value in the pylon?
     */
    private boolean betweenX(double x) {
        int ox = (int) (getLocation().getX() - radius);
        return x >= ox && x <= ox + getDiameter();
    }

    /**
     * Determines if a y value is within the pylon.
     * @param y The y value.
     * @return Is the y value in the pylon?
     */
    private boolean betweenY(double y) {
        int oy = (int) (getLocation().getY() + radius);
        return y >= oy && y <= oy + getDiameter();
    }

    @Override
    public void handleCollisions() { }

    @Override
    public Shape boundingShape() {
        return new Rectangle(
          (int) translation().getTranslateX(),
          (int) translation().getTranslateY(),
          (int) getRadius(),
          (int) getRadius()
        );
    }

    @Override
    public String getCollisionKey() { return COLLISION_KEY;  }
}
