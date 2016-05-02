#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

const int N = 600;
const int INF = 1000000010;

struct edge {
	int to, c, f;
	edge() {}
	edge(int b, int d, int e) {
		to = b;
		c = d;
		f = e;
	}
};

queue<int> q;
int n, m;
vector<edge> g[N];
int level[N];
int p[N];
bool used[N];
vector<int> ans;
int s, t;

bool bfs(int s) {
	for (int i = 0; i < N; i++) level[i] = INF;
	level[s] = 0;
	q.push(s);
	while (!q.empty()) {
		int v = q.front();
		q.pop();
		for (int i = 0; i < (int)g[v].size(); i++) {
			if (g[v][i].f == g[v][i].c) continue;
			int to = g[v][i].to;
			if (level[to] > level[v] + 1) {
				level[to] = level[v] + 1;
				q.push(to);
			}
		}
	}
	return level[t] != INF;
}

int dfs(int v, int flow) {
	if (flow == 0) return 0;
	if (v == t) return flow;
	for (int i = p[v]; i < (int)g[v].size(); i++) {
		int to = g[v][i].to;
		if (level[to] != level[v] + 1) continue;
		int n_flow = dfs(to, min(flow, g[v][i].c - g[v][i].f));
		if (n_flow != 0) {
			g[v][i].f += n_flow;
			for (int j = 0; j < (int)g[to].size(); j++) if (g[to][j].to == v) {
				g[to][j].f -= n_flow;
				break;
			}
			return n_flow;
		}
		p[v]++;
	}
	return 0;

}

void findS(int v) {
	ans.push_back(v);
	used[v] = true;
	for (int i = 0; i < g[v].size(); i++) {
	    if (g[v][i].c - g[v][i].f == 0) continue;
		int to = g[v][i].to;
		if (!used[to]) findS(to);
	}
}

void init() {
	for (int i = 0; i < n; i++) {
		p[i] = 0;
	}
}

int dinic() {
	int res = 0;
	while (bfs(s)) {
		init();
		int tmp = dfs(0, INF);
		while (tmp > 0) {
			res += tmp;
			tmp = dfs(0, INF);
		}	
	}
	return res;
}

int main() {
	freopen("cut.in", "r", stdin);
	freopen("cut.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--;
		b--;
		g[a].push_back(edge(b, c, 0));
		g[b].push_back(edge(a, c, 0));
	}
	s = 0;
	t = n - 1;
	dinic();
	for (int i = 0; i < n; i++) used[i] = false;
	findS(0);
	cout << ans.size() << endl;
	for (int i = 0; i < (int)ans.size(); i++) {
		cout << ans[i] + 1 << endl;
	}


	return 0;
}
