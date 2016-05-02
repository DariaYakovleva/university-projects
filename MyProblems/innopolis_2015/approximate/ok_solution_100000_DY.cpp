#include <bits/stdc++.h>

using namespace std;

long long a, b, n;
long long gc = -1; 
long long gd = -1;
int cnt = 0;

void cmp(long long c, long long d, long long add) {
	long long cur = gd * abs(a * d - c * b);
	long long prev = d * abs(a * gd - gc * b);
	if (cur < prev || gc == -1) {
		cnt += add;
		gc = c;
		gd = d;
	}
}

int main() {
	freopen("approximate.in", "r", stdin);
	freopen("approximate.out", "w", stdout);
	cin >> a >> b >> n;	
	for (long long d = 1; d <= n; d++) {
		long long c = a * d / b;
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