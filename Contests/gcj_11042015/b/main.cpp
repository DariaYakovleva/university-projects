#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 2010;
int n;
int arr[N]; 

int solve(int tt) {
	int addT = 0;
	for (int i = 0; i < n; i++) {
		if (arr[i] > tt) {
			addT += (arr[i] - 1) / tt;
		}
	}
	return tt + addT;
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) { 
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> arr[i];
		}
		sort(arr, arr + n);
		int minTime = 10000000;
		for (int tt = 1; tt < 1010; tt++) {
				minTime = min(minTime, solve(tt));
		}
		cout << "Case #" << t + 1 << ": " << minTime << endl;
	}

	return 0;
}