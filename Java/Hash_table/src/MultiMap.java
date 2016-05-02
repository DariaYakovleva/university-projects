import java.util.*;
import java.io.*;

public class MultiMap {
    FastScanner in;
    //Scanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
    	MyMultiMap table = new MyMultiMap();
		while (in.hasNext()) {
			String s = in.next();
			String a = in.next();
			if (s.charAt(0) == 'p') {
				String b;
				b = in.next();
				//if (table.exist(a) == null)
					table.insert(a, b);
	        }
	        if (s.charAt(0) == 'd') {
	        	if (s.length() == 9) {
	        		if (table.exist(a) != null) {
	        			table.deleteAll(a);
	        		}
	        	} else {
	        		String b = in.next();
	        		if (table.exist(a) != null) {	
	        			table.deleted(a, b);
	        		}
	        	}
	        }
	        if (s.charAt(0) == 'g') {
	        	MyMap2 res = table.exist(a);
	        	if (res != null) {
	        	ArrayList<String> List = new ArrayList<String>();
	        	for (int i = 0; i < res.hash_table.length; i++) {
	    			MyMap2.Elem p = res.hash_table[i];
	    			while (p != null) {
	    				//out.print(p.key+" ");
	    				List.add(p.key);
	    				p = p.next;
	    			}
	    		}
	        	Collections.sort(List);
	        	out.print(List.size()+" ");
	        	for (int i = 0; i < List.size(); i++) {
	        		out.print(List.get(i) + " ");
	        	}
	        	out.println();
	        	} else {
	        		out.println(0+" ");
	        	}
	        	
	        }
		}
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("multimap.in"));
            out = new PrintWriter(new File("multimap.out"));
            //in = new Scanner(System.in);
            //out = new PrintWriter(System.out);
            solve();
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        boolean hasNext() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return false;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
 
        String next() {
            return hasNext() ? st.nextToken() : null;
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
 
    public static void main(String[] arg) {
        new MultiMap().run();
    }
}



class MyMap2 {
	
	public MyMap2() {
		
	}
	static class Elem {
		String key;
		Elem next;
		Elem(String a) {
			key = a;
			next  = null;
		}
	}
	final static int P = 37;
	final static int MOD = 1000007;
	public int size = 0;
	Elem[] hash_table = new Elem[1];
	
	private int hashCode(String s) {
		long st = 1;
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = (int)((long)((hash + (s.charAt(i) - 'A' + 1) * st)) % MOD);
			st = (st * P) % MOD;
		}
		return hash;
	}
	
	private int getHash(int a) {
		return a % hash_table.length;
	}
	
	public void insert(String a) {
		int pos = getHash(hashCode(a));
		Elem p = hash_table[pos];
		hash_table[pos] = new Elem(a);
		hash_table[pos].next = p;
		size++;
		 if (size >= hash_table.length) {
	        	changeSize(hash_table.length * 2);
	        }
	}
	public void deleted(String a) {
		
		int pos = getHash(hashCode(a));
		
		if (hash_table[pos].key.equals(a)) {
			hash_table[pos] = hash_table[pos].next;
			size--;
			 if (size < hash_table.length / 2) {
	        	changeSize(hash_table.length / 2);
	        }
			return;
		}
		Elem p = hash_table[pos];
		Elem pred = p;
		while (!p.key.equals(a)) {
			pred = p;
			p = p.next;
		}
		pred.next = p.next;
		size--;
		 if (size < hash_table.length / 2) {
        	changeSize(hash_table.length / 2);
        }
	}
	public String exist(String a) {
		int pos = getHash(hashCode(a));
		Elem p = hash_table[pos];
		while (p != null) {
			if (p.key.equals(a)) {
				return p.key;
			}
			p = p.next;
		}
		return null;
	}
	
	private void changeSize(int s) {
		Elem[] table = hash_table;
		hash_table = new Elem[s];
		size = 0;
		for (int i = 0; i < table.length; i++) {
			Elem p = table[i];
			while (p != null) {
				insert(p.key);
				p = p.next;
			}
		}
	}
}

class MyMultiMap {
	
	public MyMultiMap() {
		
	}
	static class Elem {
		String key;
		MyMap2 map;
		Elem next;
		Elem(String a, String b) {
			key = a;
			map = new MyMap2();
			map.insert(b);
			next  = null;
		}
	}
	final static int P = 37;
	final static int MOD = 1000007;
	private int size = 0;
	Elem[] hash_table = new Elem[1];
	
	private int hashCode(String s) {
		long st = 1;
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = (int)((long)((hash + (s.charAt(i) - 'A' + 1) * st)) % MOD);
			st = (st * P) % MOD;
		}
		return hash;
	}
	
	private int getHash(int a) {
		return a % hash_table.length;
	}
	
	public void insert(String a, String b) {
		 
		int pos = getHash(hashCode(a));
		Elem p = hash_table[pos];
		if (hash_table[pos] == null) {
			hash_table[pos] = new Elem(a, b);
			size++;
			if (size >= hash_table.length) {
		        	changeSize(hash_table.length * 2);
		        }
			return;
		}
		if ((p.key.equals(a)) && (p.map.exist(b) == null)) {
			p.map.insert(b);
			return;
		}
		while (p.next != null) {
			p = p.next;
			if ((p.key.equals(a)) && (p.map.exist(b) == null)) {
				p.map.insert(b);
				return;
			}
		}
		p.next = new Elem(a, b);
		size++;
		if (size >= hash_table.length) {
	        	changeSize(hash_table.length * 2);
	        }
	}
	
	public void deleted(String a, String b) {
		
		int pos = getHash(hashCode(a));
		
		if (hash_table[pos].key.equals(a)) {
			if (hash_table[pos].map.exist(b) != null) {
				hash_table[pos].map.deleted(b);
				if (hash_table[pos].map == null) {
					size--;
					if ((size < hash_table.length / 2) && (hash_table.length / 2 > 0)) {
			        	changeSize(hash_table.length / 2);
			        }
				}
			}
			return;
		}
		Elem p = hash_table[pos];
		while ((p != null) && (!p.key.equals(a))) {
			p = p.next;
		}
		if ((p != null) && (p.map.exist(b) != null)) {
			p.map.deleted(b);
			if (p.map == null) {
				size--;
				if ((size < hash_table.length / 2) && (hash_table.length / 2 > 0)) {
		        	changeSize(hash_table.length / 2);
		        }
			}
		}
	}
	
	public void deleteAll(String a) {
		
		int pos = getHash(hashCode(a));
		if (hash_table[pos] == null) {
			return;
		}
		if (hash_table[pos].key.equals(a)) {
			hash_table[pos] = hash_table[pos].next;
			size--;
			if ((size < hash_table.length / 2) && (hash_table.length / 2 > 0)) {
	        	changeSize(hash_table.length / 2);
	        }
			return;
		}
		Elem p = hash_table[pos];
		Elem pred = p;
		while (!p.key.equals(a)) {
			pred = p;
			p = p.next;
		}
		pred.next = p.next;
		size--;
		if ((size < hash_table.length / 2) && (hash_table.length / 2 > 0)) {
        	changeSize(hash_table.length / 2);
        }
	}
	
	public MyMap2 exist(String a) {
		int pos = getHash(hashCode(a));
		Elem p = hash_table[pos];
		while (p != null) {
			if ((p.key != null) && (p.key.equals(a))) {
				return p.map;
			}
			p = p.next;
		}
		return null;
	}
	
	private void changeSize(int s) {
		Elem[] table = hash_table;
		hash_table = new Elem[s];
		size = 0;
		for (int i = 0; i < table.length; i++) {
			Elem p = table[i];
			while (p != null) {
				
				p = p.next;
			}
		}
		
	}
}
