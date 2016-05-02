#include <iostream>
#include <cstdio>

using namespace std;


bool isPrime1(long long a) {
	long long x = 2;
	while (x * x <= a) {
		if (a % x == 0) return false;
		x++;
	}
	return true;
}

bool ok(long long a) {
	long long x = 2;
	long long cnt = 2; 
	while (x * x <= a) {
		if (a % x == 0) {
			if (x * x == a) cnt++;
			else cnt += 2;
		}
		x++;			
	}
	if (cnt == 2) return false;
	return isPrime1(cnt);
}

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	long long n, m;
	cin >> n >> m;
	long long res = 0;
	for (long long i = n; i <= m; i++) {
		if (ok(i)) res++;
	}
	cout << res << endl;

	return 0;
}