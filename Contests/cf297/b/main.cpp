#include <iostream>
#include <cstdio>
#include <string>
#include <map>
#include <set>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int rev[300010];

int main() {
	int m;
	string s;
	getline(cin, s);
	cin >> m;
	for (int i = 0; i < 300010; i++) {
		rev[i] = 0;
	}
	int n = (int)s.size();
	for (int i = 0; i < m; i++) {
	    int a;
		cin >> a;
		rev[a - 1]++;
	}
	for (int i = 0; i < (n + 1) / 2; i++) {
		rev[i] += rev[i - 1];
		if (rev[i] % 2 != 0) {
			char tmp = s[i];
			s[i] = s[n - i - 1];
			s[n - i - 1] = tmp;
		}
	}
	cout << s << endl;




	return 0;
}