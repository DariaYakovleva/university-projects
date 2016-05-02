#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
double arr[N];
int n;
double l1;

bool solve(double d) {
	if (arr[0] > d) return false;
	for (int i = 1; i < n; i++) {
		if ((double)(arr[i] - arr[i - 1]) > 2 * d) return false;	
	}
	if ((double)(l1 - arr[n - 1]) > d) return false;
	return true;
}

double bs() {
	double l = 0.0;
	double r = l1;
	for (int i = 0; i < 300; i++) {
		double m = (r + l) / 2.0;
		if (!solve(m)) {
			l = m;
		} else {
			r = m;
		}
	}
	return l;
}

int main() {
	cin >> n >> l1;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	} 
	sort(arr, arr + n);
	cout.precision(11);
	cout << bs() << endl;
}