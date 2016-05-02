#include <bits/stdc++.h>

using namespace std;


int main() {
	string res = "";
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		char c;
		cin >> c;
		int a = c - '0';
		if (a == 2 || a == 3 || a == 5 || a == 7) res += (char)('0' + a);
		if (a == 4) res += "223";
		if (a == 6) res += "35";
		if (a == 8) res += "7222";
		if (a == 9) res += "7332";
	}
	sort(res.begin(), res.end());
	for (int i = (int)res.size() - 1; i >= 0; i--) {
		cout << res[i];
	}
	cout << endl;
}