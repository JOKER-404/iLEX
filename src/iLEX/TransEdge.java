package iLEX;

public class TransEdge {
	static int AB=0;
	static int NB=1;
	static int OB=2;
	static int alb=3;
	int nodeid;
	boolean issinglechar;//whether its trans by a single char or a char set
	int transSet;
	char transchar;
	public TransEdge(int transSet,int nodeid){
		this.nodeid=nodeid;
		this.issinglechar=false;
		this.transSet=transSet;
	}
	public TransEdge(char transchar,int nodeid) {
		this.nodeid=nodeid;
		this.issinglechar=true;
		this.transchar=transchar;
	}
}
