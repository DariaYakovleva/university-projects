#include <bits/stdc++.h>

using namespace std;
const int N = 110;
const int M = 1000000007;

char start;
vector< pair<int, int> > g[N];
long long dp[N][N][N];
string s;
int n;


long long ddp() {
	for (int i = 0; i < (int)s.size(); i++) {
		for (int j = 0; j < 26; j++) {
			for (pair<int, int> x: g[j]) {
				if (x.second == -1 && ((s[i] - 'a') == x.first)) {
					dp[j][i][i] += 1;
				}
			} 
		}
	}
	for (int len = 2; len <= (int)s.size(); len++) {
		for (int st = 0; st <= (int)s.size() - len; st++) {
			int en = st + len - 1;
			for (int k = st; k < en; k++) {
				for (int i = 0; i < 26; i++) {
					for (pair<int, int> x: g[i]) {
						if (x.second != -1) {
							dp[i][st][en] = (dp[i][st][en] + (dp[x.first][st][k] * dp[x.second][k + 1][en]) % M) % M;
						}
					} 				
				}
			}
		}
	}
	return dp[start - 'A'][0][s.size() - 1];
}

                                                                                                            	
void init() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				dp[i][j][k] = 0;
			}
		}
	}
}
int main() {
	freopen("nfc.in", "r", stdin);
	freopen("nfc.out", "w", stdout);
	init();
	cin >> n >> start;
	getline(cin, s);
	for (int i = 0; i < n; i++) {
		getline(cin, s);
		int from = s[0] - 'A';
		if (s[5] >= 'a' && s[5] <= 'z') {
			g[from].push_back(make_pair(s[5] - 'a', -1));
		} else {
			g[from].push_back(make_pair(s[5] - 'A', s[6] - 'A'));
		}	
	}
	getline(cin, s);
	cout << ddp() << endl;
	return 0;
}