import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daria on 17.02.2016.
 */

public class StupidSolution extends AbstractSolution {


//    static ArrayList<Edge>[] g;
    static int[] perm;

    StupidSolution() {}
    StupidSolution(int n, int m, int w, int r, int[][] dist, ArrayList<Item>[] items) {
        this.dist = dist;
        this.items = items;
        this.n = n;
        this.m = m;
        this.w = w;
        this.r = r;
        perm = new int[n];
        for (int i = 0; i < n; i++) perm[i] = i;
    }

    double getResult() {
        double res = -INF;
        while (next_permutation(perm)) {
            for (long mask = 0; mask < (2 << m); mask++) {
                double cur_z = z(mask, perm);
                res = Double.max(res, cur_z);
            }
        }
        return res;
    }

}
