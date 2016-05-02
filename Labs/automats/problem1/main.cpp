#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to;
	char c;
};

const int N = 100010;
string s;
int n, m, k;
vector<edge> dka[N];
bool term[N];

void init() {
	for (int i = 0; i < n; i++) {
		term[i] = false;
	}
}

bool go() {
	int v = 0;
	for (auto c: s) {
		bool have = false;
		for (int i = 0; i < (int)dka[v].size(); i++) {
			if (dka[v][i].c == c) {
				v = dka[v][i].to;
				have = true;
				break;
			}
		}
		if (!have) return false;
	}
	return term[v];
}

int main() {
	freopen("problem1.in", "r", stdin);
	freopen("problem1.out", "w", stdout);
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