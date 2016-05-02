#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 100010;
long long n, m, k;

long long solve(long long x) {
	long long res = 0;
	for (int i = 1; i <= n; i++) {
		res += min((long long)((x - 1) / i), m);	
	}
	return res;
}

long long bs() {
	long long l = 1, r = n * m + 1;
	while (r - l > 1) {
	 	long long m = (r + l) / 2;
		if (solve(m) < k) {
			l = m;
		} else {
			r = m;
		}
	}
	return l;
}

int main() {
	cin >> n >> m >> k;
	cout << bs() << endl;


	return 0;
}