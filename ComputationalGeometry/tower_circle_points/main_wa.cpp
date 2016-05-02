#include <bits/stdc++.h>

using namespace std;


const int N = 2000010;

struct point {
    long double x;
    long double y;
    point(){};
    point(long double a, long double b) {
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

const long double MAX_R = 100000000.0;
long double res_r = MAX_R;

const long double EPS = 1e-15;


long double vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

long double scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

long double dist(point a, point b) {
    return sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
}

bool inCircle(point p, point z, long double r) {
    return (dist(p, z) <= r + EPS);
}


bool onCircle(point p, point z, long double r) {
    return (fabs(dist(p, z) - r) < EPS);
}

long double square(point a, point b, point c) {
    point va = {b.x - a.x, b.y - a.y};
    point vb = {c.x - a.x, c.y - a.y};
    return fabs(vect(va, vb) / 2);
}

long double getRadius(point a, point b, point c) {
    long double sq = square(a, b, c);
    if (sq < EPS) return -1.0;
    return dist(a, b) * dist(b, c) * dist(a, c) / (4.0 * sq);
}

point sub(point a, point b) {
    return point{a.x - b.x, a.y - b.y};
}

point getCentre(point a, point b, point c) {
    long double sq = square(a, b, c);
    long double aa = dist(b, c);
    long double bb = dist(a, c);
    long double cc = dist(a, b);
    long double alpha_a = aa * aa / (8.0 * sq * sq) * scalar(sub(a, b), sub(a, c));
    long double alpha_b = bb * bb / (8.0 * sq * sq) * scalar(sub(b, a), sub(b, c));
    long double alpha_c = cc * cc / (8.0 * sq * sq) * scalar(sub(c, a), sub(c, b));
    point rr = {alpha_a * a.x + alpha_b * b.x + alpha_c * c.x, alpha_a * a.y + alpha_b * b.y + alpha_c * c.y};
    return rr;
}

void findCircle() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) if (i != j) {
            long double cur_r = dist(points[i], points[j]) / 2;
            if (cur_r < EPS) continue;
            point z = {(points[i].x + points[j].x) / 2, (points[i].y + points[j].y) / 2};
            bool all = true;
            for (int k = 0; k < n; k++) {
                if (!inCircle(points[k], z, cur_r)) {
                    all = false;
                    break;
                }
            }
            if (!all) continue;
            if (cur_r < res_r) {
                res_r = cur_r;
                res_z = z;
//                cerr << "2 " << res_r << endl;	
            }
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++)  if (i != j) {
            for (int k = 0; k < n; k++) if (k != j && k != i) {
                long double cur_r = getRadius(points[i], points[j], points[k]);
//                cerr << i << ", " << j << ", " << k << " "<< cur_r << endl;
                if (cur_r < EPS) continue;
                point z = getCentre(points[i], points[j], points[k]);
                bool all = true;
                for (int d = 0; d < n; d++) {
                    if (!inCircle(points[d], z, cur_r)) {
                        all = false;
                        break;
                    }
                }
                if (!all) continue;
                if (cur_r < res_r) {
                    res_r = cur_r;
                    res_z = z;
//                    cerr << "3 " << res_r << endl;	
                }
            }
        }
    }
}


int main() {
    freopen("tower.in", "r", stdin);
    freopen("tower2.out", "w", stdout);
    res_z = point(0.0, 0.0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> points[i].x >> points[i].y;
    }
    if (n == 1) {
        cout << 0 << endl << points[0].x << " " << points[0].y << endl;
        return 0;
    }
    findCircle();
    assert(res_r < MAX_R);
    cout.precision(8);
    cout << res_r << endl;
    cout << res_z.x << " " << res_z.y << endl;
    return 0;
}