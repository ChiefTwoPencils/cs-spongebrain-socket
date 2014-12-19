package cs.assignments.graphs;

import java.text.DecimalFormat;
/**
 * The Edge class for a WeightedGraph.
 * 
 * @author Robert Wilk
 *
 */
public class Edge implements Comparable<Edge> {

	private final double weight;
	private final int one;
	private final int other;
	
	public Edge(int one, int other, double weight) {
		this.one = one;
		this.other = other;
		this.weight = weight;
	}
	/**
	 * Gets one of the verticies of the edge.
	 * 
	 * @return An edge value.
	 */
	public int getOne() { return one; }
	/**
	 * Gets the "other" vertex getOne() doesn't return.
	 * 
	 * @param one The vertex returned by getOne().
	 * @return The other vertex.
	 */
	public int getTheOther(int one) { 
		return this.one == one ? other : this.one; 
	}
	
	public double weight() { return weight; }
	/**
	 * This method compares Edges by their weight and 
	 * determines which is larger.
	 * 
	 * @param other The Edge to compare to this Edge.
	 */
	@Override
	public int compareTo(Edge other) {
		return weight > other.weight ?
				1 : weight < other.weight ?
						-1 : 0;
	}
	
	@Override
	public String toString() {
		return String.format(" %d - %d  %s", one, other, new DecimalFormat("###0.00000").format(weight));
	}
}
