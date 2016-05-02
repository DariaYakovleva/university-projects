import java.util.*;
import java.io.*;

public class Test {
	
	static Random rnd = new Random();
	
	public static void main(String[] args) throws IOException {
		//Scanner in = new Scanner(new File("set.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("multimap.in");
		//PrintWriter out = new PrintWriter(System.out);
		int n = 1000;
		//out.println(n);
		for (int i = 0; i < n; i++) {
			int c = rnd.nextInt() % 3;
			int m = 20;
			String a = "";
			for (int j = 0; j < m; j++) {
				a += (char)(Math.abs(rnd.nextInt()) % 26 + 'a');
			}
			for (int k = 0; k< 100; k++) {
			if (c == 0) {
				String aa = "";
	
				int mm = 20;
				aa = "";
				for (int j = 0; j < mm; j++) {
					aa += (char)(Math.abs(rnd.nextInt()) % 26 + 'a');
				}
				out.println("put "+a+ " "+aa);
				out.println("get "+a);
				//out.println("delete "+a+ " "+aa);
				//out.println("get "+a);
				//out.println("next "+a);
			}
			if (c == 1) {
				//out.println("get "+a);	
				int mm = 20;
				String aa = "";
				for (int j = 0; j < mm; j++) {
					aa += (char)(Math.abs(rnd.nextInt()) % 26 + 'a');
				}
				out.println("get "+a);
				out.println("delete "+a+ " "+aa);
			}
			if (c == 2) {
				out.println("deleteall "+a);
			}
			}
		}
		out.close();
	}
}
