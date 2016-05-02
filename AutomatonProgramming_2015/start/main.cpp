#include <bits/stdc++.h>

using namespace std;

const int N = 500 + 10;
const int M = 5 * 100000 + 10;

struct vertex {
	int l;
	int r;
	char c;
};

vertex tree[N];
int n;
int m;
char s[M];
vector<int> res;
bool dp[N][2];

void ddp() {
	for (int i = 0; i < n; i++) {
		dp[i][m % 2] = true;
	}
	for (int i = m - 1; i >= 0; i--) {
		for (int j = 0; j < n; j++) {
			dp[j][i % 2] = false;
			int left = tree[j].l;
			int right = tree[j].r;
			if (tree[left].c == s[i]) dp[j][i % 2] |= dp[left][(i + 1) % 2];
			if (tree[right].c == s[i]) dp[j][i % 2] |= dp[right][(i + 1) % 2];	
		}
	}	
}

void init() {
	for (int i = 0; i <= n; i++) {
		for (int j = 0; j < 2; j++) {
			dp[i][j] = false;
		}
	}
}
int main() {
	freopen("start.in", "r", stdin);
	freopen("start.out", "w", stdout);
	scanf("%d %d", &m, &n);
	init();
	for (int i = 0; i < n; i++) {
		int l, r;
		char c;
		scanf("%d %d %c", &l, &r, &c);
		l--; r--;
		tree[i] = {l, r, c};
	}
	scanf("%s", s);
	scanf("%s", s);
	ddp();
	for (int i = 0; i < n; i++) {
		if (dp[i][0]) {
			res.push_back(i);
		}
//		cout << i << " " << j << " " << dfs(i, j) << endl;
	}
	printf("%d ", (int)res.size());
	for (int x: res) printf("%d ", x + 1);
	return 0;
}