#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
long long n, m, k, res = 0;

int main() {
    cin >> n >> m >> k;
	if ((n - 1) + (m - 1) < k) {
		cout << "-1" << endl;
		return 0;
	}
	for (long long i = 1; i * i <= n; i++) { 
		if (i - 1 <= k) {
			long long n1 = n / i, m1 = m / (k - (i - 1) + 1);
			res = max(res, n1 * m1);		
		}
		if ((n / i - 1) <= k) {
			long long n1 = n / (n / i), m1 = m / (k - ((n / i) - 1) + 1);
			res = max(res, n1 * m1);		
		}
	}
	for (long long i = 1; i * i <= m; i++) { 
		if (i - 1 <= k) {
			long long m1 = m / i, n1 = n / (k - (i - 1) + 1);
	   	 //cout << i + 1 << " " << k - i + 1 << " " << n1 << " " << m1 << endl;
			res = max(res, n1 * m1);
		}
		if (m / i - 1 <= k) {
			long long m1 = m / (m / i), n1 = n / (k - ((m / i) - 1) + 1);
	   	 //cout << i + 1 << " " << k - i + 1 << " " << n1 << " " << m1 << endl;
			res = max(res, n1 * m1);
		}
	}
	cout << res << endl;

    


	return 0;
}