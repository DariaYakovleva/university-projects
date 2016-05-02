#include <bits/stdc++.h>

using namespace std;

int main() {
	int a, b, c, d, e, f;
	cin >> a >> b >> c >> d >> e >> f;
	int res = 0;
	int x = 2 * a + 1;
	for (int i = 0; i < min(b, f); i++) {
		res += x;
		x += 2;
	}
	x -= 1;
	for (int i = 0; i < max(b, f) - min(b, f); i++) {
		res += x;
	}
	x = 2 * d + 1;
	for (int i = 0; i < min(c, e); i++) {
		res += x;
		x += 2;
	}
	cout << res << endl;
	return 0;
}
