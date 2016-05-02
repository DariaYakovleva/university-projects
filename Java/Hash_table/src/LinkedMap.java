import java.util.*;
import java.io.*;



class MyLinkedMap {
	
	public MyLinkedMap() {
		
	}
	static class elem {
		String key;
		String value;
		elem next;
		String next_add;
		String prev_add;
		elem(String a, String b) {
			key = a;
			value = b;
			next  = null;
			next_add = null;
		}
	}
	final static int P = 37;
	final static int MOD = 1000007;
	elem[] hash_table = new elem[99777];
	String prev = null;
	
	private int get_hash(String s) {
		long st = 1;
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = (int)((long)((hash + (s.charAt(i)) * st)) % MOD);
			st = (st * P) % MOD;
		}
		//System.out.println(s);
		return hash % hash_table.length;
	}
	
	public void insert(String a, String b) {

		int pos = get_hash(a);
		elem p = hash_table[pos];
		if (hash_table[pos] == null) {
			hash_table[pos] = new elem(a, b);
			p = hash_table[pos];
		} else {
			while (p.next != null) {
				p = p.next;
			}
			p.next = new elem(a, b);
			p = p.next;
		}
		
		if (prev != null) {
			elem aa = exist(prev);
			aa.next_add = p.key;
		}
		p.prev_add = prev; 
		prev = p.key;
	}
	public void change(String a, String b) {

		int pos = get_hash(a);
		elem p = hash_table[pos];
		while (p != null) {
			if (p.key.equals(a)) {
				p.value = b;
				return;
			}
			p = p.next;
		}
	}
	
	public void deleted(String a) {
		int pos = get_hash(a);
		elem p = hash_table[pos];
		
		if (hash_table[pos].key.equals(a)) {
			if (p.prev_add != null) {
				exist(p.prev_add).next_add = p.next_add;
			}
			if (p.next_add != null) {
				exist(p.next_add).prev_add = p.prev_add;
			}
			if (p.key.equals(prev)) {
				prev = exist(prev).prev_add;
			}
			hash_table[pos] = hash_table[pos].next;
		} else {
			elem pred = p;
			while (!p.key.equals(a)) {
				pred = p;
				p = p.next;
			}
			if (p.prev_add != null) {
				exist(p.prev_add).next_add = p.next_add;
			}
			if (p.next_add != null) {
				exist(p.next_add).prev_add = p.prev_add;
			}
			if (p.key.equals(prev)) {
				prev = exist(prev).prev_add;
			}
			pred.next = p.next;
		}
	}
	public elem exist(String a) {
		int pos = get_hash(a);
		elem p = hash_table[pos];
		while (p != null) {
			if (p.key.equals(a)) {
				return p;
			}
			p = p.next;
		}
		return new elem(null, null);
	}
	
	
	public String next(String a) {
		elem res = exist(a);
		if ((res.key == null) || (res.next_add == null)) {
			return "none";
		}
		res = exist(res.next_add);
		if ((res.key == null) || (res.value == null)) {
			return "none";
		}
		return res.value;
	}
	public String prev(String a) {
		elem res = exist(a);
		if ((res.key == null) || (res.prev_add == null)){
			return "none";
		}
		res = exist(res.prev_add);
		if ((res.key == null) || (res.value == null)) {
			return "none";
		}
		return res.value;
	}
}


public class LinkedMap {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("linkedmap.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("linkedmap.out");
		//PrintWriter out = new PrintWriter(System.out);
		MyLinkedMap table = new MyLinkedMap();
		while (in.hasNext()) {
			String s = in.next();
			String a = in.next();
			if ((s.charAt(0) == 'p') && (s.charAt(1) == 'u')) {
				String b;
				b = in.next();
				if (table.exist(a).key == null) {
					table.insert(a, b);
				} else {
					table.change(a, b);
				}
	        }
	        if (s.charAt(0) == 'd') {
	        	if (table.exist(a).key != null) {
	        		table.deleted(a);
	        	}
	        }
	        if (s.charAt(0) == 'g') {
	        	String res = table.exist(a).value;
	        	if (res == null) {
	        		out.println("none");
	        	} else {
	        		out.println(res);
	        	}
	        }
	        if (s.charAt(0) == 'n') {
	        	String res = table.next(a);
	        	out.println(res);
	        }
	        if ((s.charAt(0) == 'p') && (s.charAt(1) == 'r')) {
	        	String res = table.prev(a);
	        	out.println(res);
	        }
		}
		out.close();
	}
}
