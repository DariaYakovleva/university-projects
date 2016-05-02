#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string s, t;

int firstCut() {
	int poss = 0;
	for (int i = 0; i < (int)t.size(); i++) {
		if (t[i] == s[poss]) poss++;
		if (poss == (int)s.size()) {
			return i;
		}
	}
	return (int)t.size();
}

int secondCut() {
	int poss = (int)s.size() - 1;
	for (int i = (int)t.size() - 1; i >= 0; i--) {
		if (t[i] == s[poss]) poss--;
		if (poss == -1) {
			return i;
		}
	}
	return -1;
}


int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	getline(cin, s);
	getline(cin, t);
	int left = firstCut();
	int right = secondCut();
	if (right < left) {
		cout << 0 << endl;
	} else {
		cout << right - left;	
	}
	return 0;
}