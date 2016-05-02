#include <bits/stdc++.h>

using namespace std;
const int N = 1010;

char start;
vector< pair< vector<int>, vector<int> > > g[N]; //term, nonterm
string s;
int n;
set<int> res;
void go() {
	int ss = (int)res.size();
	for (int i = 0; i < 26; i++) {
		bool ok = false;
		for (int j = 0; j < (int)g[i].size(); j++) {
			bool cur = true;
			if (!g[i][j].first.empty()) cur = false;
			for (int x: g[i][j].second) if (res.find(x) == res.end()) cur = false;
			if (cur) ok = true;			
		}
		if (ok) res.insert(i);
	}
	if ((int)res.size() > ss) go();
}

int main() {
	freopen("epsilon.in", "r", stdin);
	freopen("epsilon.out", "w", stdout);
	cin >> n >> start;
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
	go();
	for (int c: res) {
		cout << (char)(c + 'A') << " ";
	}
	
	return 0;
}