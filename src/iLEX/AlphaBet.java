package iLEX;
import java.util.HashSet;
public class AlphaBet {//93
	public static HashSet<Character> alphabet=new HashSet<Character>();//52
	public static HashSet<Character> numberbet=new HashSet<Character>();//10
	public static HashSet<Character> operatorbet=new HashSet<Character>();//31
	//standard init the bets
	public static void init() {
		char x=48;
		while(x<58)
			numberbet.add(x++);
		x=65;
		while(x<91)
			alphabet.add(x++);
		x=97;
		while(x<123)
			alphabet.add(x++);
		x=32;
		while(x<48)
			operatorbet.add(x++);
		x=58;
		while(x<64)
			operatorbet.add(x++);
		x=91;
		while(x<97)
			operatorbet.add(x++);
		x=123;
		while(x<126)
			operatorbet.add(x++);
	}
}
/*
ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
0123456789
[\]^_ `!"#$%&'()*+,-./:;{<|=}>?
*/