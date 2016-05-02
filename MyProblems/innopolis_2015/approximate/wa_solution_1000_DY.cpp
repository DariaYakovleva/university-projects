#include <bits/stdc++.h>

using namespace std;

int a, b, n;
int gc = -1; 
int gd = -1;
int cnt = 0;

void cmp(int c, int d, int add) {
	int cur = gd * abs(a * d - c * b);
	int prev = d * abs(a * gd - gc * b);
	if (cur < prev || gc == -1) {
		cnt += add;
		gc = c;
		gd = d;
	}
}

int curc;
int curd;
int curcnt = 0;

void cmp_cur(int c) {
	int cur = abs(a * curd - c * b);
	int prev = abs(a * curd - curc * b);
	if (cur < prev) {
		curcnt = 1;
		curc = c;
	} else if (cur == prev) curcnt++;
}

int main() {
	freopen("approximate.in", "r", stdin);
	freopen("approximate.out", "w", stdout);
	cin >> a >> b >> n;	
	for (int d = 1; d <= n; d++) {
	    curd = d;
	    curc = 0;
	    curcnt = 0;
		for (int c = 0; c <= n; c++) {
			cmp_cur(c);
		}
		cmp(curc, curd, curcnt);
	}
	cout << cnt << endl;
	return 0;
}