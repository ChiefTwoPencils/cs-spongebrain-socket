package utilities;

/**
 * Interface for observer objects.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public interface IObserver {
    /**
     * Tells the observer to update itself.
     * @param observable The observed object.
     * @param arg Arguments for the update.
     */
    void update(Observable observable, Object arg);
}
