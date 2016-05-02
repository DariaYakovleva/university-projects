#include <bits/stdc++.h>

using namespace std;


const long long M = 1000000007;

long long power(long long a, long long b) {
	long long res = 1;
	for (long long i = 1; i <= b; i++) {
		res = (res * a) % M;
	}	
	return res;
}

int main() {
	int n;
	cin >> n;
	cout << ((power(27, n) - power(7, n) + M) % M) << endl;

}