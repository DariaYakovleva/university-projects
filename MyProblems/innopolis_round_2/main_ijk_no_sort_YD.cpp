#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>

using namespace std;

const int N = 500010;
const long long INF = 50000000000010;

struct tel {
	int x;
	int l;
	int r;
	int c;
	tel() {}
	tel(int b, int cc, int d, int e) {
		x = b;
		l = cc;
		r = d;
		c = e;
	}	
} telep[N];

vector< pair<int, int> > teleports[N];
int n, s;
long long dist[N];
int ways[N];
int prev[N];

void init() {
	for (int i = 0; i < n; i++) {
		dist[i] = INF;
		ways[i] = 0;
		prev[i] = -1;
	}
}

void dijkstra(int s) {	
	init();
	dist[s] = 0;
	ways[s] = 1;
	queue<int> que;
	que.push(s);
	while (!que.empty()) {
		int v = que.front();
		que.pop();
		for (int i = 0; i < (int)teleports[v].size(); i++) {
			int to = teleports[v][i].first;
			int weight = teleports[v][i].second;
			if (dist[v] + weight < dist[to]) {
				dist[to] = dist[v] + weight;
				ways[to] = ways[v];
				prev[to] = v;
//				cout << "FIRST " << v << " " << to << " " << dist[to] << endl;
				que.push(to);
			} else if (dist[v] + weight == dist[to] && prev[to] != v) {
				ways[to] = ways[v] + ways[to];
			} 	
		}
	}
}

void constGr() {
	for (int i = 0; i < n; i++) {
//		cout << i << " " << telep[i].p << " " << telep[i].x << " " << telep[i].l << " " << telep[i].r << endl;
		for (int j = 0; j < n; j++) if (i != j) {
			int xx = telep[j].x;
			if ((xx >= telep[i].x - telep[i].r && xx <= telep[i].x - telep[i].l) || 
			(xx >= telep[i].x + telep[i].l && xx <= telep[i].x + telep[i].r)) {
//				cout << i << " " << j << endl;
				teleports[i].push_back(make_pair(j, telep[i].c));	
			}
		}
	}		
}

int main() {
	freopen("teleports.in", "r", stdin);
	freopen("teleports.out", "w", stdout);
//	cerr << "TEST" << endl;
	cin >> n >> s;
	s--;
	for (int i = 0; i < n; i++) {
		int x, l, r, c;
		cin >> x >> l >> r >> c;
		telep[i] = tel(x, l, r, c);
	}
	constGr();
	dijkstra(s);
	for (int i = 0; i < n; i++) {
		int a = i;
		if (dist[a] < INF) {
			cout << dist[a];
			if (ways[a] > 1) cout << " YES";
			else cout << " NO";
			cout << endl;
		} else {
			cout << -1 << endl;
		}
	}
	return 0;
}