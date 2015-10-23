package utilities;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Interface for transformational objects.
 * @author Robert Wilk
 *         Created on 4/24/2015.
 */
public interface ITransform {

    /**
     * Gets the translation transform.
     * @return The transform.
     */
    AffineTransform translation();

    /**
     * Translates the translation transform.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    void translate(double x, double y);

    /**
     * Gets the rotation transform.
     * @return The transform.
     */
    AffineTransform rotation();

    /**
     * Rotates the rotation transform.
     * @param radians The angle of rotation.
     */
    void rotate(double radians);

    void rotate(double theta, double x, double y);

    /**
     * Gets the scale transform.
     * @return The transform.
     */
    AffineTransform scale();

    /**
     * Scales the scale transform.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    void scale(double x, double y);

    /*void transform(Graphics2D g);*/

}
