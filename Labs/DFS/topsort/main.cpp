#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const int N = 100010;

int n, m;
vector<int> g[N];
vector<int> res;
int colors[N];
bool circle = false;

void top_sort(int v) {
    colors[v] = 1;
    for (int i = 0; i < g[v].size(); i++) {
    	int to = g[v][i];
    	if (colors[to] == 1) {
    		circle = true;
    	}
    	if (colors[to] == 0) {
    		top_sort(to);
    	}
    }
    colors[v] = 2;
    res.push_back(v);
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = 0;
	}
}

int main() {
	freopen("topsort.in", "r", stdin);
	freopen("topsort.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
	}
	for (int i = 0; i < n; i++) {
		if (colors[i] == 0) {
			top_sort(i);
		}
	}
	if (circle) {
		cout << "-1" << endl;
		return 0;
	}
	reverse(res.begin(), res.end());
	for (int i = 0; i < n; i++) {
		cout << res[i] + 1 << " ";
	}
	


	return 0;
}