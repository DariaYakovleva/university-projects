import java.io.*;
import java.util.*;
 
public class tournament_bm {
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = in.nextInt() - 1;
        }
        int min = k - n;
        int[] cnt = new int[2];
        int j = -1;
        int[] tot = new int[2];
        for (int i = 0; i < k; i++) {
            cnt[a[i]]++;
            if (cnt[a[i]] > min) {
                j = i;
                tot[a[i]] = n - 1;
                tot[1 - a[i]] = k - n;
                out.println((i + 1));
                break;
            }
        }
        cnt[0] = cnt[1] = 0;
        for (int i = 0; i < k; i++) {
            if (i <= j) {
                if (i != k - 1 && (cnt[0] == n - 1 || cnt[1] == n - 1)) {
                    out.print("1 ");
                } else {
                    out.print("0 ");
                }
            } else {
                if (i == k - 1 || (cnt[0] == tot[0] || cnt[1] == tot[1])) {
                    out.print("1 ");
                } else {
                    out.print("0 ");
                }
            }
            cnt[a[i]]++;
        }
        out.println();
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
        new tournament_bm().runIO();
    }
}
