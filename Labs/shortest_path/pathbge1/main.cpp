#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

const int N = 100010;
const int INF = 1000010;

int n, m;
vector<int> g[N];
int d[N];
queue<int> q;

void bfs(int st) {
	d[st] = 0;
	q.push(st);
	while (!q.empty()) {
		int v = q.front();
		q.pop();
		for (int i = 0; i < g[v].size(); i++)
			if (d[g[v][i]] > d[v] + 1) {
				d[g[v][i]] = d[v] + 1;
				q.push(g[v][i]);
			}
	}
}            

void init() {
	for (int i = 0; i < n; i++) { 
		d[i] = INF;
    }
}

int main() {
	freopen("pathbge1.in", "r", stdin);
	freopen("pathbge1.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
		g[b].push_back(a);
	}
	init();
	bfs(0);
	for (int i = 0; i < n; i++) {
		cout << d[i] << " ";
	}
	cout << endl;


}