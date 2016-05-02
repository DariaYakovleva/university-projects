#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
const int N = 1010;
int a[N];
int n, m;
int res = 0;

int go() {
	if (m == 0) {
		return 1;	
	}
	if (m == 1) {
		return max(0, n - 8);
	}
	int cnt = 0;
	for (int i = 0; i < n + m; i++) {
		for (int j = i + 1; j < n + m; j++) {
			if (i >= 9 && (j - 1 >= 18)) {
				cnt++;
			}
		}
	}
	return cnt;
}

int main() {
//	freopen("ticket-office.in", "r", stdin);
//	freopen("ticket-office.out", "w", stdout);
	cin >> n >> m;
	cout << go() << endl;
	
}
