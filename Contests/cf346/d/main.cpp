#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

const int N = 100010;
pair<int, int> arr[N];
int n;

int vect(pair<int, int> a, pair<int, int> b, pair<int, int> c) {
	int ax = c.first - a.first;
	int ay = c.second - a.second;
	int bx = b.first - a.first;
	int by = b.second - a.second;
	return ax * by - ay * bx;
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].first >> arr[i].second;
	}
	int res = 0;
	for (int i = 1; i < n; i++) {
		if (vect(arr[i - 1], arr[i], arr[(i + 1) % n]) < 0) {
			res++;
//			cout << i << endl;
		}
	}
	cout << res << endl;

	return 0;
}