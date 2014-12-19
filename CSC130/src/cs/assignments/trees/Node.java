package cs.assignments.trees;


/**
 * Interface for all tree nodes.
 * @author Robert Wilk
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public interface Node<K extends Comparable<K>, V> {

	K getKey();
	V getValue();
}
