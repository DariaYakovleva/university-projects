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
long long res[N];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		long long r, h;
		cin >> r >> h;
		arr[n - i - 1] = -r * r * h;		
	}
	d.push_back(-M);
	res[0] = 0;
	for (int i = 1; i <= n; i++) {
		d.push_back(M);
		res[i] = 0;
	}
	long long ress = 0;
	for (int i = 0; i < n; i++) {
		int k = (int) (lower_bound(d.begin(), d.end(), arr[i]) - d.begin());
		int j = k + 1;
		if (d[j] == arr[i]) continue;
		d[j] = arr[i];
		res[j] = res[j - 1] - arr[i];
		ress = max(res[j], ress);
	}
//	for (int i = 0; i < d.size(); i++) {
//		cout << d[i] << " " ;
//	}
	printf("%.14lf", ress * acos(-1.));

	
	return 0;
}