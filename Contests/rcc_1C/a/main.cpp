#include <bits/stdc++.h>

using namespace std;

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		int a, b, c;
		cin >> a >> b >> c;
		c -= min(c / 2, b) * 2;
		if (c <= a) {
			cout << "YES" << endl;
		} else {
			cout << "NO" << endl;
		}
	}
}

