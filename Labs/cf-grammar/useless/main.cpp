#include <bits/stdc++.h>

using namespace std;
const int N = 1010;

char start;
vector< pair< vector<int>, vector<int> > > g[N]; //term, nonterm
string s;
int n;
set<int> all;
set<int> terms;
set<int> reach;

void go_terms() {
	int ss = (int)terms.size();
	for (int i: all) {
		bool ok = false;
		for (int j = 0; j < (int)g[i].size(); j++) {
			bool cur = false;
			if (!g[i][j].first.empty()) cur = true;
			for (int x: g[i][j].second) if (terms.find(x) != terms.end()) cur = true;
			if (cur) ok = true;			
		}
		if (ok) terms.insert(i);
	}
	if ((int)terms.size() > ss) go_terms();
}

void go_reach() {
	int ss = (int)reach.size();
	for (int i : reach) {
		for (int j = 0; j < (int)g[i].size(); j++) {
			for (int x: g[i][j].second) reach.insert(x);
		}
	}
	if ((int)reach.size() > ss) go_reach();
}


int main() {
	freopen("useless.in", "r", stdin);
	freopen("useless.out", "w", stdout);
	cin >> n >> start;
	getline(cin, s);
	for (int i = 0; i < n; i++) {
		getline(cin, s);
		vector<int> term;
		vector<int> nterm;
		int from = s[0] - 'A';
		all.insert(from);
		for (int j = 5; j < (int)s.size(); j++) {
			if (s[j] >= 'a' && s[j] <= 'z') {
				term.push_back(s[j] - 'a');
			} else {
				nterm.push_back(s[j] - 'A');
				all.insert(s[j] - 'A');
			}	
		}
		g[from].push_back(make_pair(term, nterm));
	}
	go_terms();
	reach.insert(start - 'A');
	go_reach();

	for (int c: all) 
		if (reach.find(c) == reach.end() || terms.find(c) == terms.end()) {
			cout << (char)(c + 'A') << " ";
		}
	
	return 0;
}