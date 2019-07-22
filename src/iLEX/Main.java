package iLEX;

import java.util.BitSet;

public class Main {
	public static void main(String[] args) {
		//Main.NFAgenerator();
		Main.testDFA();
	}
	public static void testalphabet() {
		AlphaBet.init();
		for(char x:AlphaBet.alphabet) {
			System.out.print(x);
		}
		System.out.println();
		for(char x:AlphaBet.numberbet) {
			System.out.print(x);
		}
		System.out.println();
		for(char x:AlphaBet.operatorbet)
			System.out.print(x);
	}
	
	public static void NFAgenerator() {
		TransFunc tf=new TransFunc();
		tf.makeNFA(tf.and(tf.or(tf.makenode('a'),tf.makenode('b')),tf.closure(tf.makenode(tf.AB))), 1);
		tf.makeNFA(tf.and(tf.or(tf.makenode('a'),tf.makenode('b')),tf.closure(tf.makenode(tf.AB))), 1);
		TransTable tt=new TransTable(tf);
		for(int i=0;i<tt.tt.length;i++) {
			System.out.print("state "+i+" ");
			for(int j=0;j<128;j++) {
				if(tt.tt[i][j]==-1)
					continue;
				System.out.print((char)j+" "+tt.tt[i][j]+" ");
			}
			System.out.println();
		}
		for(int i:tt.startSet) {
			System.out.println("start state is "+i);
		}
		for(int i:tt.endSet.keySet()) {
			System.out.println("  end state is "+i);
		}
	}
	public static void testDFA() {
		TransFunc tf=new TransFunc();
		tf.makeNFA(tf.and(tf.or(tf.makenode('a'),tf.makenode('b')),tf.closure(tf.makenode(tf.AB))), 1);
		tf.makeNFA(tf.and(tf.or(tf.makenode('a'),tf.makenode('b')),tf.closure(tf.makenode(tf.AB))), 1);
		TransTable tt=new TransTable(tf);
		DFA d=new DFA(tt);
		for(int i=0;i<d.statenum;i++) {
			System.out.print("State "+i+": ");
			Integer[] temp=d.tt.get(i);
			for(int j=0;j<128;j++) {
				if(temp[j]==-1) {
					continue;
				}else {
					System.out.print((char)j+" "+temp[j]+" ");
				}
			}
			System.out.println();
		}
		System.out.print("end state : ");
		for(BitSet x:d.index.keySet()) {
			for(int i:tt.endSet.keySet()) {
				if(x.get(i)) {
					System.out.print(d.index.get(x)+" ");
					break;
				}
			}
		}
	}
}
