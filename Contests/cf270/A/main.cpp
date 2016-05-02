#include <cstdio>
#include <iostream>
#include <algorithm>

using namespace std;

bool prime(int x) {
	for (int i = 2; i * i <= x; i++) {
		if (x % i == 0)
			return true;
	}
   	return false;
}

int main() {
	int n;
	cin >> n;
	for (int i = 4; i * 2 <= n; i++) {
		if (prime(i) && prime(n - i)) {
			cout << i << " " << n - i << endl;
			return 0;
		}
	}



	return 0;
}