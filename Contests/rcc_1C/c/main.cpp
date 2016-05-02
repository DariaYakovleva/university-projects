#include <bits/stdc++.h>

using namespace std;

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int x = 0; x < test; x++) {
		int a1, b1, a2, b2, z;
		cin >> a1 >> b1 >> a2 >> b2 >> z;
		int h1 = 0;
		int h2 = 0;
		int d = 0;
		double cnt = 0.0;
		for (int i = 0; i < z; i += 12) {
			int tt = min(z - i, 12);
			d ^= 1;
			int s = h1 - h2;
			int v = 0;
			if (d) {
				h1 += a1 * tt;
				h2 += a2 * tt;
				v = a2 - a1;
			} else {
				h1 -= b1 * tt;
				h2 -= b2 * tt;
				v = b2 - b1;
			}
			if (v == 0) {
				if (s > 0) {
					cnt += tt;
				}
			} else {
				double t = s / v;
				if (t >= (double)tt) {
					if (s > 0) {
						cnt += tt;
					}
				} else if (t < 0.00000001) {
					if (h1 > h2) cnt += tt;
				} else {
					if (s < 0) {
						cnt += tt - t;
					} else {
						cnt += t;
					}				
				}
			}
			printf("t = %d %d %.7lf\n", h1, h2, cnt);
		}	
		printf("%.7lf\n", cnt);
//		cout << cnt << endl;
	}
}

