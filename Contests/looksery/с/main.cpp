#include <bits/stdc++.h>

using namespace std;

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int n, m;
	cin >> n >> m;
	int a = 0;
	int b = 0;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		if (x % 2 == 0) {
			a++;
		} else {
			b++;
		}
	}
	int p = n - m;
	int p2 = (p + 1) / 2;
	if (p % 2 == 0 && b % 2 == 0) {
		if ((b % 2 == 1 && b <= p2) || (a % 2 == 1 && a <= p2)) {
			cout << "Stannis" << endl;
			return 0;
		} else {
			cout << "Daenerys" << endl;
		}
		return 0;
	}
	if (p % 2 == 1 && b % 2 == 0) {
		if ((b % 2 == 1 && b <= p2) || (a % 2 == 0 && a <= p2)) {
			cout << "Stannis" <<endl;
			return 0;
		} else {
			cout << "Daenerys" << endl;
		}	
		return 0;
	}
	if (p % 2 == 0 && b % 2 == 1) {
		if ((b % 2 == 0 && b <= p2) || (a % 2 == 0 && a <= p2)) {
			cout << "Stannis" << endl;
			return 0;
		} else {
			cout << "Daenerys" << endl;
		}

		return 0;
	}
	if (p % 2 == 1 && b % 2 == 1) {
		if ((b % 2 == 0 && b <= p2) || (a % 2 == 1 && a <= p2)) {
			cout << "Stannis" << endl;
			return 0;
		} else {
			cout << "Daenerys" << endl;
		}

		return 0;
	}


}