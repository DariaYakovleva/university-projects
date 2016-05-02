import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Daria on 18.02.2016.
 */
public class SimpleHeuristic extends AbstractSolution {

    double vv;
    double v_max;
    double v_min;
    int result = 0;
    int[] dd;
    double[][] u;

    class ScoreItem implements Comparable{
        Item item;
        int city;
        int pos;
        double score;
        public int compareTo(Object obj) {
            ScoreItem second = (ScoreItem)obj;
            if (this.score > second.score) {
                return -1;
            }
            if (this.score < second.score) {
                return 1;
            }
            return 0;
        }
    }
    ScoreItem[] score;

    SimpleHeuristic() {}
    SimpleHeuristic(int n, int m, int w, int r, int[][] dist, ArrayList<Item>[] items) {
        this.dist = dist;
        this.items = items;
        this.n = n;
        this.m = m;
        this.w = w;
        this.r = r;
//        perm = new int[n];
//        for (int i = 0; i < n; i++) perm[i] = i;
        v_max = 1.0;
        v_min = 0.1;
        vv = (v_min + v_max) / w;
        u = new double[n][m];
        score = new ScoreItem[n];
    }


    void get_result(int perm[]) {

        dd = new int[n];
        dd[perm[n - 1]] = dist[perm[n - 1]][perm[0]];
        for (int i = n - 2; i >= 0; i--) dd[i] = dist[perm[i]][perm[i + 1]] + dd[i + 1];

        int t1 = 0;
        for (int i = 0; i < n; i++) {
            t1 += dist[perm[i]][perm[(i + 1) % n]];
        }
        for (int i = 0; i < n; i++) {
            int x_i = perm[i];
            for (int k = 0; k < items[x_i].size(); k++) {
                int num = items[x_i].get(k).num;
                double t_xi_k = dd[x_i] / (v_max - vv * items[i].get(k).w); //equation 1
                double t1_xi_k = t1 - dd[x_i] + t_xi_k;
                score[num].score = items[x_i].get(k).p - r * t_xi_k;
                score[num].item = items[x_i].get(k);
                score[num].city = x_i;
                score[num].pos = k;
                u[x_i][k] = r * t1 + (items[x_i].get(k).p - r * t1_xi_k);
            }
        }
        Arrays.sort(score);
        int w_cur = 0;
        long plan = 0;
        for (int i = 0; i < m; i++) {
            int x_i = score[i].city;
            int k = score[i].pos;
            if ((w_cur + score[i].item.w < w) && (u[x_i][k] > 0)) {
                plan |= (1 << score[i].item.num);
                w_cur += score[i].item.w;
            }
            if (w_cur == w) break;
        }
        double z_star = Double.max(z(plan, perm), -r * t1);
    }

    double RandomLocalSearch(int[] perm, int a) {
        long p_star = 0;
        long p = p_star;
        Random rnd = new Random();
        boolean better = false;
        while (!better) {
            for (int i = 0; i < a; i++) {
                int pos = rnd.nextInt() % m;
                p ^= (1 << pos);
            }
            int w_cur = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < items[i].size(); j++) {
                    if ((p & (1 << items[i].get(j).num)) > 0) {
                        w_cur += items[i].get(j).w;
                    }
                }
            }
            if (z(p, perm) > z(p_star, perm) && w_cur < w) {
                p_star = p;
                better = true;
            }
        }
        return z(p_star, perm);
    }

    double EvolutionaryAlgorithm(int[] perm, int prob) {
        long p_star = 0;
        long p = p_star;
        Random rnd = new Random();
        boolean better = false;
        while (!better) {
            for (int i = 0; i < m; i++) {
                double x = rnd.nextInt(Integer.MAX_VALUE) / (double)Integer.MAX_VALUE;
                if (x <= 1.0 / prob) {
                    p ^= (1 << i);
                }
            }
            int w_cur = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < items[i].size(); j++) {
                    if ((p & (1 << items[i].get(j).num)) > 0) {
                        w_cur += items[i].get(j).w;
                    }
                }
            }
            if (z(p, perm) > z(p_star, perm) && w_cur < w) {
                p_star = p;
                better = true;
            }
        }
        return z(p_star, perm);
    }
}
