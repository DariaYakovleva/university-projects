#include <bits/stdc++.h>

using namespace std;
const int N = 110;

int start;
vector< pair<int, int> > g[N * N];
vector<string> gg[30];
bool dp[N * N][N][N];
string s;
int n;
int cur_nterm = 26;

int conv(char c) {
	if (c >= 'a' && c <= 'z') {
		return -(c - 'a' + 2);
	}
	if (c >= 'A' && c <= 'Z') {
		return c - 'A';
	}
}

void too_long() {
	for (int i = 0; i < 26; i++) {
		for (string to: gg[i]) {
			int cur = i;
			for (int j = 0; j < (int)to.size() - 2; j++) {
				g[cur].push_back(make_pair(conv(to[j]), cur_nterm));
				cur = cur_nterm;				
				cur_nterm++;	
			}
			if (to.size() >= 2) {
				g[cur].push_back(make_pair(conv(to[to.size() - 2]), conv(to[to.size() - 1])));				
			} else {
				if (to.size() == 1) g[cur].push_back(make_pair(conv(to[to.size() - 1]), -1));				
				else g[cur].push_back(make_pair(-1, -1));				 
			}
		}
	}	
}

void clear_v() {
	for (int i = 0; i < cur_nterm; i++) {
		int j = 0;
		while (j < (int)g[i].size()) {
			if (g[i][j].first == -1) {
				int p = g[i].size() - 1;
				swap(g[i][j], g[i][p]);
				g[i].pop_back();
			}
			else j++;
		}	
	}
}


set<int> res;
void eps() {
	int ss = (int)res.size();
	for (int i = 0; i < cur_nterm; i++) {
		bool ok = false;
		for (pair<int, int> x: g[i]) {
			if (x.first == -1 || (res.find(x.first) != res.end() && (x.second == -1 || res.find(x.second) != res.end()))) {
				ok = true;
				break;
			}
		}
		if (ok) res.insert(i);
	}
	if ((int)res.size() > ss) {
		eps();
		return;
	}
	for (int i = 0; i < cur_nterm; i++) {
		int nn = (int)g[i].size();
		for (int j = 0; j < nn; j++) {
			pair<int, int> x = g[i][j];
			if (res.find(x.first) != res.end()) {
				g[i].push_back(make_pair(x.second, -1));
			}
			if (res.find(x.second) != res.end()) {
				g[i].push_back(make_pair(x.first, -1));
			}

		}
	}
	clear_v();	
}

set<pair<int, int>> pairs;
void unit() {
	int cnt = (int)pairs.size();
	for (pair<int, int> p: pairs) {
		for (pair<int, int> x: g[p.second]) {
			if (x.second == -1 && x.first >= 0) {
				pairs.insert(make_pair(p.first, x.first));
			}
		}
	}
	if ((int)pairs.size() > cnt) {
		unit();
		return;
	}
	for (pair<int, int> p: pairs) if (p.first != p.second) {
		for (pair<int, int> x: g[p.second]) {
			if (!(x.first >= 0 && x.second == -1)) {
				g[p.first].push_back(x);
			}
		}
	}
	for (int i = 0; i < cur_nterm; i++) {
		for (int j = 0; j < (int)g[i].size(); j++) {
			pair<int, int> x = g[i][j];
			if (x.second == -1 && x.first >= 0) {
				g[i][j].first = -1;
			}
		}
	}
	clear_v();	
}

set<int> terms;
set<int> reach;

void go_terms() {
	int ss = (int)terms.size();
	for (int i = 0; i < cur_nterm; i++) {
		bool ok = false;
		for (pair<int, int> x: g[i]) {
			if (terms.find(x.first) != terms.end() || terms.find(x.second) != terms.end()) ok = true;
			if (x.first < -1 || x.second < -1) ok = true;
		}
		if (ok) terms.insert(i);
	}
	if ((int)terms.size() > ss) {
		go_terms();
		return;
	}
}

void go_reach() {
	int ss = (int)reach.size();
	for (int i : reach) {
		for (pair<int, int> x: g[i]) {
			if (x.first >= 0) reach.insert(x.first);
			if (x.second >= 0) reach.insert(x.second);
//			cout << ss << " " << i << " " << x.first << " " << x.second << endl;
		}
	}
	if ((int)reach.size() > ss) {
		go_reach();
		return;
	}
}

void useless() {
//	for (int x: terms) cout << "t" << x;
//	for (int x: reach) cout << "r" << x;
	for (int i = 0; i < cur_nterm; i++) {
		if (terms.find(i) == terms.end() || reach.find(i) == reach.end()) {
			g[i].clear();
			continue;
		}
		for (int j = 0; j < (int)g[i].size(); j++) {
			pair<int, int> x = g[i][j];
			if (x.first >= 0 && (terms.find(x.first) == terms.end() || reach.find(x.first) == reach.end())) {
				g[i][j].first = -1;
				g[i][j].second = -1;
			}
			if (x.second >= 0 && (terms.find(x.second) == terms.end() || reach.find(x.second) == reach.end())) {
				g[i][j].first = -1;
				g[i][j].second = -1;
			}
		}
	}
	clear_v();
}

void termss() {
	for (int i = 0; i < cur_nterm; i++) {
		for (int j = 0; j < (int)g[i].size(); j++) {
			pair<int, int> x = g[i][j];
			if (x.first < -1 && x.second < -1) {
				g[cur_nterm].push_back(make_pair(x.first, -1));
				cur_nterm++;
				g[cur_nterm].push_back(make_pair(x.second, -1));
				cur_nterm++;				
				g[i][j] = make_pair(cur_nterm - 2, cur_nterm - 1);
			} else if (x.first < -1 && x.second >= 0) {
				g[cur_nterm].push_back(make_pair(x.first, -1));
				cur_nterm++;
				g[i][j] = make_pair(cur_nterm - 1, x.second);
			} else if (x.first >= 0 && x.second < -1) {
				g[cur_nterm].push_back(make_pair(x.second, -1));
				cur_nterm++;
				g[i][j] = make_pair(x.first, cur_nterm - 1);
			}
		}
	}
}

void ok() {
	for (int i = 0; i < cur_nterm; i++) {
		for (int j = 0; j < (int)g[i].size(); j++) {
			pair<int, int> x = g[i][j];
			if (x.first < -1) x.first = abs(x.first) - 2;
			if (x.second < -1) x.second = abs(x.second) - 2;
			g[i][j] = x;
//				cout << i << " " << g[i][j].first << " " << g[i][j].second << endl;
		}
	}
}

bool ddp() {
	for (int i = 0; i < (int)s.size(); i++) {
		for (int j = 0; j < cur_nterm; j++) {
			for (pair<int, int> x: g[j]) {
				if (x.second == -1 && ((s[i] - 'a') == x.first)) {
					dp[j][i][i] = true;
				}
			} 
		}
	}
	for (int len = 2; len <= (int)s.size(); len++) {
		for (int st = 0; st <= (int)s.size() - len; st++) {
			int en = st + len - 1;
			for (int k = st; k < en; k++) {
				for (int i = 0; i < cur_nterm; i++) {
					for (pair<int, int> x: g[i]) {
						if (x.second != -1 && x.first != -1) {
							dp[i][st][en] |= (dp[x.first][st][k] & dp[x.second][k + 1][en]);
						}
					} 				
				}
			}
		}
	}
	return dp[start][0][s.size() - 1];
}


                                                                                                            	
void init() {
	for (int i = 0; i < N * N; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				dp[i][j][k] = false;
			}
		}
	}
}
int main() {
	freopen("cf.in", "r", stdin);
	freopen("cf.out", "w", stdout);
	init();
	char d;
	cin >> n >> d;
	start = d - 'A';
	getline(cin, s);
	for (int i = 0; i < n; i++) {
		getline(cin, s);
		int from = s[0] - 'A';
		string to = "";
		for (int j = 5; j < (int)s.size(); j++) {
			to += s[j];
		}	
		gg[from].push_back(to);
	}
	getline(cin, s);

//	cout << s << endl;
	too_long();
//	cout << "long " << endl;
	eps();
//	cout << "eps" << cur_nterm << endl;
	for (int i = 0; i < cur_nterm; i++) pairs.insert(make_pair(i, i));
	unit();
//	cout << "unit" << endl;
	go_terms();
//	cout << "terms" << endl;
	reach.insert(start);
	go_reach();
//	cout << "reach" << cur_nterm << " " << start << endl;
	useless();

	termss();
	ok();
	if (ddp()) {
		cout << "yes" << endl;
	} else {
		cout << "no" << endl;
	}
	return 0;
}