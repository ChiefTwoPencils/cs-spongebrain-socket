package utilities;

import java.awt.*;

/**
 * @author Robert Wilk
 *         Created on 4/27/2015.
 */
public class BoundingShape
extends Rectangle {

    public BoundingShape(int width, int length) {
        this.setBounds(0, 0, width, length);
    }

    public void translate(double x, double y) {
        translate(x, y);
    }
}
