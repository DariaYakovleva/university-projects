#include <cstdio>
#include <iostream>
#include <algorithm>

using namespace std;

const int N = 100010;
int n, k;
int arr[N];

bool prime(int x) {
	for (int i = 2; i * i <= x; i++) {
		if (x % i == 0)
			return true;
	}
   	return false;
}
bool sr(int a, int b) {
	return a > b;
}

int main() {
	
	cin >> n >> k;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	sort(arr, arr + n, sr);
	int res = 0;
	for (int i = 0; i < n; i += k) {
		res += (arr[i] - 1) * 2; 
	}	
	cout << res << endl;



	return 0;
}