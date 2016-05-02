#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

const int N = 100010;
long long n, x, c[N];

int main() {
    cin >> n >> x;
	for (int i = 0; i < n; i++) {
		cin >> c[i];	
	}
	sort(c, c + n);
	long long res = 0;
	for (int i = 0; i < n; i++) {
		res += c[i] * (x - i > 1?x - i:1);
	}
	cout << res << endl;
	return 0;
}