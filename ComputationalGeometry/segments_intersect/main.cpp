#include <bits/stdc++.h>

using namespace std;


const int N = 200010;

double get_y (int p, double x);

struct point {
    int x;
    int y;
    int num;
    int op;
    point(){};
    point(int a, int b) {
        x = a;
        y = b;
    }

    bool const operator<(const point& b) const {
//        return (y < b.y) || (y == b.y && x < b.x);
        int xx = max(x, b.x);
        return get_y(num, xx) < get_y(b.num, xx);
    }
};

bool cmp(point a, point b) {
    return (a.x < b.x) || (a.x == b.x && a.op > b.op) || (a.x == b.x && a.op == b.op && a.y < b.y);
}

pair<point, point> segs[N];
point arr[2 * N];
set<point>::iterator where[N];
int n;


double get_y(int p, double x) {
    if (segs[p].second.x == segs[p].first.x) {
        return segs[p].second.y;
    }
    return segs[p].first.y + (segs[p].second.y - segs[p].first.y) *
                                     (x - (double)segs[p].first.x) / (segs[p].second.x - segs[p].first.x);
}

int left_turn(point a, point b, point c) {
    long long s = (long long)(b.x - a.x) * (long long)(c.y - a.y) - (long long)(c.x - a.x) * (long long)(b.y - a.y);
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

set<point> line;
pair<int, int> intersect() {
    sort(arr, arr + 2 * n, cmp);
    for (int i = 0; i < 2 * n; i++) {
//        cout << arr[i].op << " " << arr[i].x << " " << arr[i].y << " " << arr[i].num << endl;
        if (arr[i].op == 1) {
            set<point>::iterator it = line.lower_bound(arr[i]);
            if (it != line.end()) {
                point next = *it;
                if (intersection(segs[arr[i].num], segs[next.num])) {
                    return make_pair(arr[i].num, next.num);
                }
            }
            if (it != line.begin()) {
                --it;
                point prev = *it;
                if (intersection(segs[arr[i].num], segs[prev.num])) {
                    return make_pair(arr[i].num, prev.num);
                }
                ++it;
            }
            where[arr[i].num] = line.insert(it, arr[i]);
        } else {
//            cout << "find" << " " << segs[arr[i].num].first.x << " " << segs[arr[i].num].first.y << endl;
            set<point>::iterator it = where[arr[i].num];
            assert(it != line.end());
            set<point>::iterator prev = line.end();
            set<point>::iterator next = line.end();
            if (it != line.begin()) {
                --it;
                prev = it;
                if (intersection(segs[arr[i].num], segs[prev->num])) {
                    return make_pair(arr[i].num, prev->num);
                }
                ++it;
            }
            ++it;
            if (it != line.end()) {
                next = it;
                if (intersection(segs[arr[i].num], segs[next->num])) {
                    return make_pair(arr[i].num, next->num);
                }
            }
            if (prev != line.end() && next != line.end() && intersection(segs[prev->num], segs[next->num])) {
                return make_pair(prev->num, next->num);
            }
            line.erase(where[arr[i].num]);
        }
    }
    return make_pair(-1, -1);
};

int main() {
    freopen("input.in", "r", stdin);
    cin >> n;
    for (int i = 0; i < n; i++) {
        scanf("%d %d %d %d", &segs[i].first.x, &segs[i].first.y, &segs[i].second.x, &segs[i].second.y);
        if (segs[i].first.x > segs[i].second.x) swap(segs[i].first, segs[i].second);
        if (segs[i].first.x == segs[i].second.x && segs[i].first.y > segs[i].second.y) swap(segs[i].first, segs[i].second);
        segs[i].first.num = i;
        segs[i].first.op = 1;
        arr[2 * i] = segs[i].first;
        segs[i].second.num = i;
        segs[i].second.op = -1;
        arr[2 * i + 1] = segs[i].second;
    }
    pair<int, int> res = intersect();
    if (res.first == -1) {
        cout << "NO" << endl;
    } else {
        cout << "YES" << endl;
        cout << res.first + 1 << " " << res.second + 1 << endl;
    }
    return 0;
}