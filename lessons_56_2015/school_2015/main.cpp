#include <bits/stdc++.h>

using namespace std;

int main() {
	int a = 2;
	int b = 3;
	int cur = 8;
	for (int i = 3; i < 50; i++) {
		int c = a + b - 1;
		a = b;
		b = c;
		cur += c;
		cout << i << " " << c << " " << cur << endl;
	}
}