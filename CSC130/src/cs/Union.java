package cs;

public interface Union {
	
	int find(int p);
	boolean connected(int a, int b);
	void union(int a, int b);
	int count();
}
