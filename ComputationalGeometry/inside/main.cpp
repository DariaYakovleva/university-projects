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
int m;


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

pair<int, int> bs(point x) {
    int l = 1;
    int r = n - 1;
    while (r - l > 1) {
        int m = (r + l) / 2;
        if (left_turn(points[0], x, points[m]) >= 0) {
            r = m;
        } else {
            l = m;
        }
    }
//    if (left_turn(points[0], x, points[r + 1]) == 0) return make_pair(r, r + 1);
    return make_pair(l, r);
};

const double EPS = 1e-19;

int inside(point x) {
    if (x.x < points[0].x) return -1;
    if (x.x == points[0].x && x.y == points[0].y) return 0;
    pair<int, int> two = bs(x);
    int l = two.first;
    int r = two.second;
    if (!inTriangle(points[0], points[l], points[r], x)) return -1;
    if (pointToSegment(x, points[l], points[r]) < EPS) return 0;
    if (l == 1 && pointToSegment(x, points[0], points[1]) < EPS) return 0;
    if (r == n - 1 && pointToSegment(x, points[n - 1], points[n]) < EPS) return 0;
    return 1;
}
int main() {
//    freopen("inside.in", "r", stdin);
//    freopen("inside.out", "w", stdout);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> points[i].x >> points[i].y;
    }
    prepare();
    cin >> m;
    for (int i = 0; i < m; i++) {
        point cur;
        cin >> cur.x >> cur.y;
        int res = inside(cur);
        if (res == 1) {
            cout << "INSIDE" << endl;
        } else if (res == 0) {
            cout << "BORDER" << endl;
        } else {
            cout << "OUTSIDE" << endl;
        }
    }
    return 0;
}