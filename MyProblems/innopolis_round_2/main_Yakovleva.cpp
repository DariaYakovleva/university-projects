#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>

using namespace std;

const int N = 500010;
const long long INF = 5000000000010000;

struct telep {
    int ll;
    int lr;
    int rl;
    int rr;
    int cost;
    telep() {}
    telep(int a, int b, int c, int d, int cc) {
        ll = a;
        lr = b;
        rl = c;
        rr = d;
        cost = cc;
    }
};

struct telep2 {
    int x;
    int left;
    int right;
    int position;
    int cost;
    telep2() {}
    telep2(int p, int xx, int l, int r, int c) {
        position = p;
        x = xx;
        left = l;
        right = r;
        cost = c;
    }
};

telep teleports[N];
telep2 teleports2[N];
int n, s;
long long dist[N];
bool used[N];
int parent[N];
int rank[N];
int num[N];
bool have_two_ways[N];
int realOrder[N];


void init_dsu() {
    for (int i = 0; i < N; i++) {
        parent[i] = i;
        num[i] = i;
        rank[i] = 0;
    }
}

int get_parent(int x) {
    if (parent[x] == x) return x;
    return parent[x] = get_parent(parent[x]);
}

int get_num(int x) {
    if (x < 0) return x;
    return num[get_parent(x)];
}

void union_sets(int a, int b) {
    a = get_parent(a);
    b = get_parent(b);
    if (a == b) return;
    num[a] = num[b];
    if (a < b) swap(a, b);
    parent[b] = a;
    if (rank[a] == rank[b]) rank[a]++;
}


void init() {
    for (int i = 0; i < n + 10; i++) {
        dist[i] = INF;
        used[i] = false;
        have_two_ways[i] = false;
    }
}

void dijkstra(int s) {  
    dist[s] = 0;
    used[s] = true;
    priority_queue< pair<long long, int> > que;
    que.push(make_pair(-teleports[s].cost, s));
    while (!que.empty()) {
        int v = que.top().second;
//      int x = que.top().first;
        que.pop();
        int cur_t;
        cur_t = max(0, get_num(teleports[v].ll));
        if (cur_t > teleports[v].lr) cur_t = get_num(teleports[v].rl);
        while (cur_t <= teleports[v].rr && cur_t < n) {
//          cerr << v << " " << cur_t << " " << x << endl;
            int to = cur_t;
            int weight = teleports[v].cost;
            if (!used[to]) {
                used[to] = true;
                dist[to] = dist[v] + weight;
                have_two_ways[to] |= have_two_ways[v];
//              cout << "1 " << v << " " << to << " " << dist[v] << " " << weight << " " << dist[to] << endl;
                que.push(make_pair(-(dist[to] + teleports[to].cost), to));
            } else {
                if (dist[to] == dist[v] + weight) {
//                  cerr << "2 " << v << " " << cur_t << " " << x << endl;
                    have_two_ways[to] = true;   
                }
                union_sets(to, to + 1);
            }

            cur_t = get_num(cur_t + 1);
            if (cur_t > teleports[v].lr && cur_t < teleports[v].rl) {
                cur_t = get_num(teleports[v].rl);
//              cerr << "2" <<v << " " << cur_t << " " <<x << endl;
            }
        }
    }
}

bool cmp(const telep2 &a, const telep2 &b) {
    return a.x < b.x;
}
void setOrder() {
    sort(teleports2, teleports2 + n, cmp);
    for (int i = 0; i < n; i++) {
        realOrder[teleports2[i].position] = i;
        int llx = teleports2[i].x - teleports2[i].right;
        int lrx = teleports2[i].x - teleports2[i].left;
        int rrx = teleports2[i].x + teleports2[i].right;
        int rlx = teleports2[i].x + teleports2[i].left;
        int llx2 = (int)(lower_bound(teleports2, teleports2 + n, telep2(0, llx, 0, 0, 0), cmp) - teleports2);
        int rlx2 = (int)(lower_bound(teleports2, teleports2 + n, telep2(0, rlx, 0, 0, 0), cmp) - teleports2);
        int lrx2 = (int)(upper_bound(teleports2, teleports2 + n, telep2(0, lrx, 0, 0, 0), cmp) - teleports2) - 1;
        int rrx2 = (int)(upper_bound(teleports2, teleports2 + n, telep2(0, rrx, 0, 0, 0), cmp) - teleports2) - 1;
//      cout << i << "  " << teleports2[i].position << " " << llx2 << "  " << lrx2 << "  " << rlx2 << "  " << rrx2 << endl;
        teleports[i] = telep(llx2, lrx2, rlx2, rrx2, teleports2[i].cost);
    }
}

int main() {
    freopen("teleports.in", "r", stdin);
    freopen("teleports.out", "w", stdout);
    scanf("%d %d", &n, &s);
    s--;
    init();
    init_dsu();
    for (int i = 0; i < n; i++) {
        int x, l, r, c;
        scanf("%d %d %d %d", &x, &l, &r, &c);
        teleports2[i] = telep2(i, x, l, r, c);
    }
    setOrder();
    dijkstra(realOrder[s]);
    for (int i = 0; i < n; i++) {
        int a = realOrder[i];
        if (dist[a] < INF) {
            printf("%I64d ", dist[a]);
            if (have_two_ways[a]) printf("YES\n");
            else printf("NO\n");
        } else {
            printf("-1\n");
        }

    }
    return 0;
}
