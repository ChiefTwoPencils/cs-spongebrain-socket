package utilities;

/**
 * Public interface for objects which are capable
 * of being steered.
 *
 * @author Robert Wilk
 */
public interface ISteerable {
    /**
     * Enumerated values of permissible directions.
     *
     * @author Robert Wilk
     */
    enum Direction {
        LEFT, RIGHT
    }

    /**
     * Provides the notion of steering.
     *
     * @param direction The direction to be steered.
     */
    public void steer(Direction direction);
}
