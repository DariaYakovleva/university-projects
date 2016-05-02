#include <bits/stdc++.h>

using namespace std;

long long xa, ya, xb, yb;

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	cin >> xa >> ya >> xb >> yb;
	if (ya * yb >= 0) {
		ya = -ya;
	}
	long long res = (xa - xb) * (xa - xb) + (ya - yb) * (ya - yb);
	cout << res;
	cout << ".";
	for (int i = 0; i < 20; i++) {
		cout << 0;
	}

	return 0;
}