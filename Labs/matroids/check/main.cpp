#include <bits/stdc++.h>

using namespace std;

const int N = 100010;
vector<int> g[N];
int n;
int m;
bool first = false;
bool second = false;
bool third = false;
vector<int> elem;
bool have[N];

int take_mask(int pos) {
	int res = 0;
	for (int j = 0; j < (int)g[pos].size(); j++) {
		for (int k = 0; k < (int)elem.size(); k++) {
			if (g[pos][j] == elem[k]) {
				res += (1 << k);
				break;
			}
		}
	}
	return res;
}

int main() {
	freopen("check.in", "r", stdin);
	freopen("check.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a;
		cin >> a;
		for (int j = 0; j < a; j++) {
			int b;
			cin >> b;
			b--;
			g[i].push_back(b);
			bool h = false;
			for (int k = 0; k < (int)elem.size(); k++) {
				if (elem[k] == b) h = true;
			}
			if (!h) elem.push_back(b);
		}
		if (g[i].empty()) first = true;
	}
	sort(elem.begin(), elem.end());	
	for (int i = 0; i < (1 << n); i++) have[i] = false;
	for (int i = 0; i < m; i++) {
		int cur = take_mask(i);
		have[cur] = true;
	}
	//check2
	second = true;
	for (int i = 0; i < m; i++) {
		int cnt = (int)g[i].size();
		for (int mask = 1; mask < (1<<cnt); mask++) {
			int cur = 0;
			for (int j = 0; j < cnt; j++) {
				if (((1 << j) & mask) > 0) {
					for (int k = 0; k < (int)elem.size(); k++) {
						if (g[i][j] == elem[k]) {
							cur += (1 << k);
						}
					}
				}
			}
			if (!have[cur]) second = false;			
		}
	}
	third = true;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < m; j++) if (g[i].size() > g[j].size()) {
			int a = take_mask(i);
			int b = take_mask(j);
			for (int k = 0; k < m; k++) {
				if ((b & (1 << k)) > 0) {
					if ((a & (1 << k)) > 0) {
						a = a ^ (1 << k);
					}
				} 
			}
			bool h = false;
			for (int k = 0; k < m; k++) {
				if ((a & (1 << k)) > 0) {
					int nb = b;
					nb += (1 << k);
					if (have[nb]) {
						h = true;
						break;
					}
				}
			}
			if (!h) third = false;
		}
	}
	if (first && second && third) {
		cout << "YES" << endl;
	} else {
		cout << "NO" << endl;
	}
}
