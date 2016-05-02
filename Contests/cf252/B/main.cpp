#include <cstdio>
#include <iostream>
#include <algorithm>

using namespace std;

const int N = 3010;
int n, v, that[N], next[N];

int main() {
	for (int i = 0; i < N; i++) {
		that[i] = 0;
		next[i] = 0;
	}
	cin >> n >> v;
	for (int i = 0; i < n; i++) {
	 	int a, b;
		cin >> a >> b;
		that[a] += b;
		next[a + 1] += b;
	}
	int res = 0;
	for (int i = 0; i < N; i++) {
		int cnt = min(v, that[i] + next[i]);
	 	res += cnt;
		int tmp = min(next[i], cnt);
		next[i] -= tmp;
		that[i] -= (cnt - tmp);
		next[i + 1] -= (cnt - tmp);
	}
	cout << res << endl;
	return 0;
}