#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

const int N = 1010;
int n, m, cnt, cur;
long long k;
long long arr[N][N];

int used[N][N];
int kk = 0;
int h;

void dfs(int i, int j, int num) {
	used[i][j] = num;
	kk++;
	if (i > 0 && arr[i - 1][j] == h && used[i - 1][j] == -1) dfs(i - 1, j, num);
	if (i + 1 < n  && arr[i + 1][j] == h && used[i + 1][j] == -1) dfs(i + 1, j, num);
	if (j > 0 && arr[i][j - 1] == h && used[i][j - 1] == -1) dfs(i, j - 1, num);
	if (j + 1 < m && arr[i][j + 1] == h && used[i][j + 1] == -1) dfs(i, j + 1, num);
}

void dfs2(int i, int j, int num) {
	used[i][j] = 1;
	if (i > 0 && arr[i - 1][j] == h && used[i - 1][j] == -1) dfs(i - 1, j, num);
	if (i + 1 < n  && arr[i + 1][j] == h && used[i + 1][j] == -1) dfs(i + 1, j, num);
	if (j > 0 && arr[i][j - 1] == h && used[i][j - 1] == -1) dfs(i, j - 1, num);
	if (j + 1 < m && arr[i][j + 1] == h && used[i][j + 1] == -1) dfs(i, j + 1,num);
	int cc = 0;
	if (i > 0 && arr[i - 1][j] >= h) cc++;
	if (i + 1 < n  && arr[i + 1][j] >= h) cc++;
	if (j > 0 && arr[i][j - 1] >= h) cc++;
	if (j + 1 < m && arr[i][j + 1] >= h) cc++;
	if (cur > cnt && cc <= num) {
		arr[i][j] = 0;
		cur--;
	}
}


int main() {
	cin >> n >> m >> k;
	h = k + 1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
			used[i][j] = -1;
			if (k % arr[i][j] == 0 && arr[i][j] < h) h = arr[i][j];
		}
	}
	if (h == k + 1) {
		cout << "NO" << endl;
		return 0;
	}
	cnt = k / h;
	cur = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[i][j] >= h) {
				arr[i][j] = h;
				cur++;
				int cc = 0;
				if (i > 0 && arr[i - 1][j] >= h) cc++;
				if (i + 1 < n  && arr[i + 1][j] >= h) cc++;
				if (j > 0 && arr[i][j - 1] >= h) cc++;
				if (j + 1 < m && arr[i][j + 1] >= h) cc++;
				if (cc == 0) {
					cout << "NO" << endl;
					return 0;
				}
			} else {
				arr[i][j] = 0;
			}
		}
	}
	if (cur < cnt) {
		cout << "NO" << endl;
		return 0;
	}
	int nn = 0;
	int pos = -1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			kk = 0;
			if (arr[i][j] > 0 && used[i][j] == -1) {
				dfs(i, j, nn);
				if (kk >= cnt) pos = nn;
				nn++;
			}				
		}
	}
	if (pos == -1) {
		cout << "NO" << endl;
		return 0;
	}
	int si = 0;
	int sj = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (used[i][j] != pos) {
				arr[i][j] = 0;
			} else {
				used[i][j] = -1;
				si = i;
				sj = j;
			}							
		}
	}
	dfs2(si, sj, 1);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			used[i][j] = -1;							
		}
	}
	dfs2(si, sj, 2);

	cout << "YES" << endl;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << arr[i][j] << " ";
		}
		cout << endl;
	}


		
	return 0;
}