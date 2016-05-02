#include <bits/stdc++.h>

using namespace std;

int main() {
	srand(time(NULL));
	int n = 3;//rand() % 10;
	cout << n << endl;
//	cerr << "N = " << n << endl;
	for (int i = 0; i < n; i++) {
		int a = rand() % 10;
		int b = rand() % 10;
		cout << a << " " << b << endl;
	}
	cout << n << endl;
	for (int i = 0; i < n; i++) {
		int a = rand() % 20;
		int b = rand() % 20;
		cout << a << " " << b << endl;
	}

}