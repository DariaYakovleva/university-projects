#include <bits/stdc++.h>

using namespace std;

const int N = 100010;
const long long MOD = 1000000007;
int arr[N];
int n;
int q;

int main() {
	int q;
	cin >> q;
	for (int k = 0; k < q; k++) {
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> arr[i];
		}
		sort(arr, arr + n);

	}


	return 0;
}