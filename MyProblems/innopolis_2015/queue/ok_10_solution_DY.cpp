#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
const int N = 1010;
int a[N];
int n, m;
int res = 0;

bool check() {
	int bal = 0;
	int cnt_n = 0;
	int cnt_m = 0;
	for (int i = 0; i < n + m; i++) {
		if (a[i] == 0) {
			bal++;
			cnt_n++;
		} else {
			bal -= 9;
			cnt_m++;
		}
		if (bal < 0) return false;
	}
	if (cnt_n != n) return false;
	if (cnt_m != m) return false;
	return true;
}

void go(int pos) {
	if (pos == n + m) {
		if (check()) res++;
		return;	
	}
	a[pos] = 0;
	go(pos + 1);
	a[pos] = 1;
	go(pos + 1);
}

int main() {
//	freopen("ticket-office.in", "r", stdin);
//	freopen("ticket-office.out", "w", stdout);
	cin >> n >> m;
	go(0);
	cout << res << endl;
	
}
