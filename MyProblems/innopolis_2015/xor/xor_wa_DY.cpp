#include <bits/stdc++.h>

using namespace std;
const int N = 100010;

long long arr[N];
long long res[N];
long long n, s;

int main() {
	freopen("xor.in", "r", stdin);
	freopen("xor.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		res[i] = 0;
	}
	cin >> s;
	long long st = 1;
	bool good = true;
	while (st <= s) {
		if ((s & st) > 0) {
			bool ok = false;
			for (int i = 0; i < n; i++) {
				if (res[i] + st <= arr[i]) {
					res[i] += st;
					ok = true;
					break;					
				}
			}
			if (!ok) {
				good = false;
				break;		        	
			}
		}
		st *= 2;
	}
	if (!good) {
		cout << "NO" << endl;
	} else {
		cout << "YES" << endl;
		for (int i = 0; i < n; i++) {
			cout << res[i] << " ";
		}
		cout << endl;
	}
}