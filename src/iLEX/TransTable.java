package iLEX;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TransTable {
	int statenum;
	int[][] tt;
	HashSet<Integer> startSet;
	HashMap<Integer,Integer> endSet;
	public TransTable(TransFunc tf) {
		tt=new int[tf.nmap.size()][128];
		startSet=tf.startSet;
		endSet=tf.endSet;
		for(int i=0;i<tf.nmap.size();i++) {
			for(int j=0;j<128;j++)
				tt[i][j]=-1;//-1 means nowhere to go
		}
		//build the NFA state transmission table
		for(int i=0;i<tf.nmap.size();i++) {
			TransNode cur=tf.nmap.get(i);
			for(TransEdge edge:cur.EdgeSet) {
				if(edge.issinglechar) {
					tt[i][edge.transchar]=edge.nodeid;
				}else {
					if(edge.transSet==TransEdge.alb)
						tt[i][0]=edge.nodeid;
					if(edge.transSet==TransEdge.AB) {
						for(int j=65;j<91;j++)
							tt[i][j]=edge.nodeid;
						for(int j=97;j<123;j++)
							tt[i][j]=edge.nodeid;
					}
					if(edge.transSet==TransEdge.NB) {
						for(int j=48;j<58;j++)
							tt[i][j]=edge.nodeid;
					}
					if(edge.transSet==TransEdge.OB) {
						int x=32;
						while(x<48)
							tt[i][x++]=edge.nodeid;
						x=58;
						while(x<64)
							tt[i][x++]=edge.nodeid;
						x=91;
						while(x<97)
							tt[i][x++]=edge.nodeid;
						x=123;
						while(x<126)
							tt[i][x++]=edge.nodeid;
					}
				}
			}
		}
	}
	public HashSet<Integer> albclosure(int s) {
		HashSet<Integer> set=new HashSet<Integer>();
		set.add(s);
		while(tt[s][0]!=-1) {
			if(!set.add(tt[s][0]))
				return set;
			s=tt[s][0];
		}
		return set;
	}
	public HashSet<Integer> albclosure(int[] s){
		HashSet<Integer> set=new HashSet<Integer>();
		for(int x:s) {
			set.addAll(this.albclosure(x));
		}
		return set;
	}
	public HashSet<Integer> albclosure(HashSet<Integer> s){
		HashSet<Integer> set=new HashSet<Integer>();
		for(Integer x:s) {
			set.addAll(this.albclosure(x));
		}
		return set;
	}
	public HashSet<Integer> move(int s, char x){
		HashSet<Integer> set=new HashSet<Integer>();
		s=tt[s][x];
		if(s==-1)
			return set;
		set=this.albclosure(s);
		return set;
	}
	public HashSet<Integer> move(HashSet<Integer> s,char x){
		HashSet<Integer> set=new HashSet<Integer>();
		for(int s0:s) {
			set.addAll(this.move(s0,x));
		}
		return set;
	}
}
