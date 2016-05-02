#include <iostream>
#include <cstdio>
#include <string>
#include <map>
#include <set>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 100010;
int arr[N];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	sort(arr, arr + n);
	long long ans = 0;
	int lena = -1;
	int i = n - 1;	
	while (i > 0) {
		if (arr[i] - arr[i - 1] < 2) {
			if (lena == -1) {
				lena = arr[i - 1];
			} else {
				ans += (long long)arr[i - 1] * lena;
				lena = -1;		
			}
			i -= 2;	
		} else {
			i--;
		}			
	}
	cout << ans << endl;
		



	return 0;
}