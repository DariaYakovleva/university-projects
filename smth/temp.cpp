#include <bits/stdc++.h>

using namespace std;

template <typename T, int c>
T mini(T a, T b) {
	return a > b ? b: a;
}


int main() {
	int a, b;
	cin >> a >> b;
	cout << mini(a, b) << endl;
}