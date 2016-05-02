#include <iostream>
#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

const int N = 600;
const int INF = 1000000010;

struct edge {
	int to, c, f, num;
	edge() {}
	edge(int b, int d, int e, int g) {
		to = b;
		c = d;
		f = e;
		num = g;
	}
};

queue<int> q;
int n, m;
vector<edge> g[N];
int level[N];
int p[N];
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

bool used[N];
vector< pair<int, int> > path;

bool vertexDec(int v) {
	used[v] = true;
	for (int i = 0; i < g[v].size(); i++) if (g[v][i].num != -1) {
		if (g[v][i].f > 0) {
			int to = g[v][i].to;
			path.push_back(make_pair(v, i));
			if (!used[to]) {
				if (vertexDec(to)) return true;	
			} else {
				return true;	
			}
		}
	}
	if (v == t) {
		if (path.empty()) return false;
		return true;
	}
	return false;
}

vector< vector<int> > ans;

void decom() {
	for (int j = 0; j < n; j++) used[j] = false;
	for (int i = 0; i < n; i++) {
		while (vertexDec(i)) {
			int pos = 0;
			int ver = g[path[path.size() - 1].first][path[path.size() - 1].second].to;
		    if (ver != t) {
		    	while  (path[pos].first != ver) pos++;
		    }
			int mflow = INF;
			for (int j = pos; j < path.size(); j++) {
				mflow = min(mflow, g[path[j].first][path[j].second].f);	
			}
			vector<int> tmp;
			tmp.push_back(mflow);
			tmp.push_back(path.size() - pos);
			for (int j = pos; j < path.size(); j++) {
				tmp.push_back(g[path[j].first][path[j].second].num + 1);
				g[path[j].first][path[j].second].f -= mflow;	
			}
			ans.push_back(tmp);
			if (!path.empty()) path.clear();
			for (int j = 0; j < n; j++) used[j] = false;
		}
		if (!path.empty()) path.clear();
		for (int j = 0; j < n; j++) used[j] = false;
	}
}



int main() {
	freopen("decomposition.in", "r", stdin);
	freopen("decomposition.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--;
		b--;
		g[a].push_back(edge(b, c, 0, i));
		g[b].push_back(edge(a, 0, 0, -1));
	}
	s = 0;
	t = n - 1;
	dinic();
	decom();
	cout << ans.size() << endl;
	for (int i = 0; i < ans.size(); i++) {
		for (int j = 0; j < ans[i].size(); j++) {
			cout << ans[i][j] <<  " ";
		}
		cout << endl;
	}

	return 0;
}
