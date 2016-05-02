#include <cstdio>
#include <iostream>

using namespace std;

int main() {
	int n;
	cin >> n;
	if (n > 2 && n % 2 == 0) {
		cout << "YES" << endl;
		cout << "1 * 2 = 2" << endl;
		cout << "2 * 3 = 6" << endl;
		cout << "6 * 4 = 24" << endl;
		for (int i = 5; i <= n; i += 2) {
			cout << (i + 1) << " - " << i << " = 1" << endl;
			cout << "1 * 24 = 24" << endl;
		} 
	} else if (n > 6) {
		cout << "YES" << endl;
		cout << "7 - 1 = 6" << endl;
		cout << "6 * 4 = 24" << endl;

		cout << "3 - 2 = 1" << endl;
		cout << "1 * 24 = 24" << endl;
		cout << "6 - 5 = 1" << endl;
		cout << "1 * 24 = 24" << endl;
		for (int i = 8; i <= n; i += 2) {
			cout << (i + 1) << " - " << i << " = 1" << endl;
			cout << "1 * 24 = 24" << endl;
		}
	} else if (n == 5) {
	cout << "YES" << endl;
		cout << "5 - 2 = 3" << endl;
		cout << "3 * 4 = 12" << endl;
		cout << "3 - 1 = 2" << endl;
		cout << "12 * 2 = 24" << endl;
		
	} else {
		cout << "NO" << endl;
	}


	return 0;
}