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

template<typename T>
int sign(T x) {
    if (x > 0) return 1;
    if (x < 0) return -1;
    return 0;
}

point min(point a, point b, int pos) {
    if (dist(a, lines[pos]) < dist(b, lines[pos]))
        return a;
    return b;
}


point max(point a, point b, int pos) {
    if (dist(a, lines[pos]) > dist(b, lines[pos]))
        return a;
    return b;
}

int pos_r = 0;
int ss;
bool paid(int pos) {
    point mini = convex[0];
    point maxi = convex[0];
    int l = 0;
    int r = pos_r;
    while (r - l > 2) {
        int l1 = l + (r - l) / 3;
        int r1 = r - (r - l) / 3;
        if (dist(convex[l1], lines[pos]) > dist(convex[r1], lines[pos])) {
            l = l1;
        } else {
            r = r1;
        }
    }
    mini = min(min(convex[l], convex[l + 1], pos), min(convex[r], mini, pos), pos);

    l = pos_r;
    r = ss;
    while (r - l > 2) {
        int l1 = l + (r - l) / 3;
        int r1 = r - (r - l) / 3;
        if (dist(convex[l1], lines[pos]) > dist(convex[r1], lines[pos])) {
            l = l1;
        } else {
            r = r1;
        }
    }
    mini = min(min(convex[l], convex[l + 1], pos), min(convex[r], mini, pos), pos);

    l = 0;
    r = pos_r;
    while (r - l > 2) {
        int l1 = l + (r - l) / 3;
        int r1 = r - (r - l) / 3;
        if (dist(convex[l1], lines[pos]) < dist(convex[r1], lines[pos])) {
            l = l1;
        } else {
            r = r1;
        }
    }
    maxi = max(max(convex[l], convex[l + 1], pos), max(convex[r], maxi, pos), pos);

    l = pos_r;
    r = ss;
    while (r - l > 2) {
        int l1 = l + (r - l) / 3;
        int r1 = r - (r - l) / 3;
        if (dist(convex[l1], lines[pos]) < dist(convex[r1], lines[pos])) {
            l = l1;
        } else {
            r = r1;
        }
    }
    maxi = max(max(convex[l], convex[l + 1], pos), max(convex[r], maxi, pos), pos);
//    cerr << pos << " " << convex[0].x << " " << convex[0].y << ", " << convex[pos_r].x << " " << convex[pos_r].y << endl;
//    for (point p: convex) {
//        cerr << dist(p, lines[pos]) << "  ";
//    }
    long long d1 = dist(mini, lines[pos]);
    long long d2 = dist(maxi, lines[pos]);
//    cerr << endl << d1 << " " << d2 << endl;
    return (d1 == 0 || d2 == 0 || sign(d1) != sign(d2));
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
    ss = convex.size();
    convex.push_back(convex[0]);
    for (int i = 0; i < ss; i++) {
        if ((convex[i].x > convex[pos_r].x) || (convex[i].x == convex[pos_r].x && convex[i].y >= convex[pos_r].y))
            pos_r = i;
    }
//    cerr << convex.size() << endl;
//    for (point p: convex) {
//        cerr << p.x << " " << p.y << endl; << " " << dist(p, lines[0]) << endl;
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