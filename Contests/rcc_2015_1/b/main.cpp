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
	string s;
	getline(cin, s);
	for (int i = 0; i < t; i++) {
		string x, y, z;
		getline(cin, x);
		getline(cin, y);
		getline(cin, z);
		reverse(x.begin(), x.end());
		reverse(y.begin(), y.end());
		reverse(z.begin(), z.end());
		int n = (int)x.size();
		int m = (int)y.size();
		int len = (int)z.size();
		bool good = true;
		for (int k = 0; k < len; k++) {
			int res = 0;
			for (int j = 0; j <= k; j++) {
				int a = (j < n)?(x[j] - '0'):0;
				int b = (k - j < m)?(y[k - j] - '0'):0;
				res += a * b;
			}
			if (res != (z[k] - '0')) {
				good = false;
				break;
			}
		}
		if (good) {
			cout << "Infinity" << endl;
		} else {
			cout << "Finite" << endl;
		}
	}
	return 0;
}