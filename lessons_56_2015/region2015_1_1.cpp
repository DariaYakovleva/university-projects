#include <bits/stdc++.h>

using namespace std;

int main() {
	int a, b, c, d;
	cin >> a >> b >> c >> d;
	long long res = 0;
	for (int x = 1; x <= 100000; x++) {

		int miny1 = a / x - 10;
		while (x * miny1 < a) miny1++;
		int maxy1 = b / x + 10;
		while (x * maxy1 > b) maxy1--;
		int miny2 = c / 2 - x - 10;		
		while (2 * x + 2 * miny2 < c) miny2++;
		int maxy2 = d / 2 - x + 10;		
		while (2 * x + 2 * maxy2 > d) maxy2--;
		int y1 = max(miny1, miny2);
		int y2 = min(maxy1, maxy2);
		if (y2 < y1) continue;
		if (y1 < x) y1 = x;
		if (y2 < x) continue;
		res += (long long)(y2 - y1 + 1);
	}
	cout << res << endl;
}