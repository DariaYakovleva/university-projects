#include <bits/stdc++.h>

using namespace std;
const int N = 100010;
int arr[N];
int n;
int main() {
	cin >> n;	
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	int maxi = 1;
	int cur = 1;
	for (int i = 1; i < n; i++) {
		if (arr[i] > arr[i - 1]) {
			cur++;
		} else {
			maxi = max(maxi, cur);
			cur = 1;
		}
	}
	maxi = max(maxi, cur);
	cout << maxi << endl;	
}