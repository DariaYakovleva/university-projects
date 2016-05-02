#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

const int N = 20010;
const int INF = 10000010;
struct vertex {
	int v, edge;
	vertex() {}
	vertex(int a, int b) {
		v = a;
		edge = b;
	}
};
vector<vertex> g[N];
bool used[N];
int n, m;
int timer = 0;
int tin[N];
int fmin[N];
vector<int> res;

void dfs(int v, int p) {
	used[v] = true;
	tin[v] = timer;
	fmin[v] = timer;
	timer++;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i].v, num = g[v][i].edge;
		if (num != p) {
			if (used[to]) {
				fmin[v] = min(fmin[v], tin[to]);
			} else {
				dfs(to, num);
				fmin[v] = min(fmin[v], fmin[to]);
				if (fmin[to] > tin[v]) {
					res.push_back(num);
				}
			}
		}
	}
}

void init() {
	for (int i = 0; i < N; i++) {
		used[i] = false;
		tin[i] = INF;
		fmin[i] = INF;
	}
}

int main() {
	freopen("bridges.in", "r", stdin);
	freopen("bridges.out", "w", stdout);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
    	int a, b;
    	cin >> a >> b;                     
    	a--;
    	b--;
    	g[a].push_back(vertex(b, i));
    	g[b].push_back(vertex(a, i));
    }
    init();
    for (int i = 0; i < n; i++) {
    	if (!used[i]) {
    		dfs(i, -1);
    	}
    }
    sort(res.begin(), res.end());
    cout << res.size() << endl;
    for (int i = 0; i < res.size(); i++) 
    	cout << res[i] + 1 << " ";
    

	return 0;
}                     