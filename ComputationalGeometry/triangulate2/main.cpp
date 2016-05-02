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
int curn;
point vertex[N];
int cnt[N];
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

void cntN(int v) {
    cnt[v] = 0;
    int a = v;
    int b = vertex[v].prev;
    int c = vertex[v].next;
    for (int j = 0; j < n; j++) {
        if (j != a && j != b && j != c) {
            if (inTriangle(vertex[a], vertex[b], vertex[c], vertex[j])) {
                cnt[v]++;
            }
        }
    }
}

void triangulate() {
    int pos = 0;
    while (curn > 3) {
        int a = pos;
        int b = vertex[pos].prev;
        int c = vertex[pos].next;
        int trn = left_turn(vertex[a], vertex[b], vertex[c]);
        if (trn < 0 && cnt[pos] == 0) {
            res.push_back({b, a, c});
            vertex[b].next = c;
            vertex[c].prev = b;
            cntN(b);
            cntN(c);
            curn--;
        }
        pos = vertex[pos].prev;
    }
    res.push_back({vertex[vertex[pos].prev].num, vertex[pos].num, vertex[vertex[pos].next].num});
}


int main() {
    cin >> n;
    curn = n;
    for (int i = 0; i < n; i++) {
        scanf("%d%d", &vertex[i].x, &vertex[i].y);
        vertex[i].num = i;
        vertex[i].prev = (i + n - 1) % n;
        vertex[i].next = (i + 1) % n;
    }
    for (int i = 0; i < n; i++) {
        cntN(i);
    }
    triangulate();
    for (int i = 0; i < res.size(); i++) {
        printf("%d %d %d\n", res[i].a + 1, res[i].b + 1, res[i].c + 1);
    }
    return 0;
}