package cs.assignments.trees;

import java.util.List;


/**
 * This source file is the base class for all binary trees.
 * @author Robert Wilk
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public abstract class AbstractBinaryTree<K extends Comparable<K>, V> implements Tree<K, V> {
	
	/**
	 * This determines if the tree contains the given key.
	 * @param root The root of the current sub-tree.
	 * @param key The search key.
	 * @return Whether the tree contains the key or not.
	 */
	public boolean contains(BinaryNode<K, V> root, K key) { return (find(root, key) != null); }
	
	/**
	 * Finds the node through key comparison and returns it, if possible.
	 * @param root The root of the current sub-tree.
	 * @param key The search key.
	 * @return The node with the key if found or null.
	 */
	protected BinaryNode<K, V> find(BinaryNode<K, V> root, K key) {
		if (root == null)
			return root;
		int comp = root.key.compareTo(key);
		if (comp > 0)
			return find(root.getLeft(), key);
		if (comp < 0)
			return find(root.getRight(), key);
		return root;
	}
	
	/**
	 * Finds the largest key in the tree.
	 * @param root The root of the current sub-tree
	 * @return The node mapped to the largest key or null.
	 */
	protected BinaryNode<K, V> max(BinaryNode<K, V> root) {
		while (root.getRight() != null) 
			root = root.getRight();
		return root;
	}
	
	/**
	 * Finds the smallest key in the tree.
	 * @param root The root of the current sub-tree.
	 * @return The node mapped to the smallest key or null.
	 */
	protected BinaryNode<K, V> min(BinaryNode<K, V> root) {
		if (root.getLeft() == null)
			return root;
		return min(root.getLeft());
	}
	
	/**
	 * Recursively adds the elements of the tree into a list
	 * in pre-order - Root - Left - Right. 
	 * @param root The root of the current sub-tree.
	 * @param values List of node values in pre-order.
	 */
	protected void traversePreOrder(BinaryNode<K, V> root, List<V> values) {
		if (root == null) 
			return;
		values.add(root.getValue());
		traversePreOrder(root.getLeft(), values);
		traversePreOrder(root.getRight(), values);
	}

	/**
	 * Recursively adds the elements of the tree into a list
	 * in-order - Left-Root-Right.
	 * @param root The root of the current subtree.
	 * @param values The list of node values.
	 */
	protected void traverseInOrder(BinaryNode<K, V> root, List<V> values) {
		if (root == null)
			return;
		traverseInOrder(root.getLeft(), values);
		values.add(root.getValue());
		traverseInOrder(root.getRight(), values);
	}
	
	/**
	 * Recursively adds the elements of the tree in
	 * post-order - Left-Right-Root.
	 * @param root The root of the current subtree.
	 * @param values The list of node values.
	 */
	protected void traversePostOrder(BinaryNode<K, V> root, List<V> values) {
		if (root == null)
			return;
		traversePostOrder(root.getLeft(), values);
		traversePostOrder(root.getRight(), values);
		values.add(root.value);
	}
}
