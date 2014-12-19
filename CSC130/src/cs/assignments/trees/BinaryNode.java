package cs.assignments.trees;

/**
 * Interface for all binary tree nodes.
 * @author Robert Wilk
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public abstract class BinaryNode<K extends Comparable<K>, V> implements Node<K, V> {
	
	K key;
	V value;
	
	abstract BinaryNode<K, V> getLeft();
	abstract BinaryNode<K, V> getRight();
	
	public BinaryNode(K key, V value) {
		this.key = key;
		this.value =  value;
	}
	
	public K getKey()   { return key; }
	public V getValue() { return value; }
	
	/**
	 * Determines if the node is a leaf.
	 * @return
	 */
	public boolean isLeaf() { 
		return getLeft()  == null ? 
			   getRight() == null ? 
					   true : false : false; 
	}	
}
