#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
#include <vector>
#include <set>

using namespace std;
int arr[100010];

int ans = 0;

int go(int v) {
	if (arr[v] == -1) {
		return 0;
	}
	if (arr[2 * v] == -1) {
		return 0;
	}
	int left = go(2 * v + 1) + arr[2 * v];
	int right = go(2 * v + 2) + arr[2 * v + 1];
	int d = abs(left - right);
	ans += d;
	return max(left, right);
}

int main() {
	int n;
	for (int i = 0; i < 100010; i++) {
		arr[i] = -1;
	}
	cin >> n;
	for (int i = 0; i < (1 << (n + 1)) - 2; i++) {
		cin >> arr[i];
	}
	go(0);
	cout << ans << endl;



	return 0;
}