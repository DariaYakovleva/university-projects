#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

const int N = 200010;

int n;
int t;
double c;
double arr[N];
double sum[N];
double mean[N];
int m;
int p;


void init() {                               	
	sum[0] = 0.0;
	for (int i = 1; i <= n; i++) {
		sum[i] = sum[i - 1] + arr[i - 1];
	}
	mean[0] = arr[0] / t / c;
	for (int i = 1; i < n; i++) {
		mean[i] = (mean[i - 1] + arr[i] / t) / c;
	}
}                 

double real(int p) {
	return (sum[p + 1] - sum[p - t + 1]) / t;
}

double approx(int p) {
	return mean[p];
}

double error(double r, double ap) {
	return fabs(ap - r) / r;
}

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	scanf("%d %d %lf", &n, &t, &c);
	for (int i = 0; i < n; i++) {
		scanf("%lf", &arr[i]);
	}
	init();
	scanf("%d", &m);
	for (int i = 0; i < m; i++) {
		scanf("%d", &p);
		p--;
		double r = real(p);
		double ap = approx(p);
		double er = error(r, ap);
		printf("%.7lf %.7lf %.7lf\n", r, ap, er);
	}	

}
