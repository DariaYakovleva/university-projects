#include <iostream>
#include <cstdio>
#include <vector>
#include <string>

using namespace std;


int main() {
	int n;
	int mi = -1, ma = -1;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		if (a < mi || mi == -1) mi = a;
		if (a > ma || ma == -1) ma = a;
	}
	cout << ma - mi << endl;


	return 0;
}