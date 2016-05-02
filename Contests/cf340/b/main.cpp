#include <bits/stdc++.h>

using namespace std;

const int N = 110;
int a;

int main() {
	int n;
	cin >> n;
	int cur = 0;
	long long res = 1;
	bool have = false;
	for (int i = 0; i < n; i++) {
		cin >> a;
		if (a == 0) {
			cur++;
		}
		else {
			if (have) res *= cur + 1;
			have = true;
			cur = 0;
		}
	}
	if (!have) cout << 0 << endl;
	else cout << res << endl;
	
	return 0;
}