#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
#include <string>

using namespace std;

int arr[110];
int d, k, n;

int gett(int x, int a) {
	int pos = (x + n) % d;
	if (a % 2 == 1) {
		pos = (x - n + d);
		while (pos < 0) pos += d;
		pos %= d;
	}
//	cout << x << " " << n << " " << pos << endl;
	return pos; 
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> d >> k >> n;
		k--;
		cout << "Case #" << i + 1 << ": "; 
		int pos = gett(k, k);
//		cout << "p=" << pos << endl;
		int posl = gett((pos + 1) % d, k);
		int posr = gett((pos - 1 + d) % d, k);
		cout << posl + 1 << " " << posr + 1 << endl;
	}

	return 0;
}
