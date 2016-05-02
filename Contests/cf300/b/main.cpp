#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <set>


using namespace std;

int arr[10];
int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int n;
	cin >> n;
	for (int i = 0; i < 10; i++) {
		arr[i] = 0;
	}
	int cur = 1;
	while (n != 0) {
		int cnt = n % 10;
		n /= 10;
		for (int i = 0; i < cnt; i++) {
			arr[i] += cur;
		}
		cur *= 10;
	}
	int m = 0;
	for (int i = 0; i < 10; i++) {
		if (arr[i] != 0) {
			m++;
		}
	}
	cout << m << endl;
	for (int i = 0; i < 10; i++) {
		if (arr[i] != 0) {
			cout << arr[i] << " ";
		}
	}

	return 0;
}