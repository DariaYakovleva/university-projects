#include <bits/stdc++.h>

using namespace std;

const int N = 100010;

int n, m;
int arr[N];	

bool bs(int x) {
	int l = -1;
	int r = n;
	while (r - l > 1) {
		int m = (l + r) / 2;
		if (x > arr[m]) {
			l = m;
		} else {
			r = m;
		}
	}
	if (r != n && arr[r] == x) return true;
	return false;
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < m; i++) {
		int x;
		cin >> x;
		if (bs(x)) {
			cout << "YES" << endl;
		} else {
			cout << "NO" << endl;
		}
	}
	return 0;
}