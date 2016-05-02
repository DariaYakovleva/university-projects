#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to;
	char c;
};

const int N = 110;
const long long M = 1000000007;
int n, m, k, l;
vector<edge> nka[N];
vector<edge> dka[N * N];
bool n_term[N];
bool term[N * N];
long long dp[N * N][N * N];


void init() {
	for (int i = 0; i < N; i++) {
		n_term[i] = false;
	}
	for (int i = 0; i < N * N; i++) {
		term[i] = false;
		n_term[i] = false;
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

queue< set<int> > q;
set< set<int> > s;
map< set<int>, int > number;
int cnt = 0;
void tomson() {
	set<int> st;
	st.insert(0);
	q.push(st);
	s.insert(st);
	number[st] = cnt;
	cnt++;
	if (n_term[0]) term[0] = true;
	while (!q.empty()) {
		 set<int> vec = q.front();
		 q.pop();
		 for (char c = 'a'; c <= 'z'; c++) {
		 	set<int> to;
			for (auto v: vec) {
		 		for (int j = 0; j < (int)nka[v].size(); j++) {
		 			if (nka[v][j].c == c) to.insert(nka[v][j].to);	
		 		}
		 	}
		 	if (to.empty()) continue;
		 	if (s.count(to) == 0) {
		 		q.push(to);
		 		s.insert(to);
		 		number[to] = cnt;
		 		for (auto v: to) {
		 			if (n_term[v]) {
						term[cnt] = true;
						break;		 			
		 			}
		 		}
		 		cnt++;
		 	}
			 int n_from = number[vec];
			 int n_to = number[to];
			 dka[n_from].push_back({n_to, c});
		 }
	}

}

int main() {
	freopen("problem5.in", "r", stdin);
	freopen("problem5.out", "w", stdout);
	cin >> n >> m >> k >> l;
	init();
	for (int i = 0; i < k; i++) {
		int a;
		cin >> a;
		n_term[a - 1] = true;
	}
	for (int i = 0; i < m; i++) {
		int a, b;
		char c;
		cin >> a >> b >> c;
		a--; b--;
		nka[a].push_back({b, c});
	}
	tomson();
//	int i = 0;
//	for (auto g: dka) {
//		for (auto v: g) {
//			cout << i << " " << v.to << " " << v.c << endl;
//		}
//		i++;
//	}
//	for (int j = 0; j <= cnt; j++) {
//		cout << term[j] << "!" << endl;
//	}
  
	cout << go(0, l) << endl;
	
	return 0;
}