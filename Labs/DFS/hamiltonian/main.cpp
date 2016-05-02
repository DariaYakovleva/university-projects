#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const int N = 100010;

int n, m;
vector<int> g[N];
vector<int> g2[N];
int depth[N];
bool colors[N];

void dfs(int v) {
    colors[v] = 1;
    if (g2[v].size() == 0) {
    	depth[v] = 0;
    }
    for (int i = 0; i < g2[v].size(); i++) {
    	int to = g2[v][i];
       	if (colors[to] == 0) {
    		dfs(to);
    	}
    	depth[v] = max(depth[v], depth[to] + 1);
    }
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = 0;
		depth[i] = -1;
	}
}

int main() {
	freopen("hamiltonian.in", "r", stdin);
	freopen("hamiltonian.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
		g2[b].push_back(a);
	}
	int s = -1;
	for (int i = 0; i < n; i++) {
		if (g[i].size() == 0) {
			s = i;
			dfs(s);
			break;
		}
    }
    if (s != -1 && (depth[s] == n - 1)) {
    	cout << "YES" << endl;
    } else {
    	cout << "NO" << endl;
    }	


	return 0;
}