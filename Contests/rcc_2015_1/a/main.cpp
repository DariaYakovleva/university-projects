#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
#include <string>

using namespace std;

int grisha[110];
int dima[110];

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		int n, l;
		cin >> n >> l;
		for (int k = 0; k < n; k++) {
			cin >> grisha[k];
		}
		for (int k = 0; k < n; k++) {
			cin >> dima[k];
		}
		sort(grisha, grisha + n);
		sort(dima, dima + n);
		int sum1 = 0;
		int sum2 = 0;
		for (int k = 0; k < l; k++) {
			sum1 += grisha[k];
			sum2 += dima[n - k - 1];
		}
		if (sum1 > sum2) {
			cout << "YES" << endl;
		} else {
			cout << "NO" << endl;
		}

	}
	return 0;
}