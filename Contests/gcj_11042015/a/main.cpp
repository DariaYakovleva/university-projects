#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) { 
		int n;
		string s;
		cin >> n;
		cin >> s;
		int ans = 0;
		int cur = 0;
		for (int i = 0; i <= n; i++) {
			int p = s[i] - '0';
			if (cur < i) {
				int d = i - cur;
				ans += d;
				cur += d;
			}
			cur += p;
		} 
		cout << "Case #" << t + 1 << ": " << ans << endl;
	}

	return 0;
}