package model;

import utilities.IColorable;
import utilities.ISelectable;
import utilities.Location2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;

/**
 * A game representation of a fuel can.
 * @author Robert Wilk
 */
public class FuelCan
extends AbstractFixedGameObject
implements IColorable,
           ISelectable {

    /**
     * The key used to determine a collision response by
     * the Collider.
     */
    public static final String COLLISION_KEY = "FuelCan";
    /**
     * The SIZE of the fuel can.
     */
    private final int SIZE;
    /**
     * The length of one side of square that defines
     * the fuel can graphical representation.
     */
    private static final int SIDE = 20;
    /**
     * Is the object selected on the game map.
     */
    private boolean selected = false;

    /**
     * In addition to the data taken by the super
     * class, a fuel can has a SIZE attribute.
     *
     * @param size The SIZE of the fuel can.
     */
    public FuelCan(int size, Color color, Location2D location) {
        super(Color.RED, location);
        SIZE = size;

        translate(-(.5 * SIDE), -(.5 * SIDE));
        translate(location.getX(), location.getY());
    }

    /**
     * @return The SIZE of the fuel can.
     */
    public int getSize() {
        return SIZE;
    }

    @Override
    public void changeColor(Color color) {
        super.setColor(color);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        StringBuilder sb = new StringBuilder("FuelCan: loc=");
        sb.append(df.format(getLocation().getX()))
          .append(",")
          .append(df.format(getLocation().getY()))
          .append(" color=[")
          .append(getColor().getRed())
          .append(",")
          .append(getColor().getBlue())
          .append(",")
          .append(getColor().getGreen())
          .append("] size=")
          .append(getSize());
        return sb.toString();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform saved = g.getTransform();
        g.transform(translation());
        g.setColor(getColor());
        g.fillRect(0, 0, SIDE, SIDE);
        g.setColor(Color.white);
        g.drawRect(0, 0, SIDE, SIDE);

        g.drawString(String.valueOf(getSize()),
          (int) (.2 * SIDE), (int) (.75 * SIDE));
        if(selected)
            g.drawRect((int) (getLocation().getX() - .25 * SIDE), (int) (getLocation().getY() - .25 * SIDE),
              (int) (SIDE + .5 * SIDE), (int) (SIDE + .5 * SIDE));

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
     * Determines if an x value is within the fuel can.
     * @param x The x value.
     * @return Is the x value in the can?
     */
    private boolean betweenX(double x) {
        double ox = getLocation().getX() - SIDE / 2;
        return x >= ox && x <= ox + SIDE;
    }

    /**
     * Determines if a y value is within the fuel can.
     * @param y The y value.
     * @return Is the y value in the can?
     */
    private boolean betweenY(double y) {
        double oy = getLocation().getY() + SIDE / 2;
        return y >= oy && y <= oy + SIDE;
    }

    @Override
    public void handleCollisions() { }

    @Override
    public Shape boundingShape() {
        return new Rectangle(
          0, //(int) translation().getTranslateX(),
          0, //(int) translation().getTranslateY(),
          SIDE,
          SIDE
        );
    }

    @Override
    public String getCollisionKey() {
        return COLLISION_KEY;
    }
}
