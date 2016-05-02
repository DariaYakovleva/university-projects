#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cmath>
#include <vector>
#include <set>

using namespace std;

int arr[1100];

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		int n;
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> arr[i];
		}
		int m1 = 0;
		int m2 = 0;
		int maxd = 0;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i + 1] < arr[i]) {
				m1 += arr[i] - arr[i + 1];
				maxd = max(maxd, arr[i] - arr[i + 1]);
			}
		}
		for (int i = 0; i < n - 1; i++) {
			m2 += min(maxd, arr[i]);
		}
		cout << "Case #" << t + 1 << ": " << m1 << " " << m2 << endl;
	}
	return 0;
}