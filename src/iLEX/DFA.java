package iLEX;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

public class DFA {
	HashMap<BitSet,Integer> index;
	int statenum=0;
	HashMap<Integer,Integer[]> tt;
	HashMap<Integer,Integer> acc;
	//nfa to dfa
	public DFA(TransTable t) {
		int cur=0;
		index=new HashMap<BitSet,Integer>();//bitset indicate what origin state is included, integer is tt index
		tt=new HashMap<Integer,Integer[]>();
		acc=new HashMap<Integer,Integer>();
		BitSet bs=new BitSet();
		HashSet<Integer> ssc=t.albclosure(t.startSet);
		for(int x:ssc) {
			bs.set(x);
		}
		index.put(bs, statenum++);
		while(cur<statenum) {
			Integer[] temp=new Integer[128];
			for(char i=0;i<128;i++) {
				HashSet<Integer> moveclosure=t.move(ssc, i);
				if(moveclosure==null||moveclosure.isEmpty()) {
					temp[i]=-1;
				}else {
					BitSet bsclosure=new BitSet();
					for(int x:moveclosure) {
						bsclosure.set(x);
					}
					int included=-1;
					for(BitSet t0:index.keySet()) {
						if(t0.equals(bsclosure)) {
							included=index.get(t0);
							break;
						}
					}
					if(included==-1) {
						temp[i]=statenum;
						index.put(bsclosure, statenum++);
					}else {
						temp[i]=included;
					}
				}
			}
			tt.put(cur, temp);
			cur++;
			for(BitSet t0:index.keySet()) {
				if(index.get(t0)==cur) {
					ssc.clear();
					for(int xx=0;xx<t0.length();xx++) {
						if(t0.get(xx)) {
							ssc.add(xx);
						}
					}
					break;
				}
			}
		}
	}
}
