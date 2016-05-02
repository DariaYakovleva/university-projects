#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>
#include <set>
#include <math.h>

using namespace std;

int main() {
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		int a, b, k;
		cin >> a >> b >> k;
		int aa = a;
		int bb = b;
		if (a > 99 && b > 99) {
			if (k == 1) {
				cout << 0 << endl;
				continue;	
			}
			int tmp = min(a - 99, b - 99);
			a -= tmp;
			b -= tmp;
		}
		while (a > 0 && b > 0) {
			if (min(a, 99) * k == min(b, 99) || min(b, 99) * k == min(a, 99)) {
				break;
			}
			a--;
			b--;	
		}
		if (a == 0 || b == 0) {
			cout << -1 << endl;
		} else {
			cout << aa - a << endl;
		}


	}

	return 0;

}