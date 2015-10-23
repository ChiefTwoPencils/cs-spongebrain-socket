package model;

import utilities.IColorable;
import utilities.Location2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;

/**
 * A game representation of an oil slick.
 * @author Robert Wilk
 */
public class OilSlick
  extends AbstractFixedGameObject
  implements IColorable {

    /**
     * The key used to determine a collision response by
     * the Collider.
     */
    public static final String COLLISION_KEY = "OilSlick";
    /**
     * Width of the oil slick.
     */
    private final int width;
    /**
     * Length of the oil slick.
     */
    private final int length;

    /**
     * In addition to the data taken by the super
     * class, this constructor takes the width and length of the oil slick.
     *
     * @param width  The width of the oil slick.
     * @param length The length of the oil slick.
     */
    public OilSlick(int width, int length, Color color, Location2D location) {
        super(color, location);
        this.width = width;
        this.length = length;

        translate(-(getWidth() / 2), -(getLength() / 2));
        translate(location.getX(), location.getY());
    }

    /**
     * Method to get the width.
     *
     * @return The oil slick's width.
     */
    int getWidth() {
        return width;
    }

    /**
     * Method to get the oil slick's length.
     *
     * @return The oil slick's length.
     */
    int getLength() {
        return length;
    }

    @Override
    public void changeColor(Color color) {
        super.setColor(color);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform saved = g.getTransform();
        g.transform(translation());
        g.setColor(getColor());
        g.fillOval(0, 0, width, length);
        g.drawRect(0, 0, width, length);
        g.setTransform(saved);
    }

    @Override
    public void handleCollisions() { }

    @Override
    public Shape boundingShape() {
        return new Rectangle(
          (int) translation().getTranslateX(),
          (int) translation().getTranslateY(),
          getWidth(),
          getLength()
        );
    }

    @Override
    public String getCollisionKey() {
        return COLLISION_KEY;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        return new StringBuilder("OilSlick: loc=")
          .append(df.format(getLocation().getX()))
          .append(",")
          .append(df.format(getLocation().getY()))
          .append(" color=[")
          .append(getColor().getRed())
          .append(",")
          .append(getColor().getBlue())
          .append(",")
          .append(getColor().getGreen())
          .append("] width=")
          .append(getWidth())
          .append(" length=")
          .append(getLength())
          .toString();
    }
}
