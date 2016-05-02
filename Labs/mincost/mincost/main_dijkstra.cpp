#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long

using namespace std;

const int N = 110;
const ll INF = 10000000000010;

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
ll phi[N];
bool used[N];
pair<int, int> p[N];
priority_queue< pair<int, ll> > q;

void ford(int st) {
	for (int i = 0; i < n; i++) {
		for (int k = 0; k < n; k++)
		for (size_t j = 0; j < g[k].size(); j++) {
			if (g[k][j].c == g[k][j].f) continue;
			int in = k, to = g[k][j].to;
			ll w = g[k][j].cost;
			if (phi[in] < INF) { 
		    	if (phi[to] > phi[in] + w) {
		      		phi[to] = max(-INF, phi[in] + w);
		    	}	
		    }
		}
	}
}            

void init2() {
	for (int i = 0; i < n; i++) { 
		used[i] = false;
		phi[i] = INF;
	}
}


void dijkstra(int st) {
	d[st] = 0;
	q.push(make_pair(st, 0));
	while (!q.empty()) {
		int v = q.top().first;
		q.pop();
		for (size_t i = 0; i < g[v].size(); i++) {
			if (g[v][i].c == g[v][i].f) continue;
		    int to = g[v][i].to;
		    ll w = g[v][i].cost;
			if (d[to] > d[v] + w + phi[v] - phi[to]) {
				d[to] = d[v] + w + phi[v] - phi[to];
				p[to] = make_pair(v, i);
				q.push(make_pair(to, d[to]));
			}
		}
	}
}            

void init() {
	for (int i = 0; i < n; i++) { 
		d[i] = INF;
		p[i] = make_pair(-1, -1);
    }
}

ll mincostmaxflow() {
	ll sum = 0;
	init2();
	phi[s] = 0;
	ford(s);
	while (1) {
		init();   	
		dijkstra(s);
		for (int i = 0; i < n; i++) {
			if (d[i] < INF)
				phi[i] += d[i];
		}
		if (d[t] == INF) break;
//		cout << d[t] << endl;
		int v = t;
		ll n_flow = INF;
		while (p[v].first != -1) {
			int prev = p[v].first;
			n_flow = min(n_flow, g[prev][p[v].second].c - g[prev][p[v].second].f);
			v = prev;
		}
//		cout << "n_flow " << n_flow << endl;
		v = t;
		if (n_flow != 0) {
			while (p[v].first != -1) {
//				cout << v << endl;
				int prev = p[v].first;
				g[prev][p[v].second].f += n_flow;
				sum += n_flow * g[prev][p[v].second].cost;
//				g[v][links[v][prev]].f -= n_flow;
				for (size_t i = 0; i < g[v].size(); i++) {
					if (g[v][i].to == prev && (g[v][i].f == -(g[prev][p[v].second].f - n_flow)) && (g[v][i].cost == -g[prev][p[v].second].cost)) {
						g[v][i].f -= n_flow;
					}
				}
				v = prev;
			}	
		} else break;
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