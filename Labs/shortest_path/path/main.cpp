#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long

using namespace std;

const int N = 3010;
const ll INF = 5000000000000000010;

struct edge {
	int to;
	ll w;
	edge() {}
	edge(int b, ll c) {
		to = b;
	   	w = c;
	}
};

int n, m, s;
vector<edge> g[N];
ll d[N];
bool no_path[N];
bool used[N];

void dfs(int v) {
	used[v] = true;
	no_path[v] = true;
	for (int i = 0; i < g[v].size(); i++) {
		if (!used[g[v][i].to]) 
			dfs(g[v][i].to);
	}
	
}

void ford(int st, bool t) {
	for (int i = 0; i < 2 * n; i++) {
		for (int k = 0; k < n; k++)
		for (int j = 0; j < g[k].size(); j++) {
			int in = k, to = g[k][j].to;
			ll  w = g[k][j].w;
			if (d[in] < INF) { 
		    	if (d[to] > d[in] + w) {
		      		d[to] = max(-INF, d[in] + w);
		      		if (t) no_path[to] = true;
		    	}	
		    }
		}
	}
		
}            

void init() {
	for (int i = 0; i < n; i++) { 
		d[i] = INF;
		no_path[i] = false;
	}
}

int main() {
	freopen("path.in", "r", stdin);
	freopen("path.out", "w", stdout);
	cin >> n >> m >> s;
	s--;
	init();
	for (int i = 0; i < m; i++) {
		int a, b;
		ll c;
		cin >> a >> b >> c;
		a--;
		b--;
		g[a].push_back(edge(b, c));		
	}		
	d[s] = 0;
	ford(s, false);
	ford(s, true);
	for (int i = 0; i < n; i++) {
		if (no_path[i]) {
		    for (int j = 0; j < n; j++) used[j] = false;
			dfs(i);
		}
	}
	for (int i = 0; i < n; i++) {
		if (d[i] == INF) {
			cout << "*" << endl;
		} else if (no_path[i]) {
			cout << "-" << endl;
		} else {
			cout << d[i] << endl;
		}
	}

    return 0;
}