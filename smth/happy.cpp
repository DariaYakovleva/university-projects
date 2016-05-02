#include <bits/stdc++.h>

using namespace std;


int goToInt(string s) {
	int st = 1;
	int res = 0;
	for (int i = (int)s.size() - 1; i >= 0; i--) {
		res += st * (s[i] - '0');
		st *= 2;
	}	
	return res;
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	bool ok = true;
	while (ok) {
		string cur = "";
		char c;
		for (int i = 0; i < 8; i++) {
			cin >> c;
			if (c == '!') {
				ok = false;
				break;
			}
			cur += c;
		}
		if (ok) {
			cout << (char)goToInt(cur);
		}
	}

}