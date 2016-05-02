#include <bits/stdc++.h>

using namespace std;
const int N = 10010;

int main() {
	unsigned long long l, r, k;
	cin >> l >> r >> k;
	unsigned long long x = 1;
	bool have = false;
	while (x < l) x *= k;
	while (x >= l && x <= r) {
		have = true;
		cout << x << " ";
		x *= k;
		if (x < 0) break;
	}	
	if (!have) cout << "-1" << endl;

	return 0;
}