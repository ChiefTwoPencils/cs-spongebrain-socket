package utilities;

import java.util.Collection;

/**
 * Interface for collection classes.
 *
 * @author Robert Wilk
 *         Created on 3/1/2015.
 */
interface ICollection<E> {
    /**
     * Adds an element to the collection.
     *
     * @param element The object to be added.
     */
    void add(E element);

    void addAll(Collection<E> collection);

    /**
     * Removes an element from the collection if
     * it exists.
     *
     * @param element The element to be removed.
     */
    void remove(E element);

    /**
     * Gets an iterator for the collection.
     *
     * @return The iterator.
     */
    IIterable getIterator();

    /**
     * Returns an iterator advanced to an index.
     * @param start The beginning index of the iterator.
     * @return The iterator.
     */
    IIterable getIterator(int start);
}
