package utilities;

/**
 * Class that represents a Cartesian 2-dimensional location.
 *
 * @author Robert Wilk
 */
public class Location2D
  implements Cloneable {
    /**
     * Cartesian x-axis value.
     */
    private double x;
    /**
     * Cartesian y-axis value.
     */
    private double y;

    /**
     * Default constructor that sets the coordinates at the Cartesian origin.
     */
    public Location2D() {
        x = 0;
        y = 0;
    }

    /**
     * Constructor that sets both x and y to the parameter's value.
     *
     * @param xy The value for x and y.
     */
    public Location2D(double xy) {
        x = y = xy;
    }

    /**
     * Constructor that takes parameters for both x and y.
     *
     * @param x The x-axis value.
     * @param y The y-axis value.
     */
    public Location2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to get the x-axis value.
     *
     * @return The location's x-axis value.
     */
    public double getX() {
        return x;
    }

    /**
     * Method to get the y-axis value.
     *
     * @return The location's y-axis value.
     */
    public double getY() {
        return y;
    }

    /**
     * Method to safely set the x-axis value.
     *
     * @param x The x-axis value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Method to safely set the y-axis value.
     *
     * @param y The y-axis value.
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public Location2D clone() {
        return new Location2D(x, y);
    }
}
