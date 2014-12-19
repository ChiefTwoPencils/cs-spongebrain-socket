package cs;

public abstract class ArrayUnion
implements Union {
	
	/**
	 * Number of unique component ids.
	 */
	protected int count;
	/**
	 * Array of components.
	 */
	protected int id[];
	
	/**
	 * This constructor creates an array of n elements with unique
	 * component numbers to demonstrate the union functionality.
	 * @param n number of elements in the array
	 */
	public ArrayUnion(int n) {
		id = new int[n];
		for(int i = 0; i < n; ++i)
			id[i] = i;
	}
	
	/**
	 * This method returns the number of unique components.
	 * @return Number of components.
	 */
	public int count() { return count; }
}
