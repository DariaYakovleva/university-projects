#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const int N = 100010;

int n, m;
vector<int> g[N];
int colors[N];
int p[N];
int c_beg, c_end;
vector<int> res;

bool dfs(int v) {
    colors[v] = 1;
    for (int i = 0; i < g[v].size(); i++) {
    	int to = g[v][i];
    	if (colors[to] == 1) {
    		c_beg = to;
    		c_end = v;
    		return true;
    	}
    	if (colors[to] == 0) {
    		p[to] = v;
    		if (dfs(to)) return true;
    	}
    }
    colors[v] = 2;
    return false;
}


void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = 0;
		p[i] = -1;
	}
}

int main() {
	freopen("cycle.in", "r", stdin);
	freopen("cycle.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
	}
	bool cycle = false;
	for (int i = 0; i < n; i++) {
		if (colors[i] == 0) {
			if (dfs(i)) {
				cycle = true;
			    break;
			}
		}
	}
	if (cycle) {
		cout << "YES" << endl;
		while (c_beg != c_end) {
			res.push_back(c_end);
			c_end = p[c_end];
		}
		res.push_back(c_beg);          
		for (int i = res.size() - 1; i >= 0; i--) {
			cout << res[i] + 1 << " ";
		}
		cout << endl;
	} else {
		cout << "NO" << endl;
	}
	return 0;
}