#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const int N = 100010;
const int INF = 100000100;

int n, m, s, t;

struct vertex {
	int v, c;
	vertex() {}
	vertex(int a, int b) {
		v = a;
		c = b;
	}
};
vector<vertex> g[N];
vector<int> res;
int colors[N];
int dist[N];

void top_sort(int v) {
    colors[v] = 1;
    for (int i = 0; i < g[v].size(); i++) {
    	int to = g[v][i].v;
    	if (colors[to] == 0) {
    		top_sort(to);
    	}
    }
    res.push_back(v);
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = 0;
		dist[i] = INF;
	}
}

int main() {
	freopen("shortpath.in", "r", stdin);
	freopen("shortpath.out", "w", stdout);
	cin >> n >> m >> s >> t;
	s--;
	t--;
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--;
		b--;
		g[a].push_back(vertex(b, c));
	}
	init();
	top_sort(s);
	reverse(res.begin(), res.end());
	dist[s] = 0;
	for (int i = 0; i < res.size(); i++) {
//	cout << res[i] + 1 << " " ;
		for (int j = 0; j < g[res[i]].size(); j++) {
			int to = g[res[i]][j].v, w = g[res[i]][j].c;
			if (dist[res[i]] + w < dist[to]) {
				dist[to] = dist[res[i]] + w;
			}
		}
	}
	if (dist[t] == INF) {
		cout << "Unreachable" << endl;
	} else {
		cout << dist[t] << endl;
	}

	return 0;
}