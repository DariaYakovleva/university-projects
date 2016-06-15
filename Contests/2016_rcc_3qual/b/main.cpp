#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main() {
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		int n;
		cin >> n;
		string a, b;
		getline(cin, a);
		getline(cin, a);
		getline(cin, b);
		int res = 0;
		for (int i = 0; i < n - 1; i++) {
			if (a[i] != b[i]) {
				res++;
				a[i] = b[i];
				a[i + 1] = '0' + (a[i + 1] + 1) % 2;
			}
		}
		if (a[n - 1] == b[n - 1]) {
			cout << res << endl;
		} else {
			cout << "-1" << endl;
	    }
	}


	return 0;
}