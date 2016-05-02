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

struct line {
    long long a, b, c;
};


int n;
int m;
point arr[N];
line lines[N];
point start;
vector<point> convex;

long long vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

long long dist(point a, point b) {
    return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
}

long long rotation(point a, point b, point c) {
    return vect(point(b.x - c.x, b.y - c.y), point(a.x - c.x, a.y - c.y));
}

bool compare(point a, point b) {
    long long turn = vect(point(a.x - start.x, a.y - start.y), point(b.x - start.x, b.y - start.y));
    return (turn < 0) || (turn == 0 && dist(a, start) < dist(b, start));
}

void convex_hull() {
    if (n == 1) {
        convex.push_back(arr[0]);
        return;
    }
    int st_pos = 0;
    for (int i = 1; i < n; i++) {
        if (arr[i] < arr[st_pos]) {
            st_pos = i;
        }
    }
    swap(arr[0], arr[st_pos]);
    start = arr[0];
    sort(arr + 1, arr + n, compare);
    convex.push_back(arr[0]);
    convex.push_back(arr[1]);
    for (int i = 2; i < n; i++) {
        while (convex.size() >= 2 && rotation(arr[i], convex[convex.size() - 1], convex[convex.size() - 2]) >= 0) {
            convex.pop_back();
        }
        convex.push_back(arr[i]);
    }
}


long long dist(point p, line l) {
    return l.a * p.x + l.b * p.y + l.c; // / sqrt(l.a * l.a + l.b * l.b)
}


int sign(long long x) {
    if (x > 0) return 1;
    if (x < 0) return -1;
    return 0;
}

bool paid(int pos) {
    int ss = convex.size();
    line cur = lines[pos];
    int l = 0;
    int r = ss - 1;
    if (sign(dist(convex[l], cur)) != sign(dist(convex[r], cur))) return true;
    if (sign(dist(convex[l], cur)) == 0) return true;
    if (sign(dist(convex[r], cur)) == 0) return true;
    if (r == l) return false;
    if (r - l > 2) {
        while ((r + ss - l) % ss > 2) {
            int l1, r1;
            if (l < r) {
                l1 = l + (r - l) / 3;
                r1 = r - (r - l) / 3;
            } else {
                l1 = (l + (r + ss - l) / 3) % ss;
                r1 = (r + ss - (r + ss - l) / 3) % ss;
            }
            long long dl = dist(convex[l], cur);
            long long dl1 = dist(convex[l1], cur);
            long long dr = dist(convex[r], cur);
            long long dr1 = dist(convex[r1], cur);
            if (sign(dl) != sign(dl1)) return true;
            if (sign(dr) != sign(dr1)) return true;
            if (dl == 0 || dr == 0 || dr1 == 0 || dl1 == 0) return true;
            long long mind = abs(min(min(dist(convex[l], cur), dist(convex[l1], cur)),
                                     min(dist(convex[r], cur), dist(convex[r1], cur))));
            if (abs(dist(convex[l], cur)) == mind) {
                l = r;
                r = l1;
            } else if (abs(dist(convex[r], cur)) == mind) {
                r = l;
                l = r1;
            } else if (abs(dist(convex[l1], cur)) == mind) {
                r = r1;
            } else if (abs(dist(convex[r1], cur)) == mind) {
                l = l1;
            } else {
                cerr << "MDA " << cur.a << " " << cur.b << " " << cur.c << endl;
            }
//        cerr << l << " " << r << endl;
        }
    }
    if (sign(dist(convex[l], cur)) == 0) return true;
    if (sign(dist(convex[l + 1], cur)) == 0) return true;
    if (sign(dist(convex[r], cur)) == 0) return true;
    if (sign(dist(convex[l], cur)) != sign(dist(convex[r], cur))) return true;
    if (sign(dist(convex[l], cur)) != sign(dist(convex[l + 1], cur))) return true;
    if (sign(dist(convex[r], cur)) != sign(dist(convex[l + 1], cur))) return true;
    return false;
}

int main() {
    cin >> m >> n;
    for (int i = 0; i < m; i++) {
        cin >> lines[i].a >> lines[i].b >> lines[i].c;
    }
    for (int i = 0; i < n; i++) {
        cin >> arr[i].x >> arr[i].y;
    }

    convex_hull();
//    for (point p: convex) {
//        cerr << p.x << " " << p.y << " " << dist(p, lines[0]) << endl;
//    }
    vector<int> ans;
    for (int i = 0; i < m; i++) {
        if (paid(i)) {
            ans.push_back(i);
        }
    }
    cout << ans.size() << endl;
    for (int x: ans) {
        cout << x + 1 << " ";
    }
    cout << endl;
    return 0;
}