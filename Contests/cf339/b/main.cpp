#include <bits/stdc++.h>

using namespace std;
const int N = 100010;

string arr[N];

int main() {
	int n;
	cin >> n;
	string num = "1";
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		if (arr[i] == "0") {
			num = "0";
			cnt = 0;
			break;
		}
		int ok = 0;
		bool ok2 = false;
		for (char c: arr[i]) {
			if (c == '1') ok++;
			if ((c != '0' && c != '1') || (ok > 1)) {
				num = arr[i];
				ok2 = true;
			}
		}
		if (!ok2) cnt += arr[i].size() - 1;
	}
	cout << num;
	for (int i = 0; i < cnt; i++) {
		cout << "0";
	}


	return 0;
}