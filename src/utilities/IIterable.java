package utilities;

import java.util.NoSuchElementException;

/**
 * Interface for iterators.
 *
 * @author Robert Wilk
 *         Created on 3/1/2015.
 */
public interface IIterable<E> {
    /**
     * Reports if the iterator has another element.
     *
     * @return Is there another element?
     */
    boolean hasNext();

    /**
     * Resets the iterator position to the front of
     * the collection.
     */
    void reset();

    /**
     * The next object from the iterator.
     *
     * @return The next object.
     */
    E next() throws NoSuchElementException;

    /**
     * Gets the size of the container served by the
     * iterator.
     * @return The size of the underlying container.
     */
    int size();

    /**
     * The current index of the iterator.
     * @return The indesx.
     */
    int position();
}
