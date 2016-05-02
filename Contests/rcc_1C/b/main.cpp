#include <bits/stdc++.h>

using namespace std;


int sq(long long a) {
	int res = 0;
	while (a != 0) {
		res += a % 10;
		a /= 10;
	}
	if (res > 9) return sq(res);
	return res;
}

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		long long a, b;
		cin >> a >> b;
		long long res = (b - a + 1) % 9;
		if (res == 0) res = 9;
		cout << res << " " ;
		vector<int> ans;
		for (int i = 0; i < res; i++) {
			ans.push_back(sq(a + i));
		}
		sort(ans.begin(), ans.end());
		for (auto x : ans) {
			cout << x << " ";
		}
		cout << endl;
	}
}

