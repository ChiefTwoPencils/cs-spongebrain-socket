package cs.assignments.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <hr><p><h1>
 * Grader Information: 							</h1></p><p><b>
 * @author Robert Wilk			  				<br>
 * CSc 130 - Section 4 			  				<br>
 * Assignment 3 - RedBlackTree	 				<br>
 * 11-24-2014				 	  				<br>
 * 								  				<br>
 * Source File: RedBlackTree.java	 			<br>
 * 
 * </b></p>
 * <p><b><i>
 * Running Instructions:		</i></b><br>
 * After opening a command or terminal window, navigate to the top level directory
 * where the RedBlackTree.java file is located. All the files needed to run the
 * the program are compiled and ready. You can execute the java class file using 
 * "<b>java RedBlackTree</b>" on the command line. 					</p><p><br><br>
 * The program provides an user interface which allows them to choose an operation
 * in a red-black tree. For ease of use, the interface accepts the value entered
 * by the user and copies it into the key as well.
 * 
 * @author Robert Wilk
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class RedBlackTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {

	private RedBlackNode root;

	/**
	 * Enumerator for RedBlackNode Color property.
	 * @author Robert Wilk
	 */
	enum Color {  RED, BLACK; }
	
	/**
	 * Binary search tree node with a color property.
	 * @author Robert Wilk
	 */
	protected class RedBlackNode extends BinaryNode<K, V> {

		Color color;
		RedBlackNode left;
		RedBlackNode right;
		RedBlackNode(K key, V value, Color color) {
			super(key, value);
			this.color = color;
		}
		@Override
		BinaryNode<K, V> getLeft() { return left; }
		
		@Override
		BinaryNode<K, V> getRight() { return right; }
	}
	
	/**
	 * Main menu interface for testing.
	 * @param args Not used
	 */
	public static void main(String...args) {
		
		RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();
		
		/*
		 * Display menu, get user input, and perform operations based on
		 * the request.
		 */
		Scanner scanner = new Scanner(System.in);
		while (true) {		
			System.out.print("\n\n" +
					"1) Insert a node.\n" +
					"2) Remove a node.\n" +
					"3) Display pre-order traversal.\n" +
					"4) Display in-order traversal.\n" +
					"5) Display post-order traversal.\n" +
					"6) Exit.\n" +
					"Please choose an option: "
			);
			switch (Integer.parseInt(scanner.nextLine())) {
		
			case 1:
				System.out.print("Enter the key to insert: ");
				int i = Integer.parseInt(scanner.nextLine());
				rbt.insert(i, i);
				break;
			case 2:
				System.out.print("Enter the key to remove: ");
				//System.out.print("Sorry, remove hasn't been implemented yet.");
				rbt.remove(Integer.parseInt(scanner.nextLine()));
				break;
			case 3:
				System.out.println("Pre-order traversal:");
				for (RedBlackTree<Integer, Integer>.RedBlackNode n : rbt.traversePreOrderWithColor())
					System.out.printf(n.getValue() + "(" + n.color + "), ");
				System.out.println();
				break;
			case 4:
				System.out.println("In-order traversal:");
				for (RedBlackTree<Integer, Integer>.RedBlackNode n : rbt.traverseInOrderWithColor())
					System.out.printf(n.getValue() + "(" + n.color + "), ");
				System.out.println();
				break;
			case 5:
				System.out.println("Post-order traversal:");
				for (RedBlackTree<Integer, Integer>.RedBlackNode n : rbt.traversePostOrderWithColor())
					System.out.printf(n.getValue() + "(" + n.color + "), ");
				System.out.println();
				break;
			case 6:
				scanner.close();
				return;
			default:
				System.out.println("You've entered an invalid option; " +
								   "please try again...\n\n");
				break;
			}
		}
	}

	/**
	 * Public insert interface.
	 * @param key Key to map value to.
	 * @param value Value to be inserted.
	 */
	@Override
	public void insert(K key, V value) {
		root = insert(root, new RedBlackNode(key, value, Color.RED));
		root.color = Color.BLACK;
	}
	
	/**
	 * Recursive insert helper.
	 * @param root The current root.
	 * @param current The new node to be inserted.
	 * @return Nodes to rebuild the tree.
	 */
	private RedBlackNode insert(RedBlackNode root, RedBlackNode current) {
		if (root == null) 
			return current;
		int comp = root.key.compareTo(current.key);
		if (comp > 0)
			root.left = insert(root.left, current);
		else if (comp < 0)
			root.right = insert(root.right, current);
		else
			root.value = current.value;
		if (isRedNode(root.right) && !isRedNode(root.left))
			root = rotateLeft(root);
		if (isRedNode(root.left) && isRedNode(root.left.left))
			root = rotateRight(root);
		if (isRedNode(root.left) && isRedNode(root.right))
			flipColors(root);
		return root;
	}

	/**
	 * Gets the value of the given search key.
	 * @param key The search key in question.
	 */
	@Override
	public V get(K key) { return find(root, key).value; }
	
	/**
	 * Determines if the tree contains the search key.
	 * @param key The search key in question.
	 */
	public boolean contains(K key) { return contains(root, key); }
	
	/**
	 * Public interface for the remove method.
	 * @param key The key of the node to be removed.
	 */
	@Override
	public void remove(K key) { 
		if(!contains(key))
			return;
		if(!isRedNode(root.left) && !isRedNode(root.right))
			root.color = Color.RED;		
		root = remove(root, key);
		if(root != null)
			root.color = Color.BLACK;			
	}
	
	/**
	 * Recursive helper for the remove public interface.
	 * @param root The root of the current sub-tree.
	 * @param key The key of the node to be removed.
	 * @return The nodes to rebuild the tree from the bottom up.
	 */
	public RedBlackNode remove(RedBlackNode root, K key) {
		if(root.getKey().compareTo(key) > 0) {
			if (!isRedNode(root.left) && !isRedNode(root.left.left))
				root = shiftLeft(root);
			root.left = remove(root.left, key);
		} else {
			if(isRedNode(root.left))
				root = rotateRight(root);
			if(root.getKey().compareTo(key) == 0 && (root.right == null))	
				return null;
			if(!isRedNode(root.right) && !isRedNode(root.right.left))
                root = shiftRight(root);
			if(root.getKey().compareTo(key) == 0) {
				RedBlackNode rbn = (RedBlackNode)min(root.right);
	            root.key = rbn.key;
	            root.value = rbn.value;
	            root.right = removeMin(root.right);
			} else 
				root.right = remove(root.right, key);
		}
		return balance(root);
	}

	/**
	 * Removes the minimum node from the sub-tree containing all
	 * the keys larger than the caller.
	 * @param root The root of the current sub-tree.
	 * @return The nodes to rebuild the tree from the bottom up.
	 */
	private RedBlackNode removeMin(RedBlackNode root) {
		if(root.left == null)
			return null;
		if (!isRedNode(root.left) && !isRedNode(root.left.left))
            root = shiftLeft(root);
        root.left = removeMin(root.left);
        return balance(root);
	}
	
	/**
	 * Ensures perfect balance of the tree after a deletion has
	 * occurred. 
	 * @param root The root of the current sub-tree.
	 * @return The nodes to rebuild the tree from the bottom up.
	 */
	private RedBlackNode balance(RedBlackNode root) {
		if (isRedNode(root.right))
			root = rotateLeft(root);
        if (isRedNode(root.left) && isRedNode(root.left.left)) 
        	root = rotateRight(root);
        if (isRedNode(root.left) && isRedNode(root.right))     
           	flipColors(root);
        return root;
	}
	
	/**
	 * Shifts the node to accommodate deletions.
	 * @param root The root of the current sub-tree.
	 * @return The nodes to rebuild the tree from the bottom up.
	 */
	private RedBlackNode shiftLeft(RedBlackNode root) {
		flipColors(root);
		if (isRedNode(root.right.left)) { 
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
        }
        return root;
	}
	
	/**
	 * Shifts the node to accommodate deletions.
	 * @param root The root of the current sub-tree.
	 * @return The nodes to rebuild the tree from the bottom up.
	 */
	private RedBlackNode shiftRight(RedBlackNode root) {
		flipColors(root);
		if (isRedNode(root.left.left)) { 
            root = rotateRight(root);
        }
        return root;
	}
	
	/**
	 * Performs the split and promote functionality.
	 * @param root The root of the current sub-tree to be flipped.
	 */
	private void flipColors(RedBlackNode root) {
		root.color = 
				root.color == Color.RED ? 
						Color.BLACK : Color.RED;
		root.left.color = 
				root.left.color == Color.RED ? 
						Color.BLACK : Color.RED;
		root.right.color =
				root.right.color == Color.RED ? 
						Color.BLACK : Color.RED;
	}
	
	/**
	 * Rotation for a node with a RED right child.
	 * @param root The current node.
	 * @return The rotated root.
	 */
	private RedBlackNode rotateLeft(RedBlackNode root) {
		RedBlackNode rbn = root.right;
		root.right = rbn.left;
		rbn.left = root;
		rbn.color = rbn.left.color;
		rbn.left.color = Color.RED;
		return rbn;
	}
	
	/**
	 * Rotation for a node with a RED left child who has
	 * a RED left child.
	 * @param root The current node.
	 * @return The rotated root.
	 */
	private RedBlackNode rotateRight(RedBlackNode root) {
		RedBlackNode rbn = root.left;
		root.left = rbn.right;
		rbn.right = root;
		rbn.color = rbn.right.color;
		rbn.right.color = Color.RED;
		return rbn;
	}

	/**
	 * Determines if the node is a RED node.
	 * @param root The node in question.
	 * @return The answer.
	 */
	private boolean isRedNode(RedBlackNode root) { 
		return (root == null) ? 
				false : root.color == Color.RED; 
	}

	/**
	 * Performs the same functionality as the interface's, but
	 * it stores the node instead of values to accommodate the 
	 * printing of the node's color as well.
	 * @return The list containing the nodes.
	 */
	public List<RedBlackNode> traversePreOrderWithColor() {
		List<RedBlackNode> list = new ArrayList<>();
		traversePreOrderWithColor(root, list);
		return list;
	}

	/**
	 * Recursively adds the elements of the tree into a list
	 * in-order - Left-Root-Right.
	 * @param root The root of the current subtree.
	 * @param values The list of node values.
	 */
	protected void traversePreOrderWithColor(RedBlackNode root, List<RedBlackNode> values) {
		if (root == null)
			return;
		values.add(root);
		traversePreOrderWithColor((RedBlackNode)root.getLeft(), values);
		traversePreOrderWithColor((RedBlackNode)root.getRight(), values);
	}
	
	/**
	 * Performs the same functionality as the interface's, but
	 * it stores the node instead of values to accommodate the 
	 * printing of the node's color as well.
	 * @return The list containing the nodes.
	 */
	public List<RedBlackNode> traverseInOrderWithColor() {
		List<RedBlackNode> list = new ArrayList<>();
		traverseInOrderWithColor(root, list);
		return list;
	}
	
	/**
	 * Recursively adds the elements of the tree into a list
	 * in-order - Left-Root-Right.
	 * @param root The root of the current subtree.
	 * @param values The list of node values.
	 */
	protected void traverseInOrderWithColor(RedBlackNode root, List<RedBlackNode> values) {
		if (root == null)
			return;
		traverseInOrderWithColor((RedBlackNode)root.getLeft(), values);
		values.add(root);
		traverseInOrderWithColor((RedBlackNode)root.getRight(), values);
	}
	
	/**
	 * Performs the same functionality as the interface's, but
	 * it stores the node instead of values to accommodate the 
	 * printing of the node's color as well.
	 * @return The list containing the nodes.
	 */
	public List<RedBlackNode> traversePostOrderWithColor() {
		List<RedBlackNode> list = new ArrayList<>();
		traversePostOrderWithColor(root, list);
		return list;
	}
	
	/**
	 * Recursively adds the elements of the tree into a list
	 * in-order - Left-Root-Right.
	 * @param root The root of the current subtree.
	 * @param values The list of node values.
	 */
	protected void traversePostOrderWithColor(RedBlackNode root, List<RedBlackNode> values) {
		if (root == null)
			return;
		traversePostOrderWithColor((RedBlackNode)root.getLeft(), values);
		traversePostOrderWithColor((RedBlackNode)root.getRight(), values);
		values.add(root);
	}
	
	/**
	 * @see BinarySearchTree.traverseInOrder
	 */
	@Override
	public List<V> traverseInOrder() {
		List<V> list = new ArrayList<>();
		traverseInOrder(root, list);
		return list;
	}

	/**
	 * @see BinarySearchTree.traversePostOrder
	 */
	@Override
	public List<V> traversePostOrder() {
		List<V> list = new ArrayList<>();
		traversePostOrder(root, list);
		return list;
	}

	/**
	 * @see BinarySearchTree.traversePostOrder
	 */
	@Override
	public List<V> traversePreOrder() {
		List<V> list = new ArrayList<>();
		traversePreOrder(root, list);
		return list;
	}
}
