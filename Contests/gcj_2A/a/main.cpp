#include <bits/stdc++.h>

using namespace std;
const int N = 110;
char arr[N][N];
bool used[N][N];
bool have[N][N];
bool imp = false;
int n, m;

void init() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			used[i][j] = false;
		}
	}
}

void dfs(int i, int j, char prev) {
	if (i == 0 || j == 0 || i == n || j == n) {
		if ((i == 0 && arr[i][j] == '^') || (j == 0 && arr[i][j] == '<') || (i == n && arr[i][j] == 'v') || (j == m && arr[i][j] == '>')) {
			have[i][j] = true;
		}
		if (arr[i][j] == '.') imp = true;
		return;
	}
	if (used[i][j]) return;
	used[i][j] = true;
	if (arr[i][j] == '^') {
		dfs(i - 1, j, arr[i][j]);
	}
	if (arr[i][j] == 'v') {
		dfs(i + 1, j, arr[i][j]);
	}
	if (arr[i][j] == '>') {
		dfs(i, j + 1, arr[i][j]);
	}
	if (arr[i][j] == '<') {
		dfs(i, j - 1, arr[i][j]);
	}	
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		cin >> n >> m;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> arr[i][j];
				have[i][j] = false;
			}
		}
		imp = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != '.') {
					init();
					dfs(i, j, '$');
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cnt += have[i][j];
			}
		}
		if (imp) {
		    cout << "Case #" << t + 1 << ": IMPOSSIBLE" << endl;
		} else {
			cout << "Case #" << t + 1 << ": " << cnt << endl;
		}
	}
	return 0;
}	
