#include <bits/stdc++.h>

using namespace std;
int arr[110];
int main() {
	int n;
	cin >> n;
	int ccnt1 = 0;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		if (arr[i] == 1) ccnt1++;
	}
	int res = ccnt1 - 1;
	for (int i = 0; i < n; i++) {
		for (int j = i; j < n; j++) {
			int cnt0 = 0;
			int cnt1 = 0;
			for (int k = i; k <= j; k++) {
					if (arr[k] == 0) cnt0++;
				else cnt1++;
			}	
			if (ccnt1 - cnt1 + cnt0 > res) res = ccnt1 - cnt1 + cnt0;
		}
	}
	cout << res << endl;
}
