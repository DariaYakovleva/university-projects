#include <bits/stdc++.h>

using namespace std;
const int N = 1010;

struct my {
	int x;
	char prev;
};

int arr[N][N];
my dp[N][N];
int n;
int res = 0;

vector<char> ans;

int power(int a, int p) {
	int res = 0;
	while (a % p == 0) {
		res++;
		a /= p;
	}
	return res;
}

void init(int a) {
    dp[0][0].prev = -1;
    dp[0][0].x = power(arr[0][0], a);
	for (int i = 1; i < n; i++) {
		int x = arr[i][0];
		dp[i][0] = dp[i - 1][0];
		dp[i][0].x += power(x, a);
		dp[i][0].prev = 'D';
	}	

	for (int i = 1; i < n; i++) {
		int x = arr[0][i];
		dp[0][i] = dp[0][i - 1];
		dp[0][i].x += power(x, a);
		dp[0][i].prev = 'R';
	}	
}

int path() {
    ans.clear();
    int two = 0, five = 0;
	int i = n - 1, j = n - 1;
	while (i > 0 || j > 0) {
		ans.push_back(dp[i][j].prev);
		two += power(arr[i][j], 2);
		five += power(arr[i][j], 5);
		if (dp[i][j].prev == 'D') {
			i--;
		} else {
			j--;
		}
	}
	two += power(arr[0][0], 2);
	five += power(arr[0][0], 5);
	return min(two, five);
}

int ddp(int a) {
	for (int i = 1; i < n; i++) {
		for (int j = 1; j < n; j++) {
			if (dp[i][j - 1].x < dp[i - 1][j].x) {
				dp[i][j] = dp[i][j - 1];
				dp[i][j].prev = 'R';
			} else {
				dp[i][j] = dp[i - 1][j];
				dp[i][j].prev = 'D';
			}
			dp[i][j].x += power(arr[i][j], a);
		}
	}
	return dp[n - 1][n - 1].x;
}

int main() {
	int zj = -1;
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 0) {
				zj = j;
				arr[i][j] = 10;
			}
		}
	}
	init(2);
	int res2 = ddp(2);
	res = path();
	init(5);
	int res5 = ddp(5);
	if (res5 < res2) {
		res = path();		
	}
	if (res > 1 && zj != -1) {
		cout << 1 << endl;
		for (int i = 0; i < zj; i++) {
			cout << "R";
		}
		for (int i = 0; i < n - 1; i++) {
			cout << "D";
		}
		for (int i = zj; i < n - 1; i++) {
			cout << "R";
		}
	} else {
		cout << res << endl;
		reverse(ans.begin(), ans.end());	
		for (auto x: ans) {
			cout << x;
		}
	}
	cout << endl;

	return 0;
}