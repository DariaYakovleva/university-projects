#include <bits/stdc++.h>

using namespace std;

struct point {
	long long x, y;
};

long long res = -1;
point p1;
point p2;
point arr[3010];

long long dist(point a, point b) {
	return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
}

int main() {
	int n;
	cin >> n >> p1.x >> p1.y >> p2.x >> p2.y;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].x >> arr[i].y;
	}
	for (int i = 0; i < n; i++) {
		long long r1 = dist(p1, arr[i]);		                         	
		long long r2 = 0;
		for (int j = 0; j < n; j++) {
			if (dist(p1, arr[j]) > r1) {
				r2 = max(r2, dist(p2, arr[j]));
			}			
		}
		if (res == -1) res = r1 + r2;
		res = min(res, r1 + r2);

		r1 = dist(p2, arr[i]);		                         	
		r2 = 0;
		for (int j = 0; j < n; j++) {
			if (dist(p2, arr[j]) > r1) {
				r2 = max(r2, dist(p1, arr[j]));
			}			
		}
		if (res == -1) res = r1 + r2;
		res = min(res, r1 + r2);
	}
	cout << res << endl;
		
	return 0;
}