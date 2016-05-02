#include <bits/stdc++.h>

using namespace std;


char arr[110][110];
int ans[110][110];
int cnt = 0;
void upd(int x, int y, int a) {
	for (int i = 0; i <= x; i++) {
		for (int j = 0; j <= y; j++) {
			ans[i][j] += a;
		}
	}
	cnt++;
}


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
	for (int j = m - 1; j >= 0; j--) {
		for (int i = n - 1; i >= 0; i--) {
			if (arr[i][j] == 'W' && ans[i][j] != 1) {
				upd(i, j, 1 - ans[i][j]); 
			} 
			if (arr[i][j] == 'B' && ans[i][j] != -1) {
				upd(i, j, -1 - ans[i][j]);
			}
		}
	}
	cout << cnt << endl;

}