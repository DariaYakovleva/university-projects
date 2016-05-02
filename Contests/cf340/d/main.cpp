#include <bits/stdc++.h>

using namespace std;

struct point {
	long long x, y;
};

bool cmp1(point a, point b) {
	return (a.x < b.x) || (a.x == b.x && a.y < b.y);
}

bool cmp2(point a, point b) {
	return (a.y < b.y) || (a.y == b.y && a.x < b.x);
}


point a[3];


int main() {
	cin >> a[0].x >> a[0].y >> a[1].x >> a[1].y >> a[2].x >> a[2].y;
	if ((a[0].x == a[1].x && a[1].x == a[2].x) || (a[0].y == a[1].y && a[2].y == a[1].y)) {
		cout << 1 << endl;
		return 0;
	}
	point a1[3];
	point a2[3];
	for (int i = 0; i < 3; i++) {
		a1[i] = a[i];
		a2[i] = a[i];
	}
	sort(a1, a1 + 3, cmp1);
	sort(a2, a2 + 3, cmp2);

	if ((a1[0].y == a1[1].y) || (a1[1].y == a1[2].y) || (a2[0].x == a2[1].x) || (a2[1].x == a2[2].x)) {
		cout << 2 << endl;
		return 0;
	}
	if (a1[0].x == a1[1].x) swap(a1[0], a1[1]);
	if (a1[1].x == a1[2].x) swap(a1[1], a1[2]);
	if (a2[0].y == a2[1].y) swap(a2[0], a2[1]);
	if (a2[1].y == a2[2].y) swap(a2[1], a2[2]);
	if ((a1[0].y == a1[1].y) || (a1[1].y == a1[2].y) || (a2[0].x == a2[1].x) || (a2[1].x == a2[2].x)) {
		cout << 2 << endl;
		return 0;
	}

	cout << 3 << endl;

		
	return 0;
}