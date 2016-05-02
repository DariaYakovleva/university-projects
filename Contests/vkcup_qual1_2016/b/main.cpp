#include <bits/stdc++.h>

using namespace std;

const int N = 200010;
string arr[N];
map<string, int> have;

int main() {
	int n;
	cin >> n;
	getline(cin, arr[0]);
	for (int i = 0; i < n; i++) {
		getline(cin, arr[i]);
	}
	for (int i = n - 1; i >= 0; i--) {
		if (have[arr[i]] == 0) {
			cout << arr[i] << endl;
			have[arr[i]] = 1;
		}
	}

	return 0;
}
