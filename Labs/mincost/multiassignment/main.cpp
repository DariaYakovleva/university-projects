#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long

using namespace std;

const int N = 210;
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

int n, k;
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

int math[N];

bool dfs3(int v) {
	if (used[v]) return false;
	if (v == -1) return false;
	used[v] = true;
	for (size_t i = 0; i < g[v].size(); i++) {
		if (g[v][i].f <= 0) continue;
		int to = g[v][i].to;
		if ((math[to] == -1) || dfs3(math[to])) {
			math[to] = v;
			return true;
		}
	}
	return false; 
	
}

void init3() {
	for (int i = 1; i < n; i++) {
		math[i] = -1;
	}
}

void kuhn() {
	init3();                                             	
	for (int k = 1; k < n / 2; k++) {	
		for (int h = 1; h < n; h++) used[h] = false;
		dfs3(k);
	}
}


int main() {
	freopen("multiassignment.in", "r", stdin);
	freopen("multiassignment.out", "w", stdout);
	cin >> n >> k;
	for (int i = 1; i <= n; i++) 
		for (int j = 1; j <= n; j++) {
			int a;
			cin >> a;
			g[i].push_back(edge(j + n, 1, 0, a));
			g[j + n].push_back(edge(i, 0, 0, -a));
		}
	for (int i = 1; i <= n; i++) {
		g[0].push_back(edge(i, k, 0, 0));
		g[i].push_back(edge(0, 0, 0, 0));
	}
	for (int i = n + 1; i <= n + n; i++) {
		g[2 * n + 1].push_back(edge(i, 0, 0, 0));
		g[i].push_back(edge(2 * n + 1, k, 0, 0));
	}	
	n = (n + 1) * 2 ;
	s = 0;
	t = n - 1;
	cout << mincostmaxflow() << endl;
	int math2[N];
	for (int i = 0; i < k; i++) {
		kuhn();
		for (int j = n / 2; j < n - 1; j++) {
			if (math[j] != -1) {
				math2[math[j]] = j;
			}
			for (size_t e = 0; e < g[math[j]].size(); e++) {
				if (g[math[j]][e].to == j) g[math[j]][e].f--;
			}
		}
		for (int j = 1; j < n / 2; j++) {
			cout << math2[j] - (n / 2 - 1) << " ";
		}
		cout << endl;
	}
}