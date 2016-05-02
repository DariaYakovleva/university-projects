#include <bits/stdc++.h>

using namespace std;

int main() {
	int n, t;
	cin >> n >> t;
	int res = 1;
	if (t == 10 && n < 2) {
		cout << -1 << endl;
		return 0;
	}
	cout << t;
	if (t == 10) n--;
	for (int i = 1; i < n; i++) {
		cout << 0;
	}
	cout << endl;
	              	

	return 0;
}