#include <bits/stdc++.h>

using namespace std;

int a, b, n;
double gr = 1000.0;
int cnt = 0;
double const EPS = 1e-15;

void cmp(int c, int d, int add) {
	double cur = fabs((double)c / d - (double)a / b);
	if (cur < gr) {
		cnt += add;
		gr = cur;
	}
}

int main() {
	freopen("approximate.in", "r", stdin);
	freopen("approximate.out", "w", stdout);
	cin >> a >> b >> n;	
	for (int d = 1; d <= n; d++) {
		int c = a * d / b;
		if (2 * b * c == 2 * a * d - b) {
			cmp(c, d, 2);
		} else {
			if (2 * b * c < 2 * a * d - b) c++;
			cmp(c, d, 1);
		}
//		cout << c << " " << d <<  ";" << gc << " " << gd << endl;
	}
	cout << cnt << endl;
	return 0;
}