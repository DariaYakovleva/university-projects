#include <bits/stdc++.h>

using namespace std;

const int N = 1000010;
const long long MOD = 1000000007;

long long fact[N];


void cntFact() {
	fact[0] = 1;
	fact[1] = 1;
	for (int i = 2; i < N; i++) {
		fact[i] = (i * fact[i - 1]) % MOD;
	}
}

long long c(int nn, int kk) {
	if (kk > nn) return 0;
	long long a = fact[nn];
	long long b = (fact[kk] * fact[nn - kk]) % MOD;
	return a / b;
}

int main() {
	int q;
	cin >> q;
	cntFact();
	for (int f = 0; f < q; f++) {
		long long n, k;
		cin >> n >> k;
		long long aall = c(n, k);
		cout << aall << endl;
		long long notok = (((n - 2) * c(n - 3, k - 1)) % MOD + (2 * c(n - 2, k - 1)) % MOD) % MOD;
		cout << notok << endl;
		cout << aall - notok << endl;

	}


	return 0;
}
