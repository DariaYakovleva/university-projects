#include <iostream>
#include <cstdio>
#include <string>
#include <map>
#include <set>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int keys[30];

int main() {
	int n;
	cin >> n;
	string s;
	getline(cin, s);
	getline(cin, s);

	for (int i = 0; i < 30; i++) {
		keys[i] = 0;
	}
	int ans = 0;
	for (int i = 0; i < (int)s.size(); i++) {
		if (i % 2 == 0) {
			keys[s[i] - 'a']++;	
		} else {
			if (keys[s[i] - 'A'] > 0) {
				keys[s[i] - 'A']--;
			} else {
				ans++;
			}
		}
	}
	cout << ans << endl;



	return 0;
}