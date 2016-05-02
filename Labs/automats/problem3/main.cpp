#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to;
	char c;
};

const int N = 100010;
const long long M = 1000000007;
int n, m, k;
vector<edge> dka[N];
vector<edge> idka[N];
bool term[N];
int used[N];
long long dp[N];
bool need[N];
int path[N];

void init() {
	for (int i = 0; i < n; i++) {
		term[i] = false;
		used[i] = 0;
		dp[i] = -1;
		need[i] = false;
		path[i] = -1;
	}
}

void dfs(int v) {
	need[v] = true;
	for (size_t i = 0; i < idka[v].size(); i++) {
		int to = idka[v][i].to;
		if (!need[to]) {
			dfs(to);
		}				           	
	}
}

bool circle(int v) {
	used[v] = 1;
	for (size_t i = 0; i < dka[v].size(); i++) {
		int to = dka[v][i].to;
		if (used[to] == 1) {
			if (need[to]) return true;
		    int p = path[v];
		    while (p != to) {
		    	if (need[p]) return true;
		    	p = path[p];		
		    }
		} else if (!used[to]) {
			path[to] = v;
			if (circle(to)) return true;
		}
	}
	used[v] = 2;	
	return false;
}

long long go(int v) {
	if (dp[v] != -1) {
		return dp[v];
	}
	dp[v] = 0;
	for (size_t i = 0; i < idka[v].size(); i++) {
		int to = idka[v][i].to;
		dp[v] = (dp[v] + go(to)) % M;
	}
	return dp[v];
}

int main() {
	freopen("problem3.in", "r", stdin);
	freopen("problem3.out", "w", stdout);
	cin >> n >> m >> k;
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
		idka[b].push_back({a, c});
	}
	for (int i = 0; i < n; i++) {
		if (term[i] && !used[i]) {
			dfs(i);	
		}
	}
	dp[0] = 1;
	if (!circle(0)) {
		long long ans = 0;
		for (int i = 0; i < n; i++) {
			if (term[i]) {
				ans = (ans + go(i)) % M;
			}
		}
		cout << ans << endl;
	} else {
		cout << "-1" << endl;
	}
	
	return 0;
}