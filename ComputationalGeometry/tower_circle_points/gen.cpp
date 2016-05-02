#include <bits/stdc++.h>

using namespace std;


int main() {
	srand(time(NULL));
	freopen("tower.in", "w", stdout);
	int n = rand() % 100 + 1;
	cout << n << endl;
	for (int i = 0; i < n; i++) {
		cout << rand() % 2000 - 1000 << " " << rand() % 2000 - 1000 << endl;
	}
	
}