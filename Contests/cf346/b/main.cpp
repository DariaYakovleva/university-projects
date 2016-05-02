#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

const int N = 10010;
vector< pair<int, string> > g[N];
int n, m;

bool cmp(pair<int, string> a, pair<int, string> b) {
	return a.first > b.first;
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		string a;
		cin >> a;
		int x, y;
		cin >> x >> y;
		x--;
		g[x].push_back({y, a});
	}
	for (int i = 0; i < m; i++) {
		sort(g[i].begin(), g[i].end(), cmp);
		if (g[i].size() == 2) {
			cout << g[i][0].second << " " << g[i][1].second << endl;
		} else {
			if (g[i][1].first == g[i][2].first) {
				cout << "?" << endl;
			} else {
				cout << g[i][0].second << " " << g[i][1].second << endl;
			}
		}
	}

	return 0;
}