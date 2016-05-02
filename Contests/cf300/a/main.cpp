#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <set>


using namespace std;

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	string s;
	getline(cin, s);
	int n = (int)s.size();
	bool have = false;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j <= n; j++) {
			string ss = "";
			for (int k = 0; k < i; k++) {
				ss += s[k];
			}
			for (int k = j; k < n; k++) {
				ss += s[k];
			}
			if (ss == "CODEFORCES") {
				have = true;
			}
		}
	}
	if (have) {
		cout << "YES" << endl;
	} else {
		cout << "NO" << endl;
	}

	return 0;
}