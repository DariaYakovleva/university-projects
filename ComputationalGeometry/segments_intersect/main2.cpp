#include <bits/stdc++.h>

using namespace std;


const int N = 300010;

struct point {
    long long x;
    long long y;
    int num;
    int op;
    point(){};
    point(long long a, long long b) {
        x = a;
        y = b;
    }
    bool const operator<(const point& b) const {
        return (y < b.y) || (y == b.y && x < b.x);
    }
};

bool cmp(point a, point b) {
    return (a.x < b.x) || (a.x == b.x && a.y < b.y);
}

pair<point, point> segs[N];
point arr[2 * N];
int n;

long long vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

long long scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

long long dist(point a, point b) {
    return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
}

int left_turn(point a, point b, point c) {
    long long l = (long long)(b.x - a.x) * (c.y - a.y);
    long long r = (long long)(c.x - a.x) * (b.y - a.y);
    long long s = l - r;
    if (s < 0)
        return -1;
    if (s > 0)
        return 1;
    return 0;
}

bool intersect(point a, point b, point c, point d) {
    return (max(min(a.x, b.x), min(c.x, d.x)) <= min(max(a.x, b.x), max(c.x, d.x))) &&
           (max(min(a.y, b.y), min(c.y, d.y)) <= min(max(a.y, b.y), max(c.y, d.y)));
}

bool intersection(pair<point, point> x, pair<point, point> y) {
    point a = x.first;
    point b = x.second;
    point c = y.first;
    point d = y.second;
    return intersect(a, b, c, d) && (left_turn(a, b, c) * left_turn(a, b, d) <= 0) && (left_turn(c, d, a) * left_turn(c, d, b) <= 0);
}


pair<int, int> intersect() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) if (i != j) {
			if (intersection(segs[i], segs[j])) {
				return make_pair(i, j);
			}
		}
	}
	return make_pair(-1, -1);
}

int main() {
	freopen("input.in", "r", stdin);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> segs[i].first.x >> segs[i].first.y >> segs[i].second.x >> segs[i].second.y;
        arr[2 * i] = segs[i].first;
        arr[2 * i].num = i;
        arr[2 * i].op = 1;
        arr[2 * i + 1] = segs[i].second;
        arr[2 * i + 1].num = i;
        arr[2 * i + 1].op = -1;
    }
    pair<int, int> res = intersect();
    if (res.first == -1) {
        cout << "NO" << endl;
    } else {
        cout << "YES" << endl;
 //       cout << res.first + 1 << " " << res.second + 1 << endl;
    }
    return 0;
}