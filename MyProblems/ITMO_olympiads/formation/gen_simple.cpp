#include <bits/stdc++.h>

using namespace std;

const int N = 5;
int n;

int main() {
	srand(0);
	cin >> n;
	int c1 = N;
	int c2 = N - 1;
	cout << n << endl;
	for (int i = 0; i < n; i++) {
		if (rand() % 2 == 1) {
			cout << c1 << " ";
		} else {
			cout << c2 << " ";
		}		
	}	
	cout << endl;
	cout << n << endl;
	for (int i = 0; i < n; i++) {
		cout << 239 << " ";		
	}	
	cout << endl;
}
