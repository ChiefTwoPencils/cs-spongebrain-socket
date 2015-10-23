package utilities;

import model.AbstractGameObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A collection class for game objects.
 * @author Robert Wilk
 *         Created on 3/1/2015.
 */
public class GameObjectCollection<E extends AbstractGameObject>
  implements ICollection<E> {
    /**
     * ArrayList of AbstractGameObjects for the GameWorld.
     */
    private ArrayList<E> gameObjects;

    /**
     * Basic constructor.
     */
    public GameObjectCollection() {
        gameObjects = new ArrayList<>();
    }

    /**
     * Clears the collection.
     */
    public void clear() {
        gameObjects = new ArrayList<>();
    }

    @Override
    public void add(E element) {
        gameObjects.add(element);
    }

    @Override
    public void addAll(Collection<E> collection) {
        for (E e : collection)
            gameObjects.add(e);
    }

    @Override
    public void remove(E element) {
        gameObjects.remove(element);
    }

    @Override
    public IIterable getIterator() {
        return new GameCollectionIterator();
    }

    @Override
    public IIterable getIterator(int start) { return new GameCollectionIterator(start); }

    /**
     * Iterator class for the game object collection.
     * @author Robert Wilk
     */
    private class GameCollectionIterator
      implements IIterable {

        /**
         * Current index of the iterator.
         */
        private int index;

        /**
         * Basic constructor.
         */
        public GameCollectionIterator() { this(0); }

        public GameCollectionIterator(int start) { index = start - 1; }

        @Override
        public boolean hasNext() {
            return !(gameObjects.isEmpty() || (index + 1 == gameObjects.size()));
        }

        @Override
        public void reset() {
            index = -1;
        }

        @Override
        public E next() {
            return gameObjects.get(++index);
        }

        @Override
        public int size() {
            return gameObjects.size();
        }

        @Override
        public int position() { return index; }
    }
}
