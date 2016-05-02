#include <bits/stdc++.h>

using namespace std;

long long rever(long long x) {
	if (x % 10 == 0) return x;
	long long res = 0;
	while (x != 0) {
		res = res * 10 + x % 10;
		x /= 10;
	}
	return res;
}

const int N = 1000010;
int dp[N];

long long ddp(long long pos) {
//	cout << pos << endl;
	if (pos < 1) {
		return 10000010;
	}
	if (pos == 1) {
		dp[pos] = 1;
		return 1;
	}
	if (pos < N && dp[pos] != -1) {
		return dp[pos];
	}
	long long r = rever(pos);
	int res;
//	if (pos > 1000) {
//		if ((pos - 1) % 100 == 0)
//			res = ddp(r) + 1;
//		} else {
//			res = ddp(pos - 100) + 1;
//		}
//	} else {
	if (r < pos) {
		res = min(ddp(pos - 1), ddp(r)) + 1;
	} else {
		res = ddp(pos - 1) + 1;
	}
//	}
	if (pos < N) dp[pos] = res;
	return res;
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int i = 0; i < N; i++) {
		dp[i] = -1;
	}
	for (int i = 1; i < 100100; i++) {
		if (abs(ddp(i) - ddp(i + 1)) > 1) cout << i << " " << ddp(i) << endl;
	}      
	for (int t = 0; t < test; t++) {
		int n;
		cin >> n;
		cout << "Case #" << t + 1 << ": " << ddp(n) << endl;
	}

}

