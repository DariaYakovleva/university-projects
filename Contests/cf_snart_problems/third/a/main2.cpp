#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
int arr[N];

int main() {
	int n;
	int l;
	cin >> n >> l;
	arr[0] = 0;
	for (int i = 0; i < n; i++) {
		cin >> arr[i + 1];
	} 
	n++;
	sort(arr, arr + n);
	arr[n + 1] = arr[n] + 2 * (l - arr[n]);
	n++;
	int mm = 2 * arr[1];
	for (int i = 2; i < n; i++) {
		mm = max(arr[i] - arr[i - 1], mm);
	}
	cout.precision(11);
	cout << (double)mm / 2.0 << endl;

}