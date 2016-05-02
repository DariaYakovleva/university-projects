#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
const int N = 10010;
int dp[2][N]; //[position][balance]
int n, m;

int go() {
//	for (int i = 0; i < 2; i++) {
//		for (int j = 0; j <= n + m; j++) {
//			dp[i][j] = 0;
//		}
//	}
	dp[0][0] = 1;
	for (int i = 1; i <= n + m; i++) {
		for (int j = 0; j <= i; j++) {
			int close = (i - j) / 10;
			int open = i - close;
			dp[i % 2][j] = 0;
			if (j > 0 && open <= n) {
				dp[i % 2][j] += dp[(i - 1) % 2][j - 1];
				if (dp[i % 2][j] >= MOD) dp[i % 2][j] -= MOD; 
			}
			if (j + 9 <= n + m && close <= m) {
				dp[i % 2][j] += dp[(i - 1) % 2][j + 9];
				if (dp[i % 2][j] >= MOD) dp[i % 2][j] -= MOD;
			}
		}
	}
//	for (int i = 0; i <= n + m; i++) {
//		for (int j = 0; j <= n + m; j++) {
//			cout << dp[i][j] << " ";
//		}
//		cout << endl;
//	}
	int res = 0;
	for (int i = 0; i <= n; i++) {
		res += dp[(n + m) % 2][i];
		if (res >= MOD) res -= MOD;
	}
	return res;
}

int main() {
	freopen("ticket-office.in", "r", stdin);
	freopen("ticket-office.out", "w", stdout);
	scanf("%d %d", &n, &m);
	printf("%d\n", go());
	
//	long long maxi = 0;
//	int mn = 0;
//	int mm = 0;
//	for (int j = 10000; j <= 10000; j++) {
//		for (int i = 0; i <= j; i++) {
//			n = j - i;
//			m = i;
//			long long res = go();
//			if (res > maxi) {
//				maxi = res; mn = n; mm = m;
//			}
//			if (res == 0) break;
//			cout << n << " " << m << "  " << res << " ;" << mn << " " << mm <<  endl;
//		}
//	}

}
