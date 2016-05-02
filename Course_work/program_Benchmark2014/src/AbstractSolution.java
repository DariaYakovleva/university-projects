import java.util.ArrayList;

/**
 * Created by Daria on 18.02.2016.
 */
public abstract class AbstractSolution {
    public static class Edge {
        int to;
        int d;
        Edge(){}
        Edge(int a, int b) {
            to = a;
            d = b;
        }
    }

    public static class Item {
        int p;
        int w;
        int num;
        Item(){}
        Item(int a, int b, int c) {
            p = a;
            w = b;
            num = c;
        }
    }

    final int N = 100; //max cities
    final int M = 30; //max items
    final double INF = 10000010.0;
    int[][] dist;
    ArrayList<Item>[] items;
    int n;
    int m;
    int w;
    int r;


    static boolean next_permutation(int[] perm) {
        //что поисходит с perm????
        int n = perm.length;
        int pos = n - 1;
        while (pos > 1 && perm[pos] < perm[pos - 1])  {
            pos--;
        }
        if (pos == 1) return false;
        int minPos = pos;
        for (int i = pos + 1; i < n; i++) {
            if (perm[i] > perm[pos - 1] && perm[i] < perm[minPos]) minPos = i;
        }
        int tmp = perm[pos - 1];
        perm[pos - 1] = perm[minPos];
        perm[minPos] = tmp;
        for (int i = 0; i < (n - pos) / 2; i++) {
            tmp = perm[pos + i];
            perm[pos + i] = perm[n - i - 1];
            perm[n - i - 1] = tmp;
        }
        for (int i = 0; i < n; i++) {
            System.err.print(perm[i] + " ");
        }
//        System.err.println();
        return true;
    }

    double z(long mask, int[] perm) {
        int[] ww = new int[n];
        int n = perm.length;
        double zz = 0;
        int wall = 0;
        for (int i = 0; i < n; i++) ww[i] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < items[i].size(); j++) {
                if ((mask & (1 << items[i].get(j).num)) > 0) {
                    zz += items[i].get(j).p;
                    ww[i] += items[i].get(j).w;
                }
            }
            wall += ww[i];
            if (wall > w) return -INF;
        }
        for (int i = 1; i < n; i++) {
            ww[perm[i]] += ww[perm[i - 1]];
        }
        double v_min = 0.1;
        double v_max = 1.0;
        double v = (v_max - v_min) / w;
        double sum = dist[perm[n - 1]][perm[0]] / (v_max - v * ww[perm[n - 1]]);
        for (int i = 0; i < n - 1; i++) {
            sum += dist[perm[i]][perm[i + 1]] / (v_max - v * ww[perm[i]]);
        }
        return zz - r * sum;
    }
}
