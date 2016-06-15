#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main() {
	int t;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++) {
		long long a, b, c;
		scanf("%lld %lld %lld", &a, &b, &c);
		long long cnt = (a * b) / (c * c);
		long long res = max(c * c, cnt * c * c);
		long long res1 = res + c * c;
		if (abs(a * b - res1) < abs(a * b - res)) res = res1;
		printf("%lld\n", res);
	}


	return 0;
}