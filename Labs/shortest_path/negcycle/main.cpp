#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long

using namespace std;

const int N = 510;
const ll INF = 1000000000;

struct edge {
	int in, to, w;
	edge() {}
	edge(int a, int b, ll c) {
		in = a;
		to = b;
	   	w = c;
	}
};

int n;
vector<edge> g;
ll d[N];
bool used[N];
int p[N];
vector<int> cycle;

void dfs(int v) {
	used[v] = true;
	for (int i = 0; i < g.size(); i++) {
		if ((g[i].in == v) && (!(used[g[i].to])))
			dfs(g[i].to);
	}
} 


bool ford() {
	int cycleV = -1;
	for (int i = 0; i < n; i++) {
		cycleV = -1;
		for (int j = 0; j < g.size(); j++) {
			int in = g[j].in, to = g[j].to;
			ll w = g[j].w;
			if (d[in] < INF) {
		    if (d[to] > d[in] + w) {
		      	d[to] = max(-INF, d[in] + w);
		      	cycleV = to;
		      	p[to] = in;
		    }
		    }
		}
	}
	
	if (cycleV != -1) {
		for (int i = 0; i < n; i++) {
			cycleV = p[cycleV];
		}
		cycle.push_back(cycleV);
		int v = p[cycleV];
		while (v != cycleV) {
			cycle.push_back(v);
			v = p[v];
		}
		cycle.push_back(cycleV);
		reverse(cycle.begin(), cycle.end());
		return true;
	}
	return false;

		
}            

void init() {
	for (int i = 0; i < n; i++) { 
		d[i] = INF;
		p[i] = -1;
		used[i] = false;
	}
}

int main() {
	freopen("negcycle.in", "r", stdin);
	freopen("negcycle.out", "w", stdout);
	cin >> n;
	init();
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) {
			ll c;
			cin >> c;
			if (c != INF)
				g.push_back(edge(i, j, c));		
	}
	for (int i = 0; i < n; i++)
		if (!used[i]) {
			d[i] = 0;
			dfs(i);
		}
			
	if (ford()) {
		cout << "YES" << endl;
		cout << cycle.size() << endl;
		for (int i = 0; i < cycle.size(); i++) {
			cout << cycle[i] + 1 << " ";
		}
	} else {
		cout << "NO" << endl;
	}
    return 0;
}