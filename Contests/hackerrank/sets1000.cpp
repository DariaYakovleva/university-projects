#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
const long long MOD = 1000000007;
long long ans[N][N];

long long cnt(long long nn, long long kk) {
	if (kk < 0 || nn < 0 || nn < kk) return 0;
	if (kk == 0 || (nn == kk && kk > 1)) ans[nn][kk] = 1;
	if (ans[nn][kk] != -1) return ans[nn][kk];
	long long res = 0;
	for (int i = 1; i <= nn - kk + 1; i++) {
		res = (res + cnt(nn - i - 1, kk - 2)) % MOD;
		res = (res + cnt(nn - i - 3, kk - 3)) % MOD;
		if (kk == 3 && (i + 2 == nn)) res = (res + 1) % MOD;
	}
	ans[nn][kk] = res % MOD;
	return ans[nn][kk];
}

int main() {
	int q;
	cin >> q;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			ans[i][j] = -1;
		}
	}
	for (int i = 0; i < 20; i++) {
		cout << i << "| ";
		for (int j = 0; j < 20; j++) {
			cout << cnt(i, j) << " ";
		}
		cout << endl;
	}
	for (int f = 0; f < q; f++) {
		long long n, k;
		cin >> n >> k;
		cout << cnt(n, k) << endl;

	}

	return 0;
}
	