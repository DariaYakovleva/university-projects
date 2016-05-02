#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
#include <vector>
#include <set>

using namespace std;
char arr[110];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	bool good = false;
	for (int k = 1; k <= n; k++) {
		for (int st = 0; st < n; st++) {
			good = true;
			for (int i = 0; i < 5; i++) {
				if (st + k * i < n) {
					if (arr[st + k * i] != '*') {
						good = false;
						break;
					}
				} else {
					good = false;
					break;
				}
			}
			if (good) {
				break;
			}
		}
		if (good) {
			break;
		}
	}
	if (good) {
		cout << "yes" << endl;
	} else {
		cout << "no" << endl;
	}


	return 0;
}