#include <bits/stdc++.h>

using namespace std;

const int N = 1010;

int n, m, k;
int aut[N][30];
vector<int> autr[N][30];
int naut[N][30];
bool mark[N][N];
bool term[N];
bool nterm[N];
bool used[N];
int comp[N];
int cnt_t = 0;
int m1 = 0;
int num = 0;

void init() {
	for (int i = 0; i < N; i++) {
		term[i] = false;
		used[i] = false;
		comp[i] = -1;
		nterm[i] = false;
		for (int j = 0; j < 30; j++) {
			aut[i][j] = 0;
			naut[i][j] = 0;
		}
		for (int j = 0; j < N; j++) {
			mark[i][j] = false;
		}
	}
}

void dfs(int v) {
	used[v] = true;
	for (int i = 0; i < 30; i++) {
		if (!used[aut[v][i]]) {
			dfs(aut[v][i]);
		}
	}
}

void buildDKA() {
	dfs(1);
	queue< pair<int, int> > q;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!mark[i][j] && (term[i] != term[j])) {
				mark[i][j] = true;
				mark[j][i] = true;
				q.push(make_pair(i, j));
			}
		}
	}
	while (!q.empty()) {
		pair<int, int> to = q.front();
		q.pop();
		for (int c = 0; c < 30; c++) {
			for (int u: autr[to.first][c]) {
				for (int v: autr[to.second][c]) {
					if (!mark[u][v]) {
			  			mark[u][v] = true;
			  			mark[v][u] = true;
			  			q.push(make_pair(u, v));
			  		}	
			  	}
			}
		}
	}
	for (int i = 0; i < n; i++) 
		if (!mark[0][i]) comp[i] = 0;

	for (int i = 1; i < n; i++) {
		if (!used[i]) continue;
		if (comp[i] != -1) continue;
		num++;
		comp[i] = num;
		for (int j = i + 1; j < n; j++) {
			if (!mark[i][j]) {
				comp[j] = num;
			}
		}
	}
	for (int i = 0; i < n; i++) cout << "c" << i << "=" << comp[i] << ";";
	for (int i = 0; i < n; i++) {
		if (term[i] && comp[i] != -1 && !nterm[comp[i]]) {
			nterm[comp[i]] = true;
			cnt_t++;
		}	
	}
	if (nterm[0]) cnt_t--;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 30; j++) {
			if (comp[i] > 0 && comp[aut[i][j]] > 0 && naut[comp[i]][j] == 0) {
				naut[comp[i]][j] = comp[aut[i][j]];
				m1++;
			}
		}
	}
}

int main() {
	freopen("minimization.in", "r", stdin);
	freopen("minimization.out", "w", stdout);
	init();
	cin >> n >> m >> k;	
	for (int i = 0; i < k; i++) {
		int a;
		cin >> a;
		term[a] = true;
	}
	for (int i = 0; i < m; i++) {
		int a, b;
		char c;
		cin >> a >> b >> c;
		aut[a][c - 'a'] = b;
	}
	n++;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 30; j++) {
			autr[aut[i][j]][j].push_back(i);
		}
	}

	buildDKA();
	cout << num << " " << m1 << " " << cnt_t << endl;
	for (int i = 1; i < n; i++) {
		if (nterm[i]) cout << i << " ";
	}
	cout << endl;
	for (int i = 1; i < n; i++) {
		for (int j = 0; j < 30; j++) {
			if (naut[i][j] != 0) {
				cout << i << " " << naut[i][j] << " " << (char)('a' + j) << endl;
			}
		}
	}

}