#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

const int N = 100010;
vector<int> g[N];
int n, m;
int used[N];
bool circle = false;

void dfs(int v, int prev) {
	used[v] = 1;
	for (int to: g[v]) {
		if (used[to] == 1 && to != prev) {
			circle = true;
		}
		if (used[to] == 0) {
			dfs(to, v);
		}
	}
	used[v] = 2;
}


int main() {
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		g[a].push_back(b);
		g[b].push_back(a);
	}
	for (int i = 0; i < n; i++) used[i] = 0;
	int res = 0;
	for (int i = 0; i < n; i++) {
		circle = false;
		if (used[i] == 0) {
			dfs(i, -1);
			if (!circle) res++;
		}		
	}

	cout << res << endl;
	return 0;
}