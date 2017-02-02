#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
long long n;
int m;
long long arr[N];
long long cnt[N];

int main() {
	int q;
	cin >> q;
	for (int k = 0; k < q; k++) {
		cin >> n;
		cin >> m;
		for (int i = 0; i < m; i++) {
			cin >> arr[i];
			cnt[i] = 0;
		}
		sort(arr, arr + m);
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] % arr[j] == 0) {
					cnt[i] = max(cnt[i], (arr[i] / arr[j]) * cnt[j] + 1);
				}
			}
		}
		long long res = 0;
		for (int i = 0; i < m; i++) {
//			cout << "(" << arr[i] << "," << cnt[i] << ") ";
			if (n != arr[i] && n % arr[i] == 0) {
				res = max(res, cnt[i] * (n / arr[i]) + 1);
			}
		}
		cout << res << endl;
	}


	return 0;
}