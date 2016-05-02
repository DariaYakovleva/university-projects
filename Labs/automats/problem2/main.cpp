#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to;
	char c;
};

const int N = 110;
string s;
int n, m, k;
vector<edge> dka[N];
bool term[N];
set<int> cur;
set<int> nexts;

void init() {
	for (int i = 0; i < n; i++) {
		term[i] = false;
	}
}

bool go() {
	cur.insert(0);
	for (auto c: s) {
		for (auto k: cur) {
			for (int i = 0; i < (int)dka[k].size(); i++) {
				if (dka[k][i].c == c) {
					nexts.insert(dka[k][i].to);
				}
			}
		}
		cur.clear();
		for (auto k: nexts) {
			cur.insert(k);
		}
		nexts.clear();	
	}
	for (auto k: cur) {
		if (term[k]) return true;
	}
	return false;
}

int main() {
	freopen("problem2.in", "r", stdin);
	freopen("problem2.out", "w", stdout);
	getline(cin, s);
	cin >> n >> m >> k;
	init();
	for (int i = 0; i < k; i++) {
		int a;
		cin >> a;
		term[a - 1] = true;
	}
	for (int i = 0; i < m; i++) {
		int a, b;
		char c;
		cin >> a >> b >> c;
		a--; b--;
		dka[a].push_back({b, c});
	}
	if (go()) {
		cout << "Accepts" << endl;
	} else {
		cout << "Rejects" << endl;
	}

	
	return 0;
}