import java.util.*;
import java.io.*;



class MyMap {
	
	public MyMap() {
		
	}
	static class elem {
		String key;
		String value;
		elem next;
		elem(String a, String b) {
			key = a;
			value = b;
			next  = null;
		}
	}
	final static int P = 37;
	private int size = 0;
	elem[] hash_table = new elem[1];
	
	private int get_hash(String s) {
		long st = 1;
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = (int)((long)((hash + (s.charAt(i) - 'A' + 1) * st)) % hash_table.length);
			st = (st * P) % hash_table.length;
		}
		//System.out.println(s);
		return hash % hash_table.length;
	}
	
	public void insert(String a, String b) {
		 if (size >= hash_table.length) {
	        	change_size(hash_table.length * 2);
	        }
		size++;
		int pos = get_hash(a);
		elem p = hash_table[pos];
		if (hash_table[pos] == null) {
			hash_table[pos] = new elem(a, b);
			return;
		}
		while (p.next != null) {
			p = p.next;
		}
		p.next = new elem(a, b);
	}
	public void deleted(String a) {
		size--;
		 if (size < hash_table.length / 2) {
        	change_size(hash_table.length / 2);
        }
		int pos = get_hash(a);
		
		if (hash_table[pos].key.equals(a)) {
			hash_table[pos] = hash_table[pos].next;
			return;
		}
		elem p = hash_table[pos];
		elem pred = p;
		while (!p.key.equals(a)) {
			pred = p;
			p = p.next;
		}
		pred.next = p.next;
	}
	public String exist(String a) {
		int pos = get_hash(a);
		elem p = hash_table[pos];
		while (p != null) {
			if (p.key.equals(a)) {
				return p.value;
			}
			p = p.next;
		}
		return null;
	}
	
	private void change_size(int s) {
		elem[] table = hash_table;
		hash_table = new elem[s];
		for (int i = 0; i < table.length; i++) {
			elem p = table[i];
			while (p != null) {
				insert(p.key, p.value);
				p = p.next;
			}
		}
		
	}
}


public class Map {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("map.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("map.out");
		//PrintWriter out = new PrintWriter(System.out);
		MyMap table = new MyMap();
		while (in.hasNext()) {
			String s = in.next();
			String a = in.next();
			if (s.charAt(0) == 'p') {
				String b;
				b = in.next();
				if (table.exist(a) == null) {
					table.insert(a, b);
				} else {
					table.deleted(a);
					table.insert(a, b);
				}
	        }
	        if (s.charAt(0) == 'd') {
	        	if (table.exist(a) != null) {
	        		table.deleted(a);
	        	}
	        }
	        if (s.charAt(0) == 'g') {
	        	String res = table.exist(a);
	        	if (res == null) {
	        		out.println("none");
	        	} else {
	        		out.println(res);
	        	}
	        }
		}
		out.close();
	}
}
