#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
#include <set>

using namespace std;

const int N = 500010;
const long long INF = 5000000000000000;

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
bool have_two_ways[N];
int realOrder[N];
set<int> valuable;

void init() {
	for (int i = 0; i < n + 10; i++) {
		dist[i] = INF;
		used[i] = false;
		have_two_ways[i] = false;
	}
}

int get_num(int x) {
	if (valuable.lower_bound(x) != valuable.end()) {
		return *valuable.lower_bound(x);	
	}
	return n;
}

void bfs(int s) {	
	dist[s] = 0;
	used[s] = true;
	for (int i = 0; i < n; i++) {
		valuable.insert(i);
	}
	priority_queue< pair<long long, int> > que;
	que.push(make_pair(-teleports[s].cost, s));
	while (!que.empty()) {
		int v = que.top().second;
//		int x = que.top().first;
		que.pop();
		int cur_t;
		cur_t = max(0, get_num(teleports[v].ll));
		if (cur_t > teleports[v].lr) cur_t = get_num(teleports[v].rl);
		while (cur_t <= teleports[v].rr && cur_t < n) {
//			cout << v << " " << cur_t << endl;
			int to = cur_t;
			int weight = teleports[v].cost;
			if (!used[to]) {
				used[to] = true;
				ways[to] = ways[v];
				dist[to] = dist[v] + weight;
//				cout << "1 " << v << " " << to << " " << dist[v] << " " << weight << " " << dist[to] << endl;
				que.push(make_pair(-(dist[to] + teleports[to].cost), to));
			} else {
				if (dist[to] == dist[v] + weight) {
//					cerr << "2 " << v << " " << cur_t << " " << x << endl;
					have_two_ways[to] = true;	
				}
				valuable.erase(to);
			}

			cur_t = get_num(cur_t + 1);
			if (cur_t > teleports[v].lr && cur_t < teleports[v].rl) {
				cur_t = get_num(teleports[v].rl);
//				cerr << "2" <<v << " " << cur_t << " " <<x << endl;
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
//		cout << teleports2[i].x << "  " << teleports2[i].left << " " << teleports2[i].right << endl;
		realOrder[teleports2[i].position] = i;
		int llx = teleports2[i].x - teleports2[i].right;
		int lrx = teleports2[i].x - teleports2[i].left;
		int rrx = teleports2[i].x + teleports2[i].right;
		int rlx = teleports2[i].x + teleports2[i].left;
		int llx2 = (int)(lower_bound(teleports2, teleports2 + n, telep2(0, llx, 0, 0, 0), cmp) - teleports2);
		int rlx2 = (int)(lower_bound(teleports2, teleports2 + n, telep2(0, rlx, 0, 0, 0), cmp) - teleports2);
		int lrx2 = (int)(upper_bound(teleports2, teleports2 + n, telep2(0, lrx, 0, 0, 0), cmp) - teleports2) - 1;
		int rrx2 = (int)(upper_bound(teleports2, teleports2 + n, telep2(0, rrx, 0, 0, 0), cmp) - teleports2) - 1;
//		cout << i << "  " << teleports2[i].position << " " << llx2 << "  " << lrx2 << "  " << rlx2 << "  " << rrx2 << endl;
		teleports[i] = telep(llx2, lrx2, rlx2, rrx2, teleports2[i].cost);
	}
}

int main() {
	freopen("teleports.in", "r", stdin);
	freopen("teleports.out", "w", stdout);
	cin >> n >> s;
	s--;
	init();
	for (int i = 0; i < n; i++) {
		int x, l, r, c;
		cin >> x >> l >> r >> c;
		teleports2[i] = telep2(i, x, l, r, c);
	}
	setOrder();
	bfs(realOrder[s]);
	for (int i = 0; i < n; i++) {
		int a = realOrder[i];
		if (dist[a] < INF) {
			cout << dist[a] << " ";
			if (have_two_ways[a]) cout << "YES" << endl;
			else cout << "NO" << endl;
		} else {
			cout << -1 << endl;
		}

	}
	return 0;
}