import java.util.*;
import java.io.*;

public class Set {
	
	static class elem {
		int x;
		elem next;
		elem(int a) {
			x = a;
			next  = null;
		}
	}
	//final static int MOD = 37;
	final static int N = 1000007;
	//final static int INF = 1000000010;
	static elem[] hash_table = new elem[N];
	
	static int get_hash(int a) {
		return Math.abs(a) % N;
		//int P = 31; 
		//double A = 0.618033;
		//int h = (int) (P * ((Math.abs(a) * A) % 1));
		//return h % N;
	}
	
	static void insert(int a) {
		int pos = get_hash(a);
		elem p = hash_table[pos];
		if (hash_table[pos] == null) {
			hash_table[pos] = new elem(a);
			return;
		}
		while (p.next != null) {
			p = p.next;
		}
		p.next = new elem(a);
	}
	static void deleted(int a) {
		int pos = get_hash(a);
		
		if (hash_table[pos].x == a) {
			hash_table[pos] = hash_table[pos].next;
			return;
		}
		elem p = hash_table[pos];
		elem pred = p;
		while (p.x != a) {
			pred = p;
			p = p.next;
		}
		pred.next = p.next;
	}
	static boolean exist(int a) {
		int pos = get_hash(a);
		elem p = hash_table[pos];
		while (p != null) {
			if (p.x == a) {
				return true;
			}
			p = p.next;
		}
		return false;
	}
	
	static void init() {
		for (int i = 0; i < N; i++) {
			hash_table[i] = null;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("set.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("set.out");
		//PrintWriter out = new PrintWriter(System.out);
	//	init();
		while (in.hasNext()) {
			String s = in.next();
			int a = in.nextInt();
			if (s.charAt(0) == 'i') {
				if (!exist(a)) {
					insert(a);
				}
	        }
	        if (s.charAt(0) == 'd') {
	        	if (exist(a)) {
	        		deleted(a);
	        	}
	        }
	        if (s.charAt(0) == 'e') {
	            out.println(exist(a));
	        }
		}
		out.close();
	}
}
