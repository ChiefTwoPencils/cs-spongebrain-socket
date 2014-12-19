package cs.assignments.trees;

import java.util.List;

/**
 * Interface for all trees.
 * @author Robert
 *
 * @param <K> The key type of data value.
 * @param <V> The value type to be stored.
 */
public interface Tree <K extends Comparable<K>, V> {
	
	/**
	 * Public interface for inserting a new key-value 
	 * pair into a tree
	 * @param key The key for the data value.
	 * @param value The data value to be stored.
	 */
	void insert(K key, V value);
	
	/**
	 * Public interface for getting a data value based
	 * on a key.
	 * @param key The key to be searched for.
	 * @return The value mapped to the key or null.
	 */
	V get(K key);
	
	/**
	 * Public interface for removing a data value based
	 * on a key.
	 * @param key The key for the data value to be removed.
	 */
	void remove(K key);
	
	boolean contains(K key);
	
	/**
	 * Public interface for pre-order tree traversal. 
	 * Pre-order: Root-Left-Right
	 * @return A java.util.List containing the values in pre-order. 
	 */
	List<V> traversePreOrder();
	
	/**
	 * Public interface for in-order tree traversal. 
	 * Pre-order: Left-Root-Right
	 * @return A java.util.List containing the values in-order. 
	 */
	List<V> traverseInOrder();
	
	/**
	 * Public interface for pre-order tree traversal. 
	 * Pre-order: Left-Right-Root
	 * @return A java.util.List containing the values in post-order. 
	 */
	List<V> traversePostOrder();	
}
