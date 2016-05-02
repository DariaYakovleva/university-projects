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

point first[N];
point second[N];
point start;
int n, m;

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

bool compare(point a, point b) {
    long long turn = vect(point(a.x - start.x, a.y - start.y), point(b.x - start.x, b.y - start.y));
    return (turn < 0) || (turn == 0 && dist(a, start) < dist(b, start));
}

double distance() {
    double res = dist(first[0], second[0]);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            res = min(res, pointToSegment(first[i], second[j], second[(j + 1) % m]));
//            cout << pointToSegment(first[i], second[j], second[(j + 1) % m]) << "1 " << i << " " << j << endl;
            res = min(res, pointToSegment(second[j], first[i], first[(i + 1) % n]));
//            cout << pointToSegment(first[i], second[j], second[(j + 1) % m]) << "2 " << i << " " << j << endl;
        }
    }
    return res;
}


int main() {
	freopen("input.in", "r", stdin);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> first[i].x >> first[i].y;
    }
    cin >> m;
    for (int i = 0; i < m; i++) {
        cin >> second[i].x >> second[i].y;
    }
    cout.precision(11);
    cout << distance() << endl;


    return 0;
}