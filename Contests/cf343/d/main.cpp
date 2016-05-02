#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>


using namespace std;
const int N = 100010;
const long long M = 100000000000000007;

long long arr[N];
vector<long long> d;
double res[N];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		long long r, h;
		cin >> r >> h;
		arr[n - i - 1] = -r * r * h;		
	}
	d.push_back(-M);
	res[0] = 0.0;
	for (int i = 1; i <= n; i++) {
		d.push_back(M);
		res[i] = 0.0;
	}
	double ress = 0;
	for (int i = 0; i < n; i++) {
		int k = (int)(upper_bound(d.begin(), d.end(), arr[i]) - d.begin());
		for (int j = max(1, k - 2); j <= min(n, k + 2); j++) {
			if (arr[i] > d[j - 1] && arr[i] < d[j]) {
				d[j] = arr[i];
				res[j] = res[j - 1] + (double)abs(arr[i]);
				ress = max(res[j], ress);
			}
		}
	}
//	for (int i = 0; i < d.size(); i++) {
//		cout << d[i] << " " ;
//	}
	printf("%.14lf", ress * 3.14159265359);

	
	return 0;
}