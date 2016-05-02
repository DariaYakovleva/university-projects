#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

const int N = 310;
const int INF = 1000000010;

int n;
int arr[N][N];
bool used[N];
int math[N], math2[N];
int v[N], u[N];
vector<int> g[N];
bool used2[N];
bool used3[N];

bool dfs(int v) {
	if (used[v]) return false;
	if (v == -1) return false;
	used[v] = true;
	for (size_t i = 0; i < g[v].size(); i++) {
		int to = g[v][i];
		if ((math[to] == -1) || dfs(math[to])) {
			math[to] = v;
			math2[v] = to;
			return true;
		}
	}
	return false; 
	
}

void ddfs(int v) {
	used2[v] = true;
	for (size_t i = 0; i < g[v].size(); i++) {
		used3[g[v][i]] = true;
		int to = math[g[v][i]];
		if (!used2[to]) ddfs(to);
	}
}

int hung() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			u[i] = min(u[i], arr[i][j]);
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			v[j] = min(v[j], arr[i][j] - u[i]);
		}
	}
	while (1) {
		for (int i = 0; i < n; i++) {
			g[i].clear();
			for (int j = 0; j < n; j++) {
		//		cout << i <<  " " << j << " " << u[i] << " "<< v[j] << " " << arr[i][j] << endl;
				if (u[i] + v[j] == arr[i][j]) {
					g[i].push_back(j);
		//			cout << i << " " << j << endl;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			math[i] = -1;
			math2[i] = -1;
			used2[i] = 0;
			used3[i] = 0;
		}
	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				used[j] = false;
			}
			dfs(i);
		}
		for (int i = 0; i < n; i++) {
			if (math2[i] == -1) {
				if (!used2[i]) ddfs(i);
			}
		}
		int d = INF;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
		    	if ((!used3[j]) && used2[i]) {
		    		d = min(d, arr[i][j] - u[i] - v[j]);
		    	}
			}
		}
		if (d == INF || d == 0) break;
		for (int i = 0; i < n; i++) {
		    if (used2[i]) u[i] += d;
		}
		for (int i = 0; i < n; i++) {
		    if (used3[i]) v[i] -= d;
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++)
//				cout << arr[i][j] - u[i] - v[j] << " " ;
//				cout << endl;
//		}			
	}
	int res = 0;
	for (int i = 0; i < n; i++) {
		if (math[i] != -1) res += arr[math[i]][i];
	}
	return res;
}

void init() {
	for (int i = 0; i < n; i++) {
		u[i] = arr[i][i];
		v[i] = arr[i][i];
		math[i] = -1;
		math2[i] = -1;
	}
}

int main() {
	freopen("assignment.in", "r", stdin);
	freopen("assignment.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}
	init();
	cout << hung() << endl;
	for (int i = 0; i < n; i++) {
		if (math[i] != -1) 
			cout << math[i] + 1 << " " << i + 1 << endl;
	}

	return 0;
}