#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

const int N = 100010;
int n, m;
vector<int> g[N];
int colors[N];

void dfs(int v, int c) {
	colors[v] = c;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i];
		if (colors[to] == -1)
			dfs(to, c);
	}
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i]  = -1;
	}
}

int main() {
	freopen("components.in", "r", stdin);
	freopen("components.out", "w", stdout);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
    	int a, b;
    	cin >> a >> b;
    	a--;
    	b--;
    	g[a].push_back(b);
    	g[b].push_back(a);
    }
    init();
    int k = 0;
    for (int i = 0; i < n; i++) {
    	if (colors[i] == -1) {
    		dfs(i, k);
    		k++;
    	}
    }
    cout << k << endl;
    for (int i = 0; i < n; i++) {
    	cout << colors[i] + 1 << " ";
    }

	return 0;
}