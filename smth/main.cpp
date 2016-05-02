#include <bits/stdc++.h>

using namespace std;

int sum(int a) {
	int s = 0;
	while (a > 0) {
		s += a % 10;
		a /= 10;
	}
	return s;
}

int main() {
	int res = 0;
	for (int i = 0; i < 10000000; i++) {
		int a = sum(i % 1000);
		int b = sum(i / 1000);
		if (a == b) {
			if (i < 3000)
			cout << i << endl;
			res++;
		}

	}
	cout << res << endl;
}