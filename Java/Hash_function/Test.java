import java.util.*;
import java.io.*;

public class Test {
	
	static Random rnd = new Random();
	
	public static void main(String[] args) throws IOException {
		//Scanner in = new Scanner(new File("set.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("set.in");
		//PrintWriter out = new PrintWriter(System.out);
		int n = 500000;
		//out.println(n);
		for (int i = 0; i < n; i++) {
			int c = rnd.nextInt() % 3;
			int a = rnd.nextInt() % 1000000000;
			if (c == 0) {
				out.println("i "+a);
				out.println("e "+a);
			}
			if (c == 1) {
				out.println("e "+a);
			}
			if (c == 2) {
				out.println("d "+a);
				out.println("e "+a);
			}
		}
		out.close();
	}
}
