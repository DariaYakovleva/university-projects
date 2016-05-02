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

long long curc;
long long curd;
int curcnt = 0;

void cmp_cur(long long c) {
	long long cur = abs(a * curd - c * b);
	long long prev = abs(a * curd - curc * b);
	if (cur < prev) {
		curcnt = 1;
		curc = c;
	} else if (cur == prev) curcnt++;
}

int main() {
	freopen("approximate.in", "r", stdin);
	freopen("approximate.out", "w", stdout);
	cin >> a >> b >> n;	
	for (long long d = 1; d <= n; d++) {
	    curd = d;
	    curc = 0;
	    curcnt = 0;
		for (long long c = 0; c <= n; c++) {
			cmp_cur(c);
		}
		cmp(curc, curd, curcnt);
	}
	cout << cnt << endl;
	return 0;
}