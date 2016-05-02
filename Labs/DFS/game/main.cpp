#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const int N = 100010;
const int INF = 1000010;

int n, m, s;
vector<int> g[N];
int colors[N];
bool who[N];

bool dfs(int v) {
    colors[v] = 1;
    if (g[v].size() == 0) {
    	return who[v] = false;
    }
    bool win = false;
    for (int i = 0; i < g[v].size(); i++) {
    	int to = g[v][i];                   
    	if (colors[to] == 0) {
    		dfs(to);
    	}
    	if (who[to] == false)
    		win = true;
    }
    return who[v] = win;
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = 0;
		who[i] = false;
	}
}

int main() {
	freopen("game.in", "r", stdin);
	freopen("game.out", "w", stdout);
	cin >> n >> m >> s;
	s--;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
	}
	init();
	dfs(s);
	if (who[s]) {
		cout << "First player wins" << endl;
	} else {
		cout << "Second player wins" << endl;
	}
	


	return 0;
}