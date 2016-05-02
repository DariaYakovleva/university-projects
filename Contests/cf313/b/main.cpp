#include <bits/stdc++.h>

using namespace std;

string a, b;

string sm(int l1, int r1, const string &s) {
	if ((r1 - l1) % 2 == 1) {
		return s.substr(l1, r1 - l1);
	}
	int m = (r1 - l1) / 2;
	string l = sm(l1, l1 + m, s);
	string r = sm(l1 + m, r1, s);
	if (l < r) return l + r;
	return r + l;
}

int main() {
	getline(cin, a);
	getline(cin, b);
	if (sm(0, (int)a.size(), a) == sm(0, b.size(), b)) {
		cout << "YES" << endl;
	} else {
		cout << "NO" << endl;
	}
	return 0;
}
