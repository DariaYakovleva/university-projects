#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 100010;
int n, a1, a2, a3, b1, b2, b3;
int main() {
	int sum1 = 0, sum2 = 0;
	cin >> a1 >> a2 >> a3;
	sum1 = a1 + a2 + a3;
	cin >> b1 >> b2 >> b3;
	sum2 = b1 + b2 + b3;
	cin >> n;
	if ((sum1 / 5 + (sum1 % 5 != 0?1:0) + sum2 / 10 + (sum2 % 10 != 0?1:0)) > n) {
		cout << "NO" << endl;
	} else {
		cout << "YES" << endl;
	}


	return 0;
}