#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;
const int N = 300010;
long long n, arr[N], sum[N];

long long fun(int l, int r)
{
	if (l + 1 == r) {
		return arr[l];
	}
	return sum[r] - sum[l] + fun(l, l + 1) + fun(l + 1, r);
}

int main() {
	cin >> n;
	sum[0] = 0;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	sort(arr, arr + n);
	for (int i = 0; i < n; i++) 
		sum[i + 1] = sum[i] + arr[i];

	cout << fun(0, n) << endl;
	
	return 0;
}