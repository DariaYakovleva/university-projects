#include <bits/stdc++.h>

using namespace std;

const int N = 110;
const int M = 5100;
const int INF = 100010;

struct edge {
	int from, to, c;
};

vector<edge> edges;
vector<int> g[M];
set<int> result;
set<int> other;
bool color[N];
int rankd[N];
int parent[N];
bool x1[M];
bool x2[M];
int n, m;

void init() {
	for (int i = 0; i < N; i++) {
		color[i] = false;
	}
}

void init_dsu() {
	for (int i = 0; i < N; i++) {
		parent[i] = i;
		rankd[i] = 0;
	}
}

int get_parent(int x) {
	if (parent[x] == x) return x;
	parent[x] = get_parent(parent[x]);
	return parent[x];
}

void union_dsu(int x, int y) {
	x = get_parent(x);
	y = get_parent(y);
	if (x == y) return;
	if (rankd[x] < rankd[y]) swap(x, y);
	if (rankd[x] == rankd[y]) rankd[x]++;
	parent[y] = x;
	return;
}

bool used[M];
int previ[M];
bool bfs() {
	queue<int> q;
	for (int i = 0; i < m; i++) {
		used[i] = false;
		if (x1[i]) {
			q.push(i);
			used[i] = true;
			previ[i] = -1;
		}
	}
	while (!q.empty()) {
		int v = q.front();
//		cout << "bfs " << v << endl;
		q.pop();
		if (x2[v]) {
			int vv = v;
			while (vv != -1) {
				if (other.count(vv) > 0) {
					result.insert(vv);
					color[edges[vv].c] = true;
					other.erase(vv);
				} else {
					result.erase(vv);
					color[edges[vv].c] = false;
					other.insert(vv);
				}
				vv = previ[vv];
			}
			return true;
		}
		for (auto to : g[v]) {
			if (!used[to]) {
				used[to] = true;
				previ[to] = v;
				q.push(to);
			}
		}
	}
	return false;
}

void build_gr() {
	for (int i = 0; i < m; i++) g[i].clear();
	for (auto v : result) {
	 	init_dsu();
		for (auto e : result) {
			if (e == v) continue;
			int v1 = edges[e].from;
			int u = edges[e].to;
			union_dsu(u, v1);
		}
		for (auto to : other) {
			if (edges[v].c == edges[to].c || !color[edges[to].c]) {
				g[to].push_back(v);
//				cout << "ec " << to << " " << v << endl;
			} 
			int v1 = edges[to].from;
			int u = edges[to].to;
			if (get_parent(u) != get_parent(v1)) {
				g[v].push_back(to);
//				cout << "ed " << v << " " << to << endl;
			}
		}	
	} 
}

void set_xx() {
	for (int i = 0; i < m; i++) {
		x1[i] = false;
		x2[i] = false;
	}
	init_dsu();
	for (auto e : result) {
		int v1 = edges[e].from;
		int u = edges[e].to;
		union_dsu(u, v1);
	}
	for (auto x: other) {
		if (!color[edges[x].c]) {
			x2[x] = true;
//			cout << "x2 " << x << endl;
		}
		int v1 = edges[x].from;
		int u = edges[x].to;
		if (get_parent(u) != get_parent(v1)) {
			x1[x] = true;
//			cout << "x1 " << x << endl;
		}
	}
	return;
}

int main() { 
	freopen("rainbow.in", "r", stdin);
	freopen("rainbow.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--; b--;
		edges.push_back({a, b, c});
		other.insert(i);
	}
//	int cl = clock();
	bool maxim = false;
	init();
	while (!maxim) {
		build_gr();
		set_xx();
		if (!bfs()) {
			maxim = true;
		}
	}
//	cout << "TIME: " << clock() - cl << endl;
	cout << result.size() << endl;
	for (auto v : result) {
		cout << v + 1 << " ";
	}
	return 0;
}