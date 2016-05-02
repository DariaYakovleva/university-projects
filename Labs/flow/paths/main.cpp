#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 1100;
int n, m;
vector<int> g[N];
int math[N];
bool used[N];
int p[N], rank[N];

int v_get(int v) {
	if (p[v] == v)
		return v;
	return p[v] = v_get(p[v]);
}

void v_union(int v1, int v2) {
	v1 = v_get(v1);
	v2 = v_get(v2);
	if (v1 == v2) return;
	if (rank[v1] < rank[v2])
		swap(v1, v2);
	if (rank[v1] == rank[v2]) {
		rank[v1]++;	
	}	
	p[v2] = v1;
}

bool dfs(int v) {
	if (used[v]) return false;
	if (v == -1) return false;
	used[v] = true;
	for (int i = 0; i < (int)g[v].size(); i++) if (g[v][i] != -1) {
		int to = g[v][i];
		if ((math[to] == -1) || dfs(math[to])) {
			math[to] = v;
			return true;
		}
	}
	return false; 
	
}

void init() {
	for (int i = 0; i < n; i++) {
		math[i] = -1;
		p[i] = i;
		rank[i] = 0;
	}
}

bool path(int v) {
	used[v] = true;
	if (math[v] == -1) return true;
	if (!used[math[v]]) return path(math[v]);
	else return false;
}

int main() {
	freopen("paths.in", "r", stdin);
	freopen("paths.out", "w", stdout);
	cin >> n >> m;
	init();
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--; 
		b--;
		g[a].push_back(b);
	}
	int res = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) used[j] = false;
		dfs(i);
	}
	for (int j = 0; j < n; j++) used[j] = false;
	for (int i = 0; i < n; i++) {
		if (math[i] != -1) v_union(i, math[i]);
	}
	for (int i = 0; i < n; i++) used[v_get(p[i])] = true;
	for (int i = 0; i < n; i++) if (used[i]) res++;
	
	cout << res << endl;
	return 0;
}