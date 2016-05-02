#include <bits/stdc++.h>

using namespace std;

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		int r, c, n;
		cin >> r >> c >> n;
		if (r == 1 || c == 1) {
			int fr = (r * c + 1) / 2;
			int res = 0;
			n -= min(n, fr);
			if ((r * c) % 2 == 0) {
				res += min(1, n) * 1;
				n -= min(1, n);
			}
			res += n * 2;
			cout << "Case #" << t + 1 << ": " << res << endl;
			continue;
		}
		int res = 0;
		int fr = (r * c + 1) / 2;
		if (n <= fr) {
			cout << "Case #" << t + 1 << ": " << 0 << endl;
		} else {
			int m = n;
			int res2 = 0;
			if ((r * c) % 2 == 1) {
				int fr2 = (r * c) / 2;
				m -= fr2;
				res2 = min(4, m) * 2;
				m -= min(4, m);
				res2 += min(((r + 1) / 2 + (c + 1) / 2) * 2 - 8, m) * 3;
				m -= min(((r + 1) / 2 + (c + 1) / 2) * 2 - 8, m);
				res2 += m * 4;
			} else {
				res2 = 10000010;
			}
			n -= fr;
			int ug;
			int w;
			if ((r * c) % 2 == 0) {
				ug = 2;
				if (r % 2 == 1) {
					w = (c / 2) * 2 + r - 4;
				} else {
					w = (r / 2) * 2 + c - 4;
				}
			} else {
				ug = 0;
				w = (r / 2 + c / 2) * 2;
			}
			res = min(ug, n) * 2;
			n -= min(ug, n);
			res += min(w, n) * 3;
			n -= min(w, n);
			res += n * 4;
			cout << "Case #" << t + 1 << ": " << min(res, res2) << endl;
		}
	}

}

