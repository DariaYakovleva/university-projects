#include <bits/stdc++.h>

using namespace std;


const int N = 100010;

struct point {
    long long x;
    long long y;
    point(){};
    point(long long a, long long b) {
        x = a;
        y = b;
    }
    bool operator<(point& b) const {
        return (x < b.x) || (x == b.x && y < b.y);
    }
};

int n;
point points[N];
point tmp[N];
point p;


long long vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

long long scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

double dist(point a, point b) {
    return sqrt((double)(b.x - a.x) * (b.x - a.x) + (double)(b.y - a.y) * (b.y - a.y));
}

int left_turn(const point& a, const point& b, const point& c) {
    long long l = (long long)(b.x - a.x) * (long long)(c.y - a.y);
    long long r = (long long)(c.x - a.x) * (long long)(b.y - a.y);
    long long s = l - r;
    if (s < 0)
        return -1;
    if (s > 0)
        return 1;
    return 0;
}

bool inTriangle(const point& a, const point& b, const point& c, const point& d) {
    int turn1 = left_turn(a, b, d);
    int turn2 = left_turn(c, a, d);
    int turn3 = left_turn(b, c, d);
    return (turn1 <= 0 && turn2 <= 0 && turn3 <= 0) || (turn1 >= 0 && turn2 >= 0 && turn3 >= 0);
}


double pointToLine(point a, point b, point c) {
    long long v = abs(vect(point(a.x - b.x, a.y - b.y), point(c.x - b.x, c.y - b.y)));
    return (double)v / dist(b, c);
}

double pointToSegment(point a, point b, point c) {
    if (scalar(point(a.x - b.x, a.y - b.y), point(c.x - b.x, c.y - b.y)) < 0) {
        return dist(a, b);
    }
    if (scalar(point(a.x - c.x, a.y - c.y), point(b.x - c.x, b.y - c.y)) < 0) {
        return dist(a, c);
    }
    return pointToLine(a, b, c);
}

bool intersect(point a, point b, point c, point d) {
    return (max(min(a.x, b.x), min(c.x, d.x)) <= min(max(a.x, b.x), max(c.x, d.x))) &&
           (max(min(a.y, b.y), min(c.y, d.y)) <= min(max(a.y, b.y), max(c.y, d.y)));
}

bool intersection(point a, point b, point c, point d) {
    return intersect(a, b, c, d) && (left_turn(a, b, c) * left_turn(a, b, d) <= 0) && (left_turn(c, d, a) * left_turn(c, d, b) <= 0);
}


void prepare() {
    if (left_turn(points[0], points[1], points[2]) < 0) {
        for (int i = 0; i < n; i++) {
            tmp[i] = points[n - i - 1];
        }
    } else {
        for (int i = 0; i < n; i++) {
            tmp[i] = points[i];
        }
    }

    int mp = 0;
    for (int i = 0; i < n; i++) {
        if (tmp[i] < tmp[mp]) mp = i;
    }

    for (int i = 0; i < n; i++) {
        points[i] = tmp[(mp + i) % n];
    }
    points[n] = points[0];
}

const double EPS = 1e-19;

int inside(point x) {
    if (x.x == points[0].x && x.y == points[0].y) return 1;
    int cnt = 0;
    point p1 = point{x.x - 2000000003, x.y-1};
    for (int i = 0; i < n; i++) {
        if (intersection(x, x, points[i], points[i + 1])) return 1;
        if (intersection(p1, x, points[i], points[i + 1])) {
            cnt++;
        }
    }
    return (cnt % 2) == 1;
}
int main() {
    freopen("point.in", "r", stdin);
    freopen("point.out", "w", stdout);
    cin >> n >> p.x >> p.y;
    for (int i = 0; i < n; i++) {
        cin >> points[i].x >> points[i].y;
    }
    prepare();
    int res = inside(p);
    if (res == 1) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }
    return 0;
}