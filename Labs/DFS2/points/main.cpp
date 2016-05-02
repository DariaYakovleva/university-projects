#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

const int N = 20010;
const int INF = 10000010;
vector<int> g[N];
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
	int ch = 0;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i];
		if (to != p) {
			if (used[to]) {
				fmin[v] = min(fmin[v], tin[to]);
			} else {
				dfs(to, v);
				ch++;
				fmin[v] = min(fmin[v], fmin[to]);
				if (fmin[to] >= tin[v] && p != -1) {
					res.push_back(v);
				}
			}
		}
	}
	if (p == -1 && ch > 1) 
		res.push_back(v);
}

void init() {
	for (int i = 0; i < N; i++) {
		used[i] = false;
		tin[i] = INF;
		fmin[i] = INF;
	}
}

int main() {
	freopen("points.in", "r", stdin);
	freopen("points.out", "w", stdout);
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
    	if (!used[i]) {
    		dfs(i, -1);
    	}
    }
    sort(res.begin(), res.end());
    int k = 0;
    for (int i = 0; i < res.size(); i++) {
    	if (i == 0) {
    		k++;
    		continue;
    	}
    	if (res[i] != res[i - 1]) k++;
    }
    cout << k << endl;
    for (int i = 0; i < res.size(); i++) {
    	if (i == 0) {
    		cout << res[i] + 1 << endl;
       		continue;
    	}
    	if (res[i] != res[i - 1]) 
    		cout << res[i] + 1 << endl;
    }


	return 0;
}                     