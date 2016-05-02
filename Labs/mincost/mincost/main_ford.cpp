#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long

using namespace std;

const int N = 1010;
const ll INF = 1000000010;

struct edge {
	int to;
	ll c, f, cost;
	edge() {}
	edge(int b, ll d, ll e, ll j) {
		to = b;
		c = d;
		f = e;
		cost = j;
	}
};

int n, m;
vector<edge> g[N];
int s, t;
ll d[N];
bool used[N];
int p[N];

void ford(int st) {
	for (int i = 0; i < n; i++) {
		for (int k = 0; k < n; k++)
		for (size_t j = 0; j < g[k].size(); j++) {
			if (g[k][j].c == g[k][j].f) continue;
			int in = k, to = g[k][j].to;
			ll w = g[k][j].cost;
			if (d[in] < INF) { 
		    	if (d[to] > d[in] + w) {
		      		d[to] = max(-INF, d[in] + w);
		      		p[to] = in;
		    	}	
		    }
		}
	}
}            

void init() {
	for (int i = 0; i < n; i++) { 
		used[i] = false;
		d[i] = INF;
		p[i] = -1;
	}
}

ll mincostmaxflow() {
	ll sum = 0;
	while (1) {
		init();   	
		d[s] = 0;
		ford(s);
		if (d[t] == INF) break;
//		cout << d[t] << endl;
		int v = t;
		ll n_flow = INF;
		while (p[v] != -1) {
			int prev = p[v];
			for (size_t i = 0; i < g[prev].size(); i++) {
				if (g[prev][i].to == v) {
					n_flow = min(n_flow, g[prev][i].c - g[prev][i].f);
					break;
				}
			}
			v = prev;
		}
//		cout << "n_flow " << n_flow << endl;
		v = t;
		if (n_flow != 0) {
			while (p[v] != -1) {
//				cout << v << endl;
				int prev = p[v];
				for (size_t i = 0; i < g[prev].size(); i++) {
					if (g[prev][i].to == v) {
						g[prev][i].f += n_flow;
						sum += n_flow * g[prev][i].cost;
						break;
					}
				}
				for (size_t i = 0; i < g[v].size(); i++) {
					if (g[v][i].to == prev) {
						g[v][i].f -= n_flow;
						break;
					}
				}
				v = prev;
			}	
		}
	}
	return sum;
}

int main() {
	freopen("mincost.in", "r", stdin);
	freopen("mincost.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a;
		ll b, c, d;
		cin >> a >> b >> c >> d;
		a--;
		b--;
		g[a].push_back(edge(b, c, 0, d));
		g[b].push_back(edge(a, 0, 0, -d));
	}
	s = 0;
	t = n - 1;
	cout << mincostmaxflow() << endl;
}