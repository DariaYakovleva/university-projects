#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

const int N = 100010;
int n, d, sum = 0, k = 0;

int main() {
    cin >> n >> d;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		sum += a;
	}
	sum += 10 * (n - 1);
	if (sum > d) cout << "-1" << endl; 
	else cout << 2 * (n - 1) + (d - sum) / 5 << endl;

	return 0;
}