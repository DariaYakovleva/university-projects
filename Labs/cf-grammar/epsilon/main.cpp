#include <bits/stdc++.h>

using namespace std;
const int N = 1010;

char start;
vector< pair< vector<int>, vector<int> > > g[N]; //term, nonterm
int zero[N];
string s;
int n;

int go(int v) {
	if (zero[v] != 0) return zero[v];
	if (g[v].empty()) zero[v] = 1;
	for (int i = 0; i < (int)g[v].size(); i++) {
		int cur = 0;
		if (!g[v][i].first.empty()) cur = -1;
		for (int to: g[v][i].second) {
			if (zero[to] == 0) {
				if (go(to) == -1) cur = -1;
			}
			if (zero[to] == -1) cur = -1;
		}
		if (cur == 0) cur = 1;
		if (cur == 1) zero[v] = 1;
	}
	if (zero[v] == 0) zero[v] = -1;
	return zero[v];
}

void init() {
	for (int i = 0; i < n; i++) {
		zero[i] = 0;
	}
}

int main() {
	freopen("epsilon.in", "r", stdin);
	freopen("epsilon.out", "w", stdout);
	cin >> n >> start;
	init();
	getline(cin, s);
	for (int i = 0; i < n; i++) {
		getline(cin, s);
		vector<int> term;
		vector<int> nterm;
		int from = s[0] - 'A';
		for (int j = 5; j < (int)s.size(); j++) {
			if (s[j] >= 'a' && s[j] <= 'z') {
				term.push_back(s[j] - 'a');
			} else {
				nterm.push_back(s[j] - 'A');
			}	
		}
		g[from].push_back(make_pair(term, nterm));
	}
	go(start - 'A');
	for (int i = 0; i < N; i++) {
		if (zero[i] == 1) cout << (char)(i + 'A') << " ";
	}
	
	return 0;
}