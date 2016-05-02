#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

const long long N = 10000010;

bool prime[N];
vector<long long> primes;


void eratosph() {
	for (long long i = 0; i < N; i++) prime[i] = true;
	prime[1] = false;
	for (long long i = 2; i * i <= N; i++) {
		if (prime[i]) {
			for (long long j = i * i; j < N; j += i) {
				prime[j] = false;
			}
		}	
	}
	for (int i = 2; i < N; i++) if (prime[i]) primes.push_back(i);
}

long long cnt(long long l, long long r) {
	long long res = 0;
	for (int i = 0; i < primes.size(); i++) {
	    long long pp = primes[i];
		long long a = pp * pp;
		long long x = 2;
		while (a <= r) {
			if (a >= l && prime[x + 1]) res++;
			if (a > r / pp) break;
			a *= pp;
			x++;
		}			
	}
	return res;
}

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	long long n, m;
	cin >> n >> m;
	eratosph();
	long long res = cnt(n, m);
	cout << res << endl;

	return 0;
}