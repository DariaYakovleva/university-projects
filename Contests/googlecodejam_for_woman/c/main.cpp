#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
#include <string>

using namespace std;

long long d[2][1010];
long long c, v, l;

const int MOD = 1000000007;

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> c >> v >> l;
		d[0][0] = 0;
		d[1][0] = 0;
		d[0][1] = 0;
		d[1][1] = v;
		for (int i = 2; i <= l; i++) {
			d[0][i] = (d[1][i - 1] * c) % MOD;
			d[1][i] = ((d[1][i - 1] + d[0][i - 1]) * v) % MOD;
		}
		cout << "Case #" << i + 1 << ": "; 
		cout << (d[0][l] + d[1][l]) % MOD << endl;
	}

	return 0;
}
