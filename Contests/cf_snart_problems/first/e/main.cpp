#include <bits/stdc++.h>

using namespace std;
int n, m;

int main() {
	cin >> n >> m;
	int cnt = n;
	int res = 0;
	while (cnt > 0) {
		res++;
		cnt--;
		if (res % m == 0) cnt++;
	}
	cout << res << endl;
}