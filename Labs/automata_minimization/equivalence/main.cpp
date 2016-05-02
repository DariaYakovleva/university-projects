#include <bits/stdc++.h>

using namespace std;

const int N = 10010;

int n1, m1, k1;
int aut1[N][30];
int n2, m2, k2;
int aut2[N][30];
bool term1[N];
bool term2[N];
bool used1[N];
bool used2[N];

bool bfs() {
	queue< pair<int, int> > q;
	q.push(make_pair(1, 1));
	while (!q.empty()) {
		pair<int, int> cur = q.front();
		q.pop();
		if (term1[cur.first] != term2[cur.second]) return false;
		for (int i = 0; i < 30; i++) {
			int u = aut1[cur.first][i];
			int v = aut2[cur.second][i];
			if (!used1[u] || !used2[v]) {
					q.push(make_pair(u, v));
					used1[u] = true;
					used2[v] = true;
			}
		}
	}
	return true;
}

void init() {
	for (int i = 0; i < N; i++) {
		term1[i] = false;
		term2[i] = false;
		used1[i] = false;
		used2[i] = false;
		for (int j = 0; j < 30; j++) {
			aut1[i][j] = 0;
			aut2[i][j] = 0;
		}
	}
}

int main() {
	freopen("equivalence.in", "r", stdin);
	freopen("equivalence.out", "w", stdout);
	init();
	cin >> n1 >> m1 >> k1;	
	for (int i = 0; i < k1; i++) {
		int a;
		cin >> a;
		term1[a] = true;
	}
	for (int i = 0; i < m1; i++) {
		int a, b;
		char c;
		cin >> a >> b >> c;
		aut1[a][c - 'a'] = b;
	}
	cin >> n2 >> m2 >> k2;	
	for (int i = 0; i < k2; i++) {
		int a;
		cin >> a;
		term2[a] = true;
	}
	for (int i = 0; i < m2; i++) {
		int a, b;
		char c;
		cin >> a >> b >> c;
		aut2[a][c - 'a'] = b;
	}
	if (bfs()) {
		cout << "YES" << endl;
	} else {
		cout << "NO" << endl;
	}
}