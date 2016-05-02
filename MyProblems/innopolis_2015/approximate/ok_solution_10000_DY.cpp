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
//		cout << c << " "  << d << " " << add << endl;
	}
}

int fun(long long c, long long c1, long long d) {
	if (abs(a * d - b * c) < abs(a * d - c1 * b)) return -1;
	if (abs(a * d - b * c) == abs(a * d - c1 * b)) return 0;
	return 1;
}

pair<long long, long long> get_c(long long d) {
	long long l = -1;
	long long r = n + 10;
	while (r - l > 1) {
		long long m1 = l + (r - l + 1) / 3;
		long long m2 = r - (r - l + 1) / 3;
		if (fun(m1, m2, d) < 0) {
			r = m2;
		} else {
			l = m1;
		}
	}
	pair<long long, long long> res;
	res.first = l;
	res.second = -1;
	if (fun(l, r, d) == 0) res.second = r;
	if (fun(l, l - 1, d) == 0) res.second = l - 1;
	return res;
}

int main() {
	freopen("approximate.in", "r", stdin);
	freopen("approximate.out", "w", stdout);
	cin >> a >> b >> n;	
	for (long long d = 1; d <= n; d++) {
		pair<long long, long long> cc = get_c(d);
		if (cc.second != -1) cmp(cc.second, d, 2);
		else cmp(cc.first, d, 1);
	}
	cout << cnt << endl;
	return 0;
}