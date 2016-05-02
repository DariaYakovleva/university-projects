#include <bits/stdc++.h>

using namespace std;

char arr[60][60];
string face = "acef";
int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}
	int ans = 0;
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 1; j++) {
//			cout << a << endl;
			string a = "";
			a += arr[i][j];
			a += arr[i + 1][j];
			a += arr[i][j + 1];
			a += arr[i + 1][j + 1];
			sort(a.begin(), a.end());
			if (a == face) ans++;
		}
	}
	cout << ans << endl;

}