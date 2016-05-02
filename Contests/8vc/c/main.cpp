#include <bits/stdc++.h>

using namespace std;

long long n, m;

long long f(long long x) {
	return x / 2 + x / 3 - x / 6;
}

int main() {
	cin >> n >> m;
	long long l = 0, r = (n + m) * 3;
	while (r - l > 1) {
		long long mm = (l + r) / 2;
		if (f(mm) < n + m) {
			l = mm;
		} else {
			r = mm;
		}
	}
	cout << max(n * 2, max(m * 3, l + 1)) << endl;
}