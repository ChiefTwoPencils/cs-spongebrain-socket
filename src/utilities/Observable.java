package utilities;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Super class for objects which are obsrvable.
 * @author Robert Wilk
 *         Created on 2/28/2015.
 */
public class Observable {
    /**
     * A collection of the observers.
     */
    private final ArrayList<IObserver> observers = new ArrayList<>();
    /**
     * Represents if the state of the observable has changed.
     */
    private boolean changed;

    /**
     * Registers an observer to the observable.
     * @param observer The observer to be added.
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies the registered observers to update themselves.
     */
    public void notifyObservers() {
        observers.forEach(p -> p.update(this, null));
        changed = false;
    }

    /**
     * Iterator representation of the observer collection.
     * @return The iterator.
     */
    protected Iterator<IObserver> observers() {
        return observers.iterator();
    }

    /**
     * Sets the state of the observable to changed.
     */
    protected void setChanged() {
        changed = true;
    }
}
