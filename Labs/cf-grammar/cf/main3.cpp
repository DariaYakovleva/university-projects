#include <bits/stdc++.h>

using namespace std;
const int N = 110;

int start;
vector< pair<int, int> > g[N * N];
vector<string> gg[30];
int dp[N * N][N][N];
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
	return -1;
}

char chr(int x) {
	return (-x - 2) + 'a'; 
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

int ddp(int state, int st, int en) {
//	cout << "start " << state << " " << st << "  "<< en << " " << dp[state][st][en] << endl;
	if (dp[state][st][en] != -1) return dp[state][st][en];
	dp[state][st][en] = 0;
	if (st == en) {
		for (pair<int, int> x: g[state]) {
			if (x.second == -1 && x.first < -1 && (s[st] == chr(x.first))) {
				dp[state][st][st] = 1;
//				cout << "1111" << endl;
				return dp[state][st][st];
			}
		}
    }
    int i = state;
    int len = en - st + 1;
	for (int k = st - 1; k <= en; k++) {
		for (pair<int, int> x: g[i]) {
			if (x.second == -1 && x.first >= 0) {
				dp[i][st][en] |= ddp(x.first, st, en);
//				cout << "!1!" << dp[i][st][en] << endl;
			}
			if (x.second < -1 && x.first < -1 && len == 2 && chr(x.first) == s[st] && chr(x.second) == s[en]) {
//				cout << "pair " << endl;
				dp[i][st][en] = 1;
			}
			if (x.second >= 0 && x.first < -1 && chr(x.first) == s[st]) {
				dp[i][st][en] |= ddp(x.second, st + 1, en);
//				cout << "!2!" << dp[i][st][en] << endl;
			}
			if (x.second < -1 && x.first >= 0 && chr(x.second) == s[en]) {
				dp[i][st][en] |= ddp(x.first, st, en - 1);
//				cout << "!3!" << dp[i][st][en] << endl;
			}
			if (x.second >= 0 && x.first >= 0) {
				dp[i][st][en] |= (ddp(x.first, st, k) & ddp(x.second, k + 1, en));
//				cout << "!4!" << x.second << " " << x.first <<  " " << k << " " << dp[i][st][en] << endl;
			}
			if (len == 0 && x.first == -1) {
//				cout << "emp " << endl;
				dp[i][st][en] = 1;
			}
		}
	}
//	cout << "end " << state << " " << st << "  "<< en << " " << dp[i][st][en] << endl;
	return dp[i][st][en]; 				
}
                                                                                                            	
void init() {
	for (int i = 0; i < N * N; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				dp[i][j][k] = -1;
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
	if (ddp(start, 0, (int)s.size() - 1)) {
		cout << "yes" << endl;
	} else {
		cout << "no" << endl;
	}
	return 0;
}