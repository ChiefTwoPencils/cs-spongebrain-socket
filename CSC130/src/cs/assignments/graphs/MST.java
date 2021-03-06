package cs.assignments.graphs;

import java.util.Arrays;
/**
 * This class is used to extract the Minimum Spanning Tree form a 
 * WeightedGraph.
 * @author Robert Wilk
 *
 */
public class MST {

	private WeightedGraph graph;
	private MinPriorityQ<Double> candidates;
	private Edge[] shortest;
	private double[] length;
	private boolean[] used;
	private int first;
	/**
	 * This constructor prepares the MST class for extracting the MST
	 * and calls the helper method used to complete its task.
	 * @param graph The WeightedGraph to extract the MST from.
	 */
	public MST(WeightedGraph graph) {
		this.graph = graph;
		shortest = new Edge[this.graph.verticies()];
		used = new boolean[this.graph.verticies()];
		Arrays.fill(used, false);
		length = new double[this.graph.verticies()];
		Arrays.fill(length, Double.POSITIVE_INFINITY);
		length[0] = 0.0;
		candidates = new  MinPriorityQ<>(graph.verticies());
		for (int i = 0; i < graph.adjacencyList().size(); ++i) {
			if (graph.adjacencyList().get(i).size() != 0) {
				candidates.enqueue(i, 0.0);
				first = i;
				break;
			}
		}
		while(!candidates.empty()) 
			visit(graph, candidates.dequeue());
	}
	/**
	 * This method visits an edge, if it hasn't been used it adds it and all
	 * its related verticies to the priority queue. 
	 * @param graph The WeightedGraph to extract the MST from.
	 * @param one The smallest weighted vertex in the priority queue.
	 */
	private void visit(WeightedGraph graph, int one) {
		used[one] = true;
		for(Edge edge : graph.adjacencyList().get(one)) {
			int other = edge.getTheOther(one);
			if(used[other])
				continue;
			if(edge.weight() < length[other]) {
				shortest[other] = edge;
				length[other] = edge.weight();
				if(candidates.has(other)) 
					candidates.swapPriority(other, length[other]);
				else
					candidates.enqueue(other, length[other]);
			}
		}
	}
	/**
	 * This method gets the total weight of the MST from its respective verticies.
	 * @return The total weight of the MST.
	 */
	public double weight() { 
		double weight = 0.0;
		for(double d :  length) {
			if (d == Double.POSITIVE_INFINITY)
				continue;
			weight += d;
		}
		return weight; 
	}	
	/**
	 * This method turns the MST into its WeightedGraph representation
	 * to be used in the GUI.
	 * @return The MST as a WeightedGraph.
	 */
	public WeightedGraph toWeightedGraph() {
		WeightedGraph graph = new WeightedGraph(shortest.length);
		for(int i = first + 1; i < shortest.length; ++i)
			graph.addEdge(shortest[i]);
		return graph;			
	}
	
	public Edge[] getShortest() { return shortest; }
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		Arrays.sort(shortest, first + 1, shortest.length);
		for(int i = first + 1; i < shortest.length; ++i) {
			builder.append(shortest[i]);
			builder.append("\n");
		}
		return builder.toString();
	}
}
