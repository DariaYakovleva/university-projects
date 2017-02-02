#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
int n, m;
pair<int, int> houses[N];
pair<int, int> clients[N];
int pp[N];
bool used[N];
bool usedp[N];
vector<int> g[N];

bool dfs(int v) {
	if (used[v]) return false;
	used[v] = true;
	for (int u: g[v]) {
		if (pp[u] == -1 || dfs(pp[u])) {
			pp[u] = v;
			return true;
		}
	}
	return false;
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> clients[i].first >> clients[i].second;
	}

	for (int i = 0; i < m; i++) {
		cin >> houses[i].first >> houses[i].second;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (houses[j].first > clients[i].first && houses[j].second <= clients[i].second) {
				g[i].push_back(j);				
			}
		}
	}
	for (int i = 0; i < n; i++) {
		usedp[i] = false;
	}
	for (int i = 0; i < n; i++) {
		for (int u: g[i]) {
			if (pp[u] == -1) {
				pp[u] = i;
				usedp[i] = true;
				break;
			}
		}
	}

	for (int i = 0; i < m; i++) {
		pp[i] = -1;
	}
	for (int i = 0; i < n; i++) {
		if (usedp[i]) continue;
		for (int j = 0; j < n; j++) {
			used[j] = false;
		}
		dfs(i);
	}
	int res = 0;
	for (int i = 0; i < m; i++) {
		if (pp[i] != -1) res++;
	}	
	cout << res << endl;

	return 0;
}