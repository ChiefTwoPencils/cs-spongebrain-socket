package cs.assignments.graphs;
/**
 * An interface for all non-prioritized queues.
 * 
 * @author Robert Wilk
 *
 * @param <E> The type of element to be queued.
 */
public interface Q <E extends Comparable<E>> {
	/**
	 * This method inserts an element into the queue. 
	 * 
	 * @param element The element to be inserted.
	 */
	public void enqueue(E element);
	/**
	 * This method removes and returns the first element in the
	 * queue.
	 * 
	 * @return The first element in the queue.
	 */
	public E dequeue();
	/**
	 * This method lets the user look at the first element in the
	 * queue without removing it.
	 * 
	 * @return A copy of the first element in the queue.
	 */
	public E peek();
	/**
	 * This method determines if the queue is empty.
	 * 
	 * @return true, if empty, or false.
	 */
	public boolean empty();
	/**
	 * This method determines if the queue contains a certain element.
	 * 
	 * @param element The element to look for.
	 */
	public boolean has(E element);
	/**
	 * This method returns the head of the list backing the queue.
	 * 
	 * @return The head node.
	 */
	public ListNode<E> getHead();
	/**
	 * This method returns the tail of the list backing the queue.
	 * 
	 * @return The tail node.
	 */
	public ListNode<E> getTail();
}
