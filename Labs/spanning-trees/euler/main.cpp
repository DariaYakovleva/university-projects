#include <iostream>
#include <cstdio>
#include <set>
#include <algorithm>
#include <vector>

using namespace std;

const int N = 1010;

vector<int> g[N];
vector<int> odd;
int n;
vector<int> res;

void euler(int v) {
		for (int i = 0; i < g[v].size(); i++) {
			if (g[v][i] != -1) {
				int tmp = g[v][i];
				g[v][i] = -1;
				for (int j = 0; j < g[tmp].size(); j++) {
					if (g[tmp][j] == v) {g[tmp][j] = -1; break; }
				}
				euler(tmp);
			}
		}
		res.push_back(v);	
}

int main() {
	freopen("euler.in", "r", stdin);
	freopen("euler.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
		int k;
		cin >> k;
		for (int j = 0; j < k; j++) {
			int x;
			cin >> x;
			if (x % 2 == 1) 
				odd.push_back(i);
			g[i].push_back(x - 1);
		}
	}
	if (odd.size() == 3 || odd.size() == 1) {
		cout << "-1" << endl;
	}
	if (odd.size() == 2) {
		euler(odd[0]);
	} else {
		euler(0);
	}
	cout << res.size() - 1 << endl;
	for (int i = 0; i < res.size(); i++) {
		cout << res[i] + 1 << " ";
	}

	return 0;
}