#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 300;
int n, m, k;
vector<int> g[N];
int math[N];
bool used[N];

bool dfs(int v) {
	if (used[v]) return false;
	if (v == -1) return false;
	used[v] = true;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i];
		if ((math[to] == -1) || dfs(math[to])) {
			math[to] = v;
			return true;
		}
	}
	return false; 
	
}

void init() {
	for (int i = 0; i < m; i++) {
		math[i] = -1;
	}
}

int main() {
	freopen("matching.in", "r", stdin);
	freopen("matching.out", "w", stdout);
	cin >> n >> m >> k;
	init();
	for (int i = 0; i < k; i++) {
		int a, b;
		cin >> a >> b;
		a--; 
		b--;
		g[a].push_back(b);
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) used[j] = false;
		dfs(i);
	}
	int res = 0;
	for (int i = 0; i < m; i++)
		if (math[i] != -1) res++;
	cout << res << endl;

	return 0;
}