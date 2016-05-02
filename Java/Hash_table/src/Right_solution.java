import java.util.*;
import java.util.Set;
import java.io.*;
import java.util.Map;

public class Right_solution {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("multimap.in"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("set_right.out");
		//PrintWriter out = new PrintWriter(System.out);
		final int N = 99777;
		 HashSet<String>[] map = new HashSet[N];
		 for (int i = 0; i < N; i++) {
			 map[i] = new HashSet<String>();
		 }
		while (in.hasNext()) {
			String s = in.next();
			String a = in.next();
			int pos = Math.abs(a.hashCode()) % N;
			if (s.charAt(0) == 'p') {
				String b = in.next();
				map[pos].add(b);
	        }
	        if (s.charAt(0) == 'd') {
	        	if (s.length() == 6) {
	        		String b = in.next();
	        		map[pos].remove(b);
	        	} else {
	        		map[pos].clear();
	        	}
	        }
	        if (s.charAt(0) == 'g') {
	            out.print(map[pos].size() + " ");
	            String[] myArray = {};
	        	myArray = map[pos].toArray(new String[map[pos].size()]);
	        	Arrays.sort(myArray);
	        	for (int i = 0; i < myArray.length; i++) {
	        		out.print(myArray[i] + " ");
	        	}
	            out.println();
	        }
		}
		out.close();
	}
}
