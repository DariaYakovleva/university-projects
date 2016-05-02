#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

const int N = 300;
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
int s, t;
vector<edge> edges;

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

void init() {
	for (int i = 0; i < n; i++) {
		p[i] = 0;
	}
}

int dinic() {
	int res = 0;
	while (bfs(s)) {
		init();
		int tmp = dfs(s, INF);
		while (tmp > 0) {
			res += tmp;
			tmp = dfs(s, INF);
		}	
	}
	return res;
}

int main() {
	freopen("circulation.in", "r", stdin);
	freopen("circulation.out", "w", stdout);
	cin >> n >> m;
	s = n;
	t = n + 1;
	n += 2;
	for (int i = 0; i < m; i++) {
		int a, b, l, c;
		cin >> a >> b >> l >> c;
		a--;
		b--;
		g[s].push_back(edge(b, l, 0));
		g[b].push_back(edge(s, 0, 0));

		g[a].push_back(edge(b, c - l, 0));
		edges.push_back(edge(a, b, l));
		g[b].push_back(edge(a, 0, 0));

		g[a].push_back(edge(t, l, 0));
		g[t].push_back(edge(a, 0, 0));
	}
	int sum = 0;
	for (int i = 0; i < (int)g[s].size(); i++) {
		sum += g[s][i].c;
	}
	int flow = dinic();
	if (flow < sum) {
		cout << "NO" << endl;
	} else {
		cout << "YES" << endl;
		for (int i = 0; i < m; i++) {
			int in = edges[i].to;
			int to = edges[i].c;
			for (int j = 0; j < (int)g[in].size(); j++) {
				if (g[in][j].to == to) cout << g[in][j].f + edges[i].f << endl;
			}
		}
	}

	return 0;
}
