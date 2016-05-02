import java.io.*;
import java.util.*;
 
public class kingdom_bm {
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = in.nextInt();
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                int curMax = dp[i][j];
                for (int ni = i + 1; ni <= n; ni++) {
                    if (ni != i + 1) {
                        curMax = Math.max(curMax, pos[ni - 1] - pos[i]);
                    }
                    int len = ni - i;
                    if (len >= a && len <= b) {
                        dp[ni][j + 1] = Math.min(dp[ni][j + 1], curMax);
                    }
                }
            }
        }
        out.println(dp[n][k]);
    }
 
    void run() {
        try {
            in = new FastScanner(new File("kingdom_bm.in"));
            out = new PrintWriter(new File("kingdom_bm.out"));
 
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
        new kingdom_bm().runIO();
    }
}
