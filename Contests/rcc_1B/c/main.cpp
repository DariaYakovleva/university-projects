#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>
#include <set>
#include <math.h>

using namespace std;

int n;
const int N = 1000010;
int arr[N];


bool solve(double x) {
	double ost = (double)x;
	for (int i = 0; i < n; i++) {
		if (ost + (double)arr[i] < 2 * x) {
			if ((double)arr[i] < ost) {
				ost = min(ost - (double)arr[i], 2 * x - (ost + (double)arr[i]));
			} else {
				ost = 0.0;
			}
		} else {
			return false;
		}
	}
	return true;
}

double bs() {
	double l = 0.0;
	double r = 10000.0;
	for (int i = 0;  i < 500; i++) {
		double m = (l + r) / 2;
		if (solve(m)) {
			r = m;
		} else {
			l = m;
		}
	}
	return r;
}

int main() {
	int test;
	scanf("%d", &test);
	for (int t = 0; t < test; t++) {
		scanf("%d", &n);
		for (int i = 0; i < n; i++) {
			scanf("%d", &arr[i]);
		}
		printf("%.9lf\n", bs());
	}

	return 0;
	
}