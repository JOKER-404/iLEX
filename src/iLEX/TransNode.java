package iLEX;

import java.util.HashSet;

public class TransNode {
	static int acc=0;
	static int start=1;
	static int middle=2;
	static int tid=0;
	int id;
	int attr;
	int edgecount=0;
	HashSet<TransEdge> EdgeSet=new HashSet<TransEdge>();
	public TransNode() {
		id=tid++;
		attr=middle;
	}
	public void addEdge(TransEdge e) {
		this.edgecount++;
		this.EdgeSet.add(e);
	}
}
