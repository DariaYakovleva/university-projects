#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to;
	char c;
};

const int N = 110;
const long long M = 1000000007;
int n, m, k, l;
vector<edge> dka[N];
bool term[N];
long long dp[N][N * N];

void init() {
	for (int i = 0; i < n; i++) {
		term[i] = false;
		for (int j = 0; j <= l; j++) {
			dp[i][j] = -1;
		}
	}
}

long long go(int v, int len) {
	if (dp[v][len] != -1) {
		return dp[v][len];
	}
	if (len == 0) {
		if (term[v]) {
			dp[v][len] = 1;
		} else {
			dp[v][len] = 0;
		}
		return dp[v][len];
	}
	dp[v][len] = 0;
	for (size_t i = 0; i < dka[v].size(); i++) {
		int to = dka[v][i].to;
		dp[v][len] = (dp[v][len] + go(to, len - 1)) % M;
	}
	return dp[v][len];
}

int main() {
	freopen("problem4.in", "r", stdin);
	freopen("problem4.out", "w", stdout);
	cin >> n >> m >> k >> l;
	init();
	for (int i = 0; i < k; i++) {
		int a;
		cin >> a;
		term[a - 1] = true;
	}
	for (int i = 0; i < m; i++) {
		int a, b;
		char c;
		cin >> a >> b >> c;
		a--; b--;
		dka[a].push_back({b, c});
	}
	cout << go(0, l) << endl;
	
	return 0;
}