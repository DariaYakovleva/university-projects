#include <iostream>
#include <cstdio>
#include <cmath>
#include <algorithm>

using namespace std;

long long countX(long long x) {
	if (x < 0) return 0;
	long long cnt = (long long)pow((double)(x / 36000), 1.0/6);
	long long maxX = 0;
	for (long long i = max((long long)0, cnt - 5); i < cnt + 5; i++) {
		if (i * i * i * i * i * i * 36000 <= x) {
			maxX = i;
		}
	}
	return maxX + 1;
}


int main() {
    freopen("exam.in", "r", stdin);
    freopen("exam.out", "w", stdout);
	long long l, r;
	cin >> l >> r;       
	cout << countX(r) - countX(l - 1) << endl;
}
