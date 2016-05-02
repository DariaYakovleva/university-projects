#include <bits/stdc++.h>

using namespace std;

const int N = 2000010;

struct point {
    double x;
    double y;
    point(){};
    point(double a, double b) {
        x = a;
        y = b;
    }
    bool operator<(point& b) const {
        return (x < b.x) || (x == b.x && y < b.y);
    }
};

int n;
point points[N];
point res_z;

const double MAX_R = 10000000.0;
double res_r = MAX_R;

const double EPS = 1e-9;

double vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

double scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

double dist(point a, point b) {
    return sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
}

bool inCircle(point p, point z, double r) {
    return (dist(p, z) <= r + EPS);
}


double square(point a, point b, point c) {
    point va = {b.x - a.x, b.y - a.y};
    point vb = {c.x - a.x, c.y - a.y};
    return fabs(vect(va, vb) / 2);
}

double getRadius(point a, point b, point c) {
    double sq = square(a, b, c);
    if (sq < EPS) return -1.0;
    return dist(a, b) * dist(b, c) * dist(a, c) / (4.0 * sq);
}

point sub(point a, point b) {
    return point{a.x - b.x, a.y - b.y};
}

point getCentre(point a, point b, point c) {
    double sq = square(a, b, c);
    double aa = dist(b, c);
    double bb = dist(a, c);
    double cc = dist(a, b);
    double alpha_a = aa * aa / (8.0 * sq * sq) * scalar(sub(a, b), sub(a, c));
    double alpha_b = bb * bb / (8.0 * sq * sq) * scalar(sub(b, a), sub(b, c));
    double alpha_c = cc * cc / (8.0 * sq * sq) * scalar(sub(c, a), sub(c, b));
    point rr = {alpha_a * a.x + alpha_b * b.x + alpha_c * c.x, alpha_a * a.y + alpha_b * b.y + alpha_c * c.y};
    return rr;
}

void findCircle() {

    //begin_first 1..n
    res_z = {(points[0].x + points[1].x) / 2, (points[0].y + points[1].y) / 2};
    res_r = dist(points[0], points[1]) / 2;
    for (int i = 2; i < n; i++) {
        if (inCircle(points[i], res_z, res_r)) continue;

        //begin_second 1..i-1
        point cur_z = {(points[i].x + points[0].x) / 2, (points[i].y + points[0].y) / 2};
        double cur_r = dist(points[i], points[0]) / 2;
        for (int j = 1; j < i; j++) {
            if (inCircle(points[j], cur_z, cur_r)) continue;

            //begin_third 1..j-1
            point cur2_z = {(points[i].x + points[j].x) / 2, (points[i].y + points[j].y) / 2};
            double cur2_r = dist(points[i], points[j]) / 2;
            for (int k = 0; k < j; k++) {
                if (inCircle(points[k], cur2_z, cur2_r)) continue;
                double r = getRadius(points[i], points[j], points[k]);
//                    if (r < EPS) continue;
                point z = getCentre(points[i], points[j], points[k]);
//                    cerr << r << " " << z.x << " " << z.y << endl;
                cur2_r = r;
                cur2_z = z;
            }
            //end_third
            cur_z = cur2_z;
            cur_r = cur2_r;
        }
        //end_second
        res_r = cur_r;
        res_z = cur_z;
    }
    //end_first
}


int main() {
    freopen("tower.in", "r", stdin);
    freopen("tower.out", "w", stdout);
    res_z = point(0.0, 0.0);
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%lf%lf", &points[i].x, &points[i].y);
    }
    if (n == 1) {
        cout << 0 << endl << points[0].x << " " << points[0].y << endl;
        return 0;
    }
    random_shuffle(points, points + n);
    findCircle();
    printf("%.8lf\n", res_r);
    printf("%.8lf %.8lf\n", res_z.x, res_z.y);
    return 0;
}