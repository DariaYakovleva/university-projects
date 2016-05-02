#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>

using namespace std;
const int N = 3010;
const long long M = 1000000007;
string s;
int bal = 0;
int minb = N;
int maxb = -N;
long long dp[N][N];

int main() {
	int n, m;
	cin >> n >> m;
	cin >> s;
	for (int i = 0; i < m; i++) {
		if (s[i] == ')') {
			bal--;
		} else {
			bal++;
		}
		minb = min(minb, bal);
		maxb = max(maxb, bal);
	}
	for (int i = 0; i <= n - m; i++) {
		for (int j = 0; j <= n - m; j++) {
			dp[i][j] = 0;
		}
	}
	dp[0][0] = 1;
	for (int i = 1; i <= n - m; i++) {
		for (int j = 0; j <= n - m; j++) {
			if (j > 0) {
				dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % M;
			}
			if (dp[i - 1][j + 1] > 0) {
				dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % M;
			}
		}
	}
	long long res = 0;
	for (int i = 0; i <= n - m; i++) {
		for (int j = 0; j <= n - m; j++) {
			if (minb + j >= 0) {
				int curl = n - m - i;
				int curb = bal + j;
				if (curb > curl) continue;
				res = (res + (dp[i][j] * dp[curl][curb]) % M) % M;
			}	
		}
	}
	cout << res << endl;
	
	return 0;
}

