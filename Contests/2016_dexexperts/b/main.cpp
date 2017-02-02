#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

const long long MOD = 1000000000 + 7;
int dividers[10];

int main() {
	long long n;
	cin >> n;
	for (long long i = 1; i < 10; i++) dividers[i] = 0;
	dividers[2] = 7;
	dividers[3] = 4;
	dividers[5] = 1;
	dividers[7] = 1;
	long long res = 1;
	for (long long i = 1; i < 10; i++) {
		long long cur = (n + i);
		for (long long j = 7; j > 1; j--) {
			while (dividers[j] > 0 && (cur % j == 0)) {
				dividers[j]--;
				cur /= j;
			}
		}
		res = (res * (cur % MOD)) % MOD;
	}
	cout << res << endl;
	return 0;
}       