#include <bits/stdc++.h>

using namespace std;

const int N = 100010;
int n, m;
int arr[N];

int bs(int x) {
	int l = -1;
	int r = n;
	while (r - l > 1) {
		int m = (l + r) / 2;
		if (x > arr[m]) {
			l = m;
		} else {
			r = m;		
		}
	}
	if (arr[r] < x) r++; 
	return r;
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		if (i > 0) arr[i] += arr[i - 1];
	}
	cin >> m;
	for (int i = 0; i < m; i++) {
		int q;
		cin >> q;
		cout << bs(q) + 1 << endl;
	}	
}