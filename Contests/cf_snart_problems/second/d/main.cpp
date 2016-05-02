#include <bits/stdc++.h>

using namespace std;
const int N = 1000010;
int arr[N];
int pref[N];
int main() {
	int n;
	cin >> n;
	int res = 0;
	pref[0] = 0;	
	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
		res ^= arr[i];
		pref[i + 1] = pref[i] ^ (i + 1);
	}

	for (int i = 1; i <= n; i++) {
		int k = i;
		if (n > 1 && (n % k) == (n - 1)) k++;
		res ^= pref[n % k];
		if ((n / k) % 2 == 1) {
			res ^= pref[i - 1];	
		}
	}
	cout << res << endl;

}