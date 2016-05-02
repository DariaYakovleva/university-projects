#include <bits/stdc++.h>

using namespace std;
const int N = 110;
double arr[N][2];
double eps = 0.000000001;
int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		int n;
		double v, x;
		scanf("%d%lf%lf", &n, &v, &x);
		for (int i = 0; i < n; i++) {
			scanf("%lf %lf", &arr[i][0], &arr[i][1]);
		}
		if (n == 1) {                                         	
			if (fabs(x - arr[0][1]) < eps) {
				printf("Case #%d: %.9lf\n", t + 1, v / arr[0][0]);	
			} else {
				printf("Case #%d: IMPOSSIBLE\n", t + 1);	
			}
		} else {
			if (fabs(arr[0][1] - arr[1][1]) < eps) {
				if (fabs(x - arr[0][1]) < eps) {
					printf("Case #%d: %.9lf\n", t + 1, v / (arr[0][1] + arr[0][0]));								 
				} else {
					printf("Case #%d: IMPOSSIBLE\n", t + 1);
				}
			} else {
				double v1 = v * (x - arr[0][1]) / (arr[1][1] - arr[0][1]);
				if (v1 < eps) {
					printf("Case #%d: IMPOSSIBLE\n", t + 1);			
				} else {
					printf("Case #%d: %.9lf\n", t + 1, max(v1 / arr[1][0], (v - v1) / arr[0][0]));					
				}
			}
		}
	}

	return 0;
}	
