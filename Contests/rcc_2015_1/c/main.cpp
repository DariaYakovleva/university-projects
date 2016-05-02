#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
#include <string>

using namespace std;

const int N = 110;
int arr[2 * N];
int ddp[2 * N][N][2];

int dp(int cnt, int cntM, int who) {
	if (ddp[cnt][cntM][who - 1] != -1) {
	cout << "??" <<endl;
		return ddp[cnt][cntM][who - 1];
	}
	if (arr[cnt] != 3 && arr[cnt] != who) {
		ddp[cnt][cntM][who - 1] = 0;
		return ddp[cnt][cntM][who - 1];
	}
	if (cntM == 0) {
		if ((who == 1 && cntM == 1) || (who == 2 && cntM == 0)) {
			ddp[cnt][cntM][who - 1] = 1;
		} else {
			ddp[cnt][cntM][who - 1] = 0;
		}
		return ddp[cnt][cntM][who - 1];
	}
	if (cntM == 1) {
		if ((who == 1 && (cntM == 1 || cntM == 2)) || (who == 2 && (cntM == 0 || cntM == 1))) {
			ddp[cnt][cntM][who - 1] = 1;
		} else {
			ddp[cnt][cntM][who - 1] = 0;
		}
		return ddp[cnt][cntM][who - 1];	
	}

	if (who == 1) {
		ddp[cnt][cntM][who - 1] = (cntM - cntM) * dp(cnt - 2, cntM - 1, 2) + cntM * dp(cnt - 1, cntM - 1, 1);
	} else {
		ddp[cnt][cntM][who - 1] = (cntM - 1) * dp(cnt - 2, cntM - 1, 1) + (cnt - cntM + 1) * dp(cnt - 1, cntM, 2);
	}
	return ddp[cnt][cntM][who - 1];

}

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		int n;
		cin >> n;
		for (int j = 0; j < 2 * n; j++) {
			cin >> arr[j];
		}
		for (int j = 0; j <= 2 * n; j++)
			for (int k = 0; k <= n; k++)
				for (int h = 0; h < 2; h++)
					ddp[j][k][h] = -1;
		cout << dp(2 * n - 1, n, 1) + dp(2 * n - 1, n, 2) << endl;	
	}
	return 0;
}