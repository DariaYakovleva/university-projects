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

point arr[N];
point start;
int n;
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


int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i].x >> arr[i].y;
    }
    convex_hull();
    cout << convex.size() << endl;
    for (point a: convex) {
        cout << a.x << " " << a.y << endl;
    }
    return 0;
}