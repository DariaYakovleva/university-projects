#include <bits/stdc++.h>

using namespace std;
const int N = 100010;

struct point {
	long long  x, y;
	point() {}
	point(long long a, long long b) {
		x = a; y = b;
	}
};

point p;
point arr[N];
double r_min = -1.0;
double r_max = 0.0;

long long vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

long long scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

double dist(point a, point b) {
    return sqrt((double)(b.x - a.x) * (b.x - a.x) + (double)(b.y - a.y) * (b.y - a.y));
}

double pointToLine(point a, point b, point c) {
    double v = abs(vect(point(a.x - b.x, a.y - b.y), point(c.x - b.x, c.y - b.y)));
    return (double)v / dist(b, c);
}

double pointToSegment(point a, point b, point c) {
    if (scalar(point(a.x - b.x, a.y - b.y), point(c.x - b.x, c.y - b.y)) < 0) {
        return dist(a, b);
    }
    if (scalar(point(a.x - c.x, a.y - c.y), point(b.x - c.x, b.y - c.y)) < 0) {
        return dist(a, c);
    }
    return pointToLine(a, b, c);
}

int main() {
	int n;
	cin >> n >> p.x >> p.y;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].x >> arr[i].y;
//		long long r = dist(arr[i], p);
//		if (r > r_max) r_max = r;
//		if (r < r_min || r_min == -1) {
//			r_min = r;
//		}
	}
	for (int i = 0; i < n; i++) {
		double r1 = max(pointToSegment(p, arr[i], arr[(i + 1) % n]), dist(p, arr[i]));
		double r2 = min(pointToSegment(p, arr[i], arr[(i + 1) % n]), dist(p, arr[i]));
		if (r1 > r_max) r_max = r1;
		if (r2 < r_min || r_min < 0) {
			r_min = r2;
		}
	}
	double res = 3.141592653 * (r_max - r_min) * (r_max + r_min);
	printf("%.8lf\n", res);


	return 0;
}