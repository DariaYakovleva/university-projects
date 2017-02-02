#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

long long f(long long x) {
	return x * (x + 1) / 2;
}

int main() {
	long long n, k;
	cin >> n >> k;
	long long l = -1, r = min(n + 1, (long long)1000000000 + 5 * 100000000);
	while (r - l > 1) {
		long long m = (r + l) / 2;
		if (f(m) <= k) {
			l = m;
		} else {
			r = m;
		}
	}
	long long cnt = l;
	long long res = k - f(cnt);
	if (res == 0) res = cnt;
	cout << res << endl;

	return 0;
}       