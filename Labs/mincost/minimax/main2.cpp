#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

struct edge {
	int to, w;
	edge(){}
	edge(int a, int b) {
		to = a;
		w = b;
	}
};

const int N = 310;
const int INF = 2000000010;
int n;
vector<edge> g[N];
vector<int> edges;
int math[N];
bool used[N];

bool dfs(int v, int w) {
	if (used[v]) return false;
	if (v == -1) return false;
	used[v] = true;
	for (size_t i = 0; i < g[v].size(); i++) {
		if (g[v][i].w < w) continue;
		int to = g[v][i].to;
		if ((math[to] == -1) || dfs(math[to], w)) {
			math[to] = v;
			return true;
		}
	}
	return false; 
	
}

void init() {
	for (int i = 0; i < n; i++) {
		math[i] = -1;
	}
}

int kuhn(int w) {
	init();                                             	
	for (int k = 0; k < n; k++) {	
		for (int h = 0; h < n; h++) used[h] = false;
		dfs(k, w);
	}
	int cnt = 0;
	for (int k = 0; k < n; k++) if (math[k] != -1) cnt++;
	return cnt;
}

int bs() {
	int l = -1, r = edges.size();
	while (r - l > 1) {
		int m = (l + r) / 2;
		if (kuhn(edges[m]) == n) {
			l = m;
		} else {
			r = m;
		}
	}
	return edges[l];
}

int main() {
	freopen("minimax.in", "r", stdin);
	freopen("minimax.out", "w", stdout);
	cin >> n;
	for(int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int a;
			cin >> a;
			g[i].push_back(edge(j, a));
			edges.push_back(a);
		}
	}
	sort(edges.begin(), edges.end());
	cout << bs();

	return 0;
}