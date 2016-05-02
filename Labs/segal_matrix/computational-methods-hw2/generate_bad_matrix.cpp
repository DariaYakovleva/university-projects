#include <bits/stdc++.h>

using namespace std;

int main() {
	int n = 5;
	cout.precision(6);
	cout << n << endl;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cout << (double)1 / (i + j) << " ";
		}
		cout << 0 << endl;
	}

	return 0;
}