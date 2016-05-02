#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
const int N = 1010;
long long dp[N][N]; //[position][balance]
int n, m;

long long go() {
	for (int i = 0; i <= n + m; i++) {
		for (int j = 0; j <= n + m; j++) {
			dp[i][j] = 0;
		}
	}
	dp[0][0] = 1;
	// open = balance + (pos - balance) // 2
	for (int i = 1; i <= n + m; i++) {
		for (int j = 0; j <= i; j++) {
			int close = (i - j) / 10;
			int open = i - close;
			dp[i][j] = 0;
			if (j > 0 && open <= n) {
				dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
			}
			if (j + 9 <= n + m && close <= m) {
				dp[i][j] = (dp[i][j] + dp[i - 1][j + 9]) % MOD;
			}
		}
	}
//	for (int i = 0; i <= n + m; i++) {
//		for (int j = 0; j <= n + m; j++) {
//			cout << dp[i][j] << " ";
//		}
//		cout << endl;
//	}
	long long res = 0;
	for (int i = 0; i <= n; i++) {
		res = (res + dp[n + m][i]) % MOD;
	}
	return res;
}

int main() {
	freopen("ticket-office.in", "r", stdin);
	freopen("ticket-office.out", "w", stdout);
	cin >> n >> m;
	cout << go() << endl;	
}
