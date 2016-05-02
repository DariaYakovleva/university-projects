#include <bits/stdc++.h>

using namespace std;


const int N = 200010;

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

point arr1[N];
point arr2[N];
int n1, m2;

long long vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

long long scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

double dist(point a, point b) {
    return sqrt((double)(b.x - a.x) * (b.x - a.x) + (double)(b.y - a.y) * (b.y - a.y));
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


double distance(point* first, point* second, int n, int m) {
    double res = dist(first[0], second[0]);
    int pos = 0;
    for (int i = 0; i < m; i++) {
        double a = pointToSegment(first[0], second[(i) % m], second[(i + 1) % m]);
        if (a < res) {
            res = a;
            pos = i;
        }
    }
    for (int i = 1; i <= n; i++) {
        res = min(res, pointToSegment(first[i % n], second[(pos) % m], second[(pos + 1) % m]));
        double aa = pointToSegment(first[i % n], second[(pos + 1) % m], second[(pos + 2) % m]);
        while (aa < res) {
            res = aa;
            pos = (pos + 1) % m;
            aa = pointToSegment(first[i % n], second[(pos + 1) % m], second[(pos + 2) % m]);
        }
        aa = pointToSegment(first[i % n], second[pos % m], second[(pos + 1) % m]);

        if (aa == dist(first[i % n], second[(pos + 1) % m])) pos = (pos + 1) % m;

        aa = pointToSegment(first[i % n], second[(pos - 1 + m) % m], second[pos % m]);
        while (aa < res) {
            res = aa;
            pos = (pos - 1 + m) % m;
            aa = pointToSegment(first[i % n], second[(pos - 1 + m) % m], second[(pos) % m]);
        }
        aa = pointToSegment(first[i % n], second[pos % m], second[(pos + 1) % m]);
        if (aa == dist(first[i % n], second[(pos + 1) % m])) pos = (pos + 1) % m;
    }
    return res;
}


int main() {
//	freopen("input.in", "r", stdin);
//    freopen("distance.in", "r", stdin);
//    freopen("distance.out", "w", stdout);
    cin >> n1;
    for (int i = 0; i < n1; i++) {
        cin >> arr1[i].x >> arr1[i].y;
    }
    cin >> m2;
    for (int i = 0; i < m2; i++) {
        cin >> arr2[i].x >> arr2[i].y;
    }
    cout.precision(11);
    cout << min(distance(arr1, arr2, n1, m2), distance(arr2, arr1, m2, n1)) << endl;

    return 0;
}