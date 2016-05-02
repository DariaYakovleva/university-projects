#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>

using namespace std;
string arr[110];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	int res = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (arr[i][j] == 'C') {
				for (int k = i + 1; k < n; k++) {
					if (arr[k][j] == 'C') res++;
				}
				for (int k = j + 1; k < n; k++) {
					if (arr[i][k] == 'C') res++;
				}

			}
		}
	}
	cout << res << endl;
	return 0;
}