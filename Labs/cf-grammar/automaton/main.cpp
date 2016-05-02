#include <bits/stdc++.h>

using namespace std;
const int N = 1010;

char start;
vector< pair<int, int> > g[N]; //term, nonterm
string s;

bool check(int pos, int v) {
//	cout << pos << " " << v << endl;
	if (pos == (int)s.size()) {
		return (v == -1);
	}
	if (v == -1) return false;
	int c = s[pos] - 'a';
	for (pair<int, int> to: g[v]) {
			if (to.first == c) {
				if (check(pos + 1, to.second)) return true;
			}
	}
	return false;
}


int main() {
	freopen("automaton.in", "r", stdin);
	freopen("automaton.out", "w", stdout);
	int n;
	cin >> n >> start;
	getline(cin, s);
	for (int i = 0; i < n; i++) {
		getline(cin, s);
		int pos = 0;
		while (!(s[pos] >= 'A' && s[pos] <= 'Z')) pos++;
		int from = s[pos] - 'A';
		pos++;
		while (!(s[pos] >= 'a' && s[pos] <= 'z')) pos++;
		int to = -1;
		int term = s[pos] - 'a';
		pos++;
		if (pos < (int)s.size()) {
			to = s[pos] - 'A'; 
		}
		g[from].push_back(make_pair(term, to));
	}
	int m;
	cin >> m;
	getline(cin, s);
	for (int i = 0; i < m; i++) {
		getline(cin, s);
		if (check(0, start - 'A')) {
			cout << "yes" << endl;
		} else {
			cout << "no" << endl;
		}
	}	

	return 0;
}