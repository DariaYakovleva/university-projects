#include <bits/stdc++.h>

using namespace std;

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		int n;
		cin >> n;
		vector< pair<int, int> > hi;
		for (int i = 0; i < n; i++) {
			int a, b, c;
			cin >> a >> b >> c;
			for (int i = 0; i < b; i++) {
				hi.push_back(make_pair(a, c + i));
			}
			int res = 1;
			cout << "Case #" << t + 1 << ": " << res << endl;

		}
	}

}

