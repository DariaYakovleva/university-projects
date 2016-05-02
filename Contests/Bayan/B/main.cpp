#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

const int N = 21 * 21;
int n, m;
vector<int> g[N];
int cnt = 0;
bool used[N];

void dfs(int v) {
	used[v] = true;
	cnt++;
	for (size_t i = 0; i < g[v].size(); i++) {
		if (!used[g[v][i]])
			dfs(g[v][i]);
	}
}

void init() {
	for (int i = 0; i < N; i++) {
		used[i] = false;
	}
	cnt = 0;
}	
            	
int main() {
	cin >> n >> m;
	string s, s2;
	cin >> s;
	cin >> s2;
	for (int i = 0; i < n; i++) {
		if (s[i] == '>') {
			for (int j = 0; j < m - 1; j++) {
				g[i * m + j].push_back(i * m + j + 1);
			}
		} else {
			for (int j = m - 1; j > 0; j--) {
				g[i * m + j].push_back(i * m + j - 1);
			}
		}
	}
	for (int i = 0; i < m; i++) {
		if (s2[i] == 'v') {
			for (int j = 0; j < n - 1; j++) {
				g[j * m + i].push_back((j + 1) * m + i);
			}
		} else {
			for (int j = n - 1; j > 0; j--) {
				g[j * m + i].push_back((j - 1) * m + i);
			}
		}
	}

	for (int i = 0; i < n * m; i++) {
		init();
		dfs(i);
		if (cnt < n * m) {
			cout << "NO" << endl;
			return 0;
	   }
	}
	cout << "YES" << endl;

	return 0;
}