#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>
#include <set>


using namespace std;
const int N = 55;
int n;
char arr[N][N];
vector < pair<int, int> > xx;
vector < pair<int, int> > oo;
char ans[N * 2][N * 2];

int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 'x') {
				xx.push_back(make_pair(i, j));
			}
			if (arr[i][j] == 'o') {
				oo.push_back(make_pair(i, j));
			}
		}
	}
	bool good = true;
	vector < pair<int, int> > res;
	for (int i = 0; i < (int)xx.size(); i++) {
		bool xok = false;
		for (int j = 0; j < (int)oo.size(); j++) {
			int a = xx[i].first - oo[j].first;
			int b = xx[i].second - oo[j].second;
//			cout << a << " " << b << endl;
			bool ok = true;
			for (int k = 0; k < (int)oo.size(); k++) {
				int x = oo[k].first + a;
				int y = oo[k].second + b;
//				cout << "x" << x << " "<< y << endl;
				if (x < 0 || y < 0 || x >= n || y >= n) {
					ok = true;
				} else if (arr[x][y] == '.') {
					ok = false;
					break;
				}
			}
			if (ok) {
				res.push_back(make_pair(a, b));
//				cout << "x" << i << j << endl;
				xok = true;
				break;
			}
		}
		if (xok == false) {
			good = false;
			break;
		}
	}
	if (!good) {
		cout << "NO" << endl;
	} else {
		cout << "YES" << endl;
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				ans[i][j] = '.';	
			}
		}
		ans[n - 1][n - 1] = 'o';
		for (int i = 0; i < (int)res.size(); i++) {
			ans[n - 1 + res[i].first][n - 1 + res[i].second] = 'x';
		}
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				cout << ans[i][j];
			}
			cout << endl;
		}
	}

	return 0;
}