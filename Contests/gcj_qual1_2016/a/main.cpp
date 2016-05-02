#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <algorithm>

using namespace std;

int used[10];
void init() {
	for (int i = 0; i < 10; i++) used[i] = false;
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int tests;
	cin >> tests;
	for (int tt = 0; tt < tests; tt++) {
		init();
		int n;
		cin >> n;
		if (n == 0) {
			cout << "Case #" << (tt + 1) << ": " << "INSOMNIA" << endl;
			continue;
		}
		int cnt = 0;
		int nn = n;
		while (cnt < 10) {
			int cur = nn;
			while (cur > 0) {
				if (!used[cur % 10]) {
					used[cur % 10] = true;
					cnt++;
				}
				cur /= 10;
			}
			nn += n;
		}
		cout << "Case #" << (tt + 1) << ": " << (nn - n) << endl;
		cerr << "Case #" << (tt + 1) << ": " << (nn - n) << endl;
	}


	return 0;
}