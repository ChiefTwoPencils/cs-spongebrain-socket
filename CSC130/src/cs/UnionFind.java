package cs;

public class UnionFind 
extends ArrayUnion {
	
	public UnionFind(int n) { super(n); }

	/**
	 * <p>This method connects a and b if not already connected by changing the 
	 * component numbers to match.</p>
	 * @param a The source component
	 * @param b The destination component
	 */
	@Override
	public void union(int a, int b) {
		if (!connected(a, b)) {
			for (int i = 0; i < id.length; ++i) {
				if (i == a) continue;
				if (id[i] == id[a]) {
					id[i] = id[b];
					--count;
				}
			}
			id[a] = id[b];
		}
	}
	
	/**
	 * <p>This method returns the component id of p.</p>
	 * @param p The element to find.
	 * @return Either the component id or -1 if not found.
	 */
	@Override
	public int find(int p) {
		if (p >= 0 && p < id.length)
			return id[p];
		return -1;
	}
	
	/**
	 * <p>This method determines if two elements, a and b, are connected.</p>
	 * @param a 
	 * @param b
	 * @return The validity of connectivity of the two parameters.
	 */
	@Override
	public boolean connected(int a, int b) {
		if (id[a] == id[b])
			return true;
		return false;
	}
}
