#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
const int N = 110;
long long dp[N][N][N]; //[position][balance]
int n, m;

long long go() {
	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= m; j++) {
			for (int k = 0; k <= n; k++) {
				dp[i][j][k] = 0;
			}
		}
	}
	dp[0][0][0] = 1;
	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= m; j++) {
			for (int k = 0; k <= n; k++) { 
				if (k == i - (9 * j)) {
					if (i > 0 && k > 0) {
						dp[i][j][k] += dp[i - 1][j][k - 1];
					}
					if (j > 0) {
						dp[i][j][k] += dp[i][j - 1][k + 9];
					}
					if (dp[i][j][k] > MOD) dp[i][j][k] -= MOD;
					if (dp[i][j][k] > MOD) dp[i][j][k] -= MOD;
				}
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
		res = (res + dp[n][m][i]) % MOD;
	}
	return res % MOD;
}

int main() {
//	freopen("ticket-office.in", "r", stdin);
//	freopen("ticket-office.out", "w", stdout);
	cin >> n >> m;
	cout << go() << endl;
//	for (int j = 0; j <= 100; j++) {
//		for (int i = 0; i <= j; i++) {
//			n = i;
//			m = j - i;
//			long long res = go();
//			if (res > 0) {
//				cout << n << " " << m << "  " << res << endl;
//			}
//		}
//	}
	
}
