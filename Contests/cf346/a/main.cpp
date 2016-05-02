#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

int main() {
	int n, a, b;
	cin >> n >> a >> b;	
	int res = a + b - 1;
	while (res < 0) res += n;
	cout << res % n + 1 << endl;

	return 0;
}