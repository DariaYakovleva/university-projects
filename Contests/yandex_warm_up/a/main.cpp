#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
set<int> g[N];
vector< pair<int, int> > ans;

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int n, m, k;
	cin >> n >> m >> k;
	int cnt = 0;
	for (int j = 0; j < n; j++) {
		int s = (int)g[j].size();
		for (int h = 1; h <= k - s; h++) {
			int x = j;
			int y = (j + h) % n;
			g[x].insert(y);
			g[y].insert(x);
			ans.push_back(make_pair(x, y));
			cnt++;	
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) if (i != j) {
			if (cnt < m) {
				if (g[i].count(j) == 0) {
					g[i].insert(j);
					g[j].insert(i);
					ans.push_back(make_pair(i, j));
					cnt++;					
				}	
			}
		}
	}
	for (int i = 0; i < m; i++) {
		cout << ans[i].first + 1 << " " << ans[i].second + 1 << endl;
	}

	return 0;
}