package iLEX;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TransFunc {
	int AB=0;
	int NB=1;
	int OB=2;
	int alb=3;
	Map<Integer,TransNode> nmap;
	HashSet<Integer> startSet;
	HashMap<Integer,Integer> endSet;
	public TransFunc() {
		nmap=new HashMap<Integer,TransNode>();
		startSet=new HashSet<Integer>();
		endSet=new HashMap<Integer,Integer>();
	}
	public void makeNFA(int[] a,int acctype) {
		startSet.add(a[0]);
		endSet.put(a[1], acctype);
		nmap.get(a[0]).attr=TransNode.start;
		nmap.get(a[1]).attr=TransNode.acc;
	}
	public int[] makenode(char x) {
		TransNode start=new TransNode();
		TransNode end=new TransNode();
		start.addEdge(new TransEdge(x,end.id));
		nmap.put(start.id, start);
		nmap.put(end.id, end);
		int[] re= {start.id,end.id};
		return re;
	}
	public int[] makenode(int set) {
		TransNode start=new TransNode();
		TransNode end=new TransNode();
		start.addEdge(new TransEdge(set,end.id));
		nmap.put(start.id, start);
		nmap.put(end.id, end);
		int[] re= {start.id,end.id};
		return re;
	}
	public int[] and(int[] a,int[] b) {
		TransNode temp=nmap.get(a[1]);
		temp.addEdge(new TransEdge(alb,b[0]));
		int[] re= {a[0],b[1]};
		return re;
	}
	public int[] or(int[] a,int[] b) {
		TransNode start=nmap.get(a[0]);
		TransNode end=new TransNode();
		nmap.put(end.id, end);
		TransNode a2=nmap.get(a[1]);
		TransNode b2=nmap.get(b[1]);
		start.addEdge(new TransEdge(alb,b[0]));
		a2.addEdge(new TransEdge(alb,end.id));
		b2.addEdge(new TransEdge(alb,end.id));
		int[] re= {start.id,end.id};
		return re;
	}
	public int[] closure(int[] a) {
		TransNode start=nmap.get(a[0]);
		TransNode end=nmap.get(a[1]);
		end.addEdge(new TransEdge(alb,start.id));
		int[] re= {start.id,start.id};
		return re;
	}
	/*
	 * functions below is not recommended
	 * */
	public void and(char a,char b) {
		TransNode start=new TransNode();
		TransNode middle=new TransNode();
		start.addEdge(new TransEdge(a,middle.id));
		TransNode end=new TransNode();
		middle.addEdge(new TransEdge(b,end.id));
		nmap.put(start.id, start);
		nmap.put(middle.id, middle);
		nmap.put(end.id, end);
	}
	
	public void or(char a,char b) {
		TransNode start=new TransNode();
		TransNode end=new TransNode();
		start.addEdge(new TransEdge(a,end.id));
		start.addEdge(new TransEdge(b,end.id));
		nmap.put(start.id, start);
		nmap.put(end.id, end);
	}
	
	public void closure(char a) {
		TransNode start=new TransNode();
		start.addEdge(new TransEdge(a,start.id));
		nmap.put(start.id, start);
	}
	public void closure(int start,int end) {
		
	}
}
