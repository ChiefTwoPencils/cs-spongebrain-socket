package cs.assignments.trees;

/**
 * This class provides a basic Node class for all trees to extend.
 * @author Robert
 *
 * @param <K> The key type for the data value. 
 * @param <V> The value type to be stored.
 */
public abstract class AbstractTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/**
	 * Basic node for all trees.
	 */
	protected class Node {
		K key;
		V value;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		// Getters
		V getValue() { return value; }
		K getKey()   { return key; }
	}
}
