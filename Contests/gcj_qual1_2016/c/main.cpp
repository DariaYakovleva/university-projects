#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <algorithm>

using namespace std;

int n, j;
vector<int> ch;
vector<long long> res;

long long isPrime(long long p) {
	for (long long x = 2; x < min(p, (long long)1000000); x++) {
		if (p % x == 0) return x;
	}
	return -1;
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int tests;
	cin >> tests;
	for (int tt = 0; tt < tests; tt++) {
		cin >> n >> j;
		int nn = 0;
		cout << "Case #" << (tt + 1) << ":" << endl;
		for (long long cur = 0; cur < (1 << (n - 2)); cur++) {
			if (nn == j) break;
			ch.clear();
			res.clear();
			ch.push_back(1);
			long long cc = cur;
			for (int i = 0; i < (n - 2); i++) {
				ch.push_back(cc % 2);
				cc = cc >> 1;
			}	
//			for (int i = 0; i < (n - 2) / 2; i++) ch.push_back(0);
			ch.push_back(1);
			for (int i = 2; i < 11; i++) {
				long long tt = 0;
				long long st = 1;
				for (int j = 0; j < ch.size(); j++) {
					tt += st * ch[j];
					st *= i;
				}
				cout << tt << " " << endl;
				long long p = isPrime(tt);
				if (p != -1) res.push_back(p);
			}
			cerr << cur << " " << res.size() << endl;
			if (res.size() == 9) {
				for (int i = ch.size() - 1; i >= 0; i--) cout << ch[i];
				for (long long x: res) cout << " " << x;
				cout << endl;
				nn++;
			}
		}

	}


	return 0;
}