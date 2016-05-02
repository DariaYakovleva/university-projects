import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Daria on 18.02.2016.
 */
public class Main {


    static final int N = 100; //max cities
    static final int M = 30; //max items
    static final double INF = 10000010.0;

    static ArrayList<AbstractSolution.Edge>[] g;
    static int[][] dist;
    static ArrayList<AbstractSolution.Item>[] items;

    static int n, m, w, r;
    static int k;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.in"));
        System.setOut(new PrintStream("output.out"));
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<AbstractSolution.Edge>();
        }
        dist = new int[n][n];
//        perm = new int[n];
//        ww = new int[n];
        m = in.nextInt();
        items = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            items[i] = new ArrayList<AbstractSolution.Item>();
        }
        w = in.nextInt();
        r = in.nextInt();
        k = in.nextInt();
        for (int i = 0; i < k; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int d = in.nextInt();
            a--;
            b--;
            g[a].add(new AbstractSolution.Edge(b, d));
            g[b].add(new AbstractSolution.Edge(a, d));
            dist[a][b] = d;
            dist[b][a] = d;

        }
        for (int i = 0; i < m; i++) {
            int pi = in.nextInt();
            int wi = in.nextInt();
            int c = in.nextInt();
            c--;
            items[c].add(new AbstractSolution.Item(pi, wi, i));
        }
//        init();
//        double res = go();
//        System.out.println(res);
//        System.err.println(res);
        in.close();
    }
}
