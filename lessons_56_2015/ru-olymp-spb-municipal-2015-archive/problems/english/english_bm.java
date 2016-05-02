import java.io.*;
import java.util.*;
 
public class english_bm {
    FastScanner in;
    PrintWriter out;
 
    String first(String s) {
        String res = s.charAt(0) + "";
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 's' && (i == s.length() - 1 || s.charAt(i + 1) != 'h')) {
                res += "th";
            } else {
                res += c;
            }
        }
        return res;
    }
 
    String second(String s) {
        if (s.charAt(0) == 'e') {
            return "ae" + s.substring(1);
        } else {
            return s;
        }
    }
 
    String third(String s) {
        ArrayList<Character> res = new ArrayList<>();
        int it = 0;
        while (it < s.length()) {
            char c = s.charAt(it++);
            if (c != 'o' || it == s.length() || s.charAt(it) != 'o') {
                res.add(c);
                continue;
            }
            res.add('o');
            res.add('u');
            it++;
            while (it != s.length() && s.charAt(it) == 'o') {
                res.add('o');
                it++;
            }
        }
        String ans = "";
        for (Character c : res) {
            ans += c;
        }
        return ans;
    }
 
    void solve() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            boolean up = Character.isUpperCase(s.charAt(0));
            s = s.toLowerCase();
            s = first(s);
            s = second(s);
            s = third(s);
            if (up) {
                char[] tmp = s.toCharArray();
                tmp[0] = Character.toUpperCase(tmp[0]);
                s = new String(tmp);
            }
            out.println(s);
        }
    }
 
    void run() {
        try {
            in = new FastScanner(new File("object.in"));
            out = new PrintWriter(new File("object.out"));
 
            solve();
 
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    void runIO() {
 
        in = new FastScanner(System.in);
        out = new PrintWriter(System.out);
 
        solve();
 
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return null;
                st = new StringTokenizer(s);
            }
            return st.nextToken();
        }
 
        boolean hasMoreTokens() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return false;
                st = new StringTokenizer(s);
            }
            return true;
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 
    public static void main(String[] args) {
        new english_bm().runIO();
    }
}
