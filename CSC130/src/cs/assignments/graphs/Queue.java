package cs.assignments.graphs;
/**
 * This class is a basic, generic, FIFO queue.
 * 
 * @author Robert Wilk
 *
 * @param <E> The element type to be queued.
 */
public class Queue <E extends Comparable<E>> 
extends AbstractQueue <E> {
	// Head and tail of the Queue.
	protected QueueNode head;
	protected QueueNode tail;
	/**
	 * This class is the ListNode specialization of the Queue
	 * 
	 * @author Robert
	 *
	 */
	protected class QueueNode extends ListNode <E> {
		QueueNode next;
		
		public QueueNode(E element) { super(element); }
		
		public QueueNode(E element, QueueNode next) {
			super(element);
			this.next = next;
		}
		/**
		 * @see ListNode#getNext()
		 */
		@Override
		ListNode<E> getNext() { return next; }
	}
	/**
	 * @see Q#enqueue(Comparable)
	 */
	@Override
	public void enqueue(E element) {
		if(head == null) {
			head = tail = new QueueNode(element);
		} else {
			tail.next = new QueueNode(element);
			tail = tail.next;
		}
	}
	/**
	 * @see Q#getHead()
	 */
	@Override
	public ListNode<E> getHead() { return head; }
	/**
	 * @see Q#getTail()
	 */
	@Override
	public ListNode<E> getTail() { return tail; }
	/**
	 * @see Q#setHead(ListNode<E> node)
	 */
	@Override
	protected void setHead(ListNode<E> node) { head = (QueueNode)node; }
	/**
	 * @see Q#setTail(ListNode<E> node)
	 */
	@Override
	protected void setTail(ListNode<E> node) { tail = (QueueNode)node; }
}
