#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <algorithm>

using namespace std;

string s;
void init() {

}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int tests;
	cin >> tests;
	for (int tt = 0; tt < tests; tt++) {
		init();
		cin >> s;
		int cnt = 0;
		for (int i = 0; i < s.size() - 1; i++) {
			if (s[i] != s[i + 1]) cnt++;
		}
		if (s[s.size() - 1] == '-') cnt++;
		cout << "Case #" << (tt + 1) << ": " << cnt << endl;
		cerr << "Case #" << (tt + 1) << ": " << cnt << endl;
	}


	return 0;
}