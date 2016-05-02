#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

const int N = 10010;
const int INF = 10000010;

struct edge {
	int v, c;
	edge() {}
	edge(int a, int b) {
		v = a;
		c = b;
	}
};

int n, s, t;
vector<edge> g[N];
int d[N];
queue<int> q;

void bfs(int st) {
	d[st] = 0;
	q.push(st);
	while (!q.empty()) {
		int v = q.front();
		q.pop();
		for (int i = 0; i < g[v].size(); i++) {
		    int to = g[v][i].v, w = g[v][i].c;
			if (d[to] > d[v] + w) {
				d[to] = d[v] + w;
				q.push(to);
			}
		}
	}
}            

void init() {
	for (int i = 0; i < n; i++) { 
		d[i] = INF;
    }
}

int main() {
	freopen("pathmgep.in", "r", stdin);
	freopen("pathmgep.out", "w", stdout);
	scanf("%d%d%d", &n, &s, &t);
	s--;
	t--;
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < n; j++) {
			int a;
			scanf("%d", &a);
			if (a != -1 && i != j) 
				g[i].push_back(edge(j, a));

		}		
	init();
	bfs(s);
	if (d[t] == INF) {
		printf("%d\n", -1);
	} else {
		printf("%d\n", d[t]);
	}

}