package cs.assignments.graphs;
/**
 * Minimum priority queue implemented with a minimum binary heap.
 * This implementation is borrowed, in part, from the books implementation
 * of IndexMinPQ#java. After trying doing my own the book's proved to be
 * more elegant and faster. I altered my code to match the book's where
 * necessary.
 * 
 * @author Robert Wilk
 *
 * @param <K> Priority type.
 */
public class MinPriorityQ<K extends Comparable<K>> {  
    private int size;         
    private int[] reggieHeap;      
    private int[] inverseHeap;      
    private K[] keys;     

    @SuppressWarnings("unchecked")
	public MinPriorityQ(int max) {
        keys = (K[]) new Comparable[max + 1];    
        reggieHeap = new int[max + 1];
        inverseHeap = new int[max + 1];                
        for (int i = 0; i <= max; i++) 
        	inverseHeap[i] = -1;
    }
    /**
     * @see Q#empty();
     */
    public boolean empty() {
        return size == 0;
    }
    /**
     * @see Q#has()
     */
    public boolean has(int element) {
        return inverseHeap[element] != -1;
    }
    /**
     * This method returns the "size" of the queue 
     * based on the actual number of elements in the 
     * MinPrioritQ
     * 
     * @return The number of elements in the queue.
     */
    public int size() {
        return size;
    }
    /**
     * @see Q#enqueue(E element)
     */
    public void enqueue(int element, K priority) {
        size++;
        inverseHeap[element] = size;
        reggieHeap[size] = element;
        keys[element] = priority;
        swim(size);
    }
    /**
     * @see Q#dequeue()
     */
    public int dequeue() { 
        int min = reggieHeap[1];        
        swap(1, size--); 
        sink(1);
        inverseHeap[min] = -1;           
        keys[reggieHeap[size+1]] = null;   
        reggieHeap[size+1] = -1;           
        return min; 
    }
    /**
     * This method swaps the priority of the given element.
     * @param element The element to swap priorities.
     * @param priority The priority to be swapped.
     */
    public void swapPriority(int element, K priority) {
        keys[element] = priority;
        swim(inverseHeap[element]);
        sink(inverseHeap[element]);
    }
    /**
     * This method determines which of the keys at the given indicies.
     * 
     * @param first The first index.
     * @param second The second index.
     * @return True, if first is greater than second, or false.
     */
    private boolean greater(int first, int second) {
        return keys[reggieHeap[first]].compareTo(keys[reggieHeap[second]]) > 0;
    }
    /**
     * Swaps the positions of heap elements.
     * @param first The first element to swap.
     * @param second The second element to swap.
     */
    private void swap(int first, int second) {
        int swap = reggieHeap[first]; 
        reggieHeap[first] = reggieHeap[second]; 
        reggieHeap[second] = swap;
        inverseHeap[reggieHeap[first]] = first; 
        inverseHeap[reggieHeap[second]] = second;
    }
    /**
     * Heapify from the bottom up.
     * @param index The starting position.
     */
    private void swim(int index)  {
        while (index > 1 && greater(index/2, index)) {
            swap(index, index/2);
            index = index/2;
        }
    }
    /**
     * Heapify from the top down.
     * @param index The starting position.
     */
    private void sink(int index) {
        while (2 * index <= size) {
            int other = 2 * index;
            if (other < size && greater(other, other + 1)) 
            	other++;
            if (!greater(index, other)) 
            	break;
            swap(index, other);
            index = other;
        }
    }
}
