#include <iostream>
#include <cstdio>
#include <vector>
#include <math.h>
#include <algorithm>
#include <string>

using namespace std;

const int N = 2000;
int n;
long long arr[2][N];


long long dp(int col, int pos) {
	if (arr[col][pos] != -1) return arr[col][pos];
	if (pos == 0 || pos == 1) {
		arr[col][pos] = 1;
		return arr[col][pos];
	}
	arr[col][pos] = 0;
	for (int i = 1; i <= pos; i += 2) {
		arr[col][pos] += dp((col + 1) % 2, pos - i);
	}
	return arr[col][pos];
}

int main() {
	cin >> n;
	for (int i = 0; i <= n; i++) {
		arr[0][i] = -1; arr[1][i] = -1;
	}
	cout << dp(0, n) + dp(1, n) << endl;

	return 0;
}