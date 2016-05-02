#include <bits/stdc++.h>

using namespace std;

struct point {
    int x;
    int y;
    int num;
    int prev;
    int next;
};

struct triangle {
    int a;
    int b;
    int c;
};

const int N = 5010;

int n;
point vertex[N];
vector<triangle> res;

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

long long dist(const point& a, const point& b) {
    return (long long)(b.x - a.x) * (long long)(b.x - a.x) + (long long)(b.y - a.y) * (long long)(b.y - a.y);
}

long long sqr(long long a) {
    return a * a;
}
long long pointToLine(point a, point b, point c) {
    return sqr((b.y - c.y) * (long long)a.x + (c.x - b.x) * (long long)a.y + ((long long)b.x * c.y - (long long)c.x * b.y)) / dist(b, c);
}

void triangulate(int pos) {
    if (vertex[vertex[vertex[pos].next].next].next == pos) {
        res.push_back({vertex[vertex[pos].prev].num, pos, vertex[vertex[pos].next].num});
        return;
    }
    int a = pos;
    int b = vertex[pos].prev;
    int c = vertex[pos].next;
    int turn = left_turn(vertex[a], vertex[b], vertex[c]);
    while (turn >= 0) {
        pos = vertex[pos].next;
        a = pos;
        b = vertex[pos].prev;
        c = vertex[pos].next;
        turn = left_turn(vertex[a], vertex[b], vertex[c]);
    }
    int v = -1;
    for (int i = 0; i < n; i++) {
        if (i != a && i != b && i != c) {
            if (inTriangle(vertex[a], vertex[b], vertex[c], vertex[i])) {
                if (v == -1 || dist(vertex[i], vertex[a]) < dist(vertex[v], vertex[a])) v = i;
            }
        }
    }
    int from, to;
    if (v == -1) {
        from = b;
        to = c;
    } else {
        from = v;
        to = a;
    }
    int p = vertex[to].prev;
    int ne = vertex[from].next;
    vertex[to].prev = from;
    vertex[from].next = to;
    triangulate(vertex[from].prev);
    vertex[to].prev = p;
    vertex[to].next = from;
    vertex[to].next = from;
    vertex[from].next = ne;
    vertex[from].prev = to;
    triangulate(vertex[from].prev);
}


int main() {
//    cout << left_turn(point{0, 0}, point{0,1}, point{1,0});
    cin >> n;
    for (int i = 0; i < n; i++) {
        scanf("%d%d", &vertex[i].x, &vertex[i].y);
        vertex[i].num = i;
        vertex[i].prev = (i + n - 1) % n;
        vertex[i].next = (i + 1) % n;
    }
    triangulate(0);
    for (int i = 0; i < res.size(); i++) {
        printf("%d %d %d\n", res[i].a + 1, res[i].b + 1, res[i].c + 1);
    }
    return 0;
}