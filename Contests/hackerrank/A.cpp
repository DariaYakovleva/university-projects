#include <bits/stdc++.h>

using namespace std;

const int N = 1100;

int keys[N];
int mouses[N];
int main() {
	int n, m, s;
	cin >> s >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> keys[i];
	}
	for (int i = 0; i < m; i++) {
		cin >> mouses[i];
	}
	int res = -1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (keys[i] + mouses[j] <= s && keys[i] + mouses[j] > res) {
				res = keys[i] + mouses[j];
			}
		}
	}
	cout << res << endl;

	return 0;
}