#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>
#include <set>


using namespace std;
const int N = 100010;
pair<int, int> arr[N];
int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int m, n;
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		cin >> arr[i].first >> arr[i].second;
	}
	int h = 0;
	for (int i = 0; i < m; i++) {
		h = max(h, arr[i].second);
		if (i == 0) {
			h = max(h, arr[0].second + arr[0].first - 1);		
		} else {
			int hi = arr[i - 1].second - arr[i].second;
			if (hi < 0) hi = -hi;
			int ost = arr[i].first - arr[i - 1].first - hi;
			if (ost < 0) {
				cout << "IMPOSSIBLE" << endl;
				return 0;
			}
			int mh = max(arr[i - 1].second, arr[i].second) + ost / 2;
			h = max(h, mh);
		}
	}
	h = max(h, n - arr[m - 1].first + arr[m - 1].second);
	cout << h << endl;

	return 0;
}