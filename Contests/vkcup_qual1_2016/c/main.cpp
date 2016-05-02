#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
string arr[N];
int mcnt = 6;

int main() {
	int n;
	cin >> n;
	getline(cin, arr[0]);
	for (int i = 0; i < n; i++) {
		getline(cin, arr[i]);
	}
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			int cnt = 0;
			for (int k = 0; k < 6; k++) {
				if (arr[i][k] != arr[j][k]) cnt++;
			}
			mcnt = min(mcnt, cnt);
		}
	}
	if (n == 1) {
		cout << 6 << endl;
		return 0;
	}
	if (mcnt > 4) {
		cout << 2 << endl;
	} else if (mcnt > 2) {
		cout << 1 << endl;
	} else {
		cout << 0 << endl;
	}

	return 0;
}
