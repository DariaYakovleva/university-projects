#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

const int N = 100010;
const int INF = 10000010;
vector<int> g[N];
int colors[N];
int n, m;

bool dfs(int v, int color) {
	colors[v] = color;
	bool ok = true;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i];
		if (colors[to] == -1) {
			if (!dfs(to, color ^ 1)) ok = false;
		} else {
			if (colors[to] == colors[v]) {
				ok = false;
			} 
		} 
	}
	return ok;
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = -1;
	}
}

int main() {
	freopen("bipartite.in", "r", stdin);
	freopen("bipartite.out", "w", stdout);
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
    for (int i = 0; i < n; i++) {
    	if (colors[i] == -1) 
    		if (!dfs(i, 0)) {
    			cout << "NO" << endl;
    			return 0;
    		}
    }
    cout << "YES" << endl;
    
	return 0;
}                     