#include <bits/stdc++.h>

using namespace std;

int main() {
	srand(time(NULL));
	int n = rand() % 10;
	cout << n << endl;
//	cerr << "N = " << n << endl;
	for (int i = 0; i < n; i++) {
		int a = rand() % 10;
		int b = rand() % 10;
		int c = rand() % 10;
		int d = rand() % 10;
		cout << a << " " << b << " " << c << " " << d << endl;
	}

}