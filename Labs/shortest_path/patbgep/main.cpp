#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

const int N = 100010;
const int INF = 100000010;

struct edge {
	int v, c;
	edge() {}
	edge(int a, int b) {
		v = a;
		c = b;
	}
};

int n, m;
vector<edge> g[N];
int d[N];
priority_queue< pair<int, int> > q;

void bfs(int st) {
	d[st] = 0;
	q.push(make_pair(st, 0));
	while (!q.empty()) {
		int v = q.top().first;
		q.pop();
		for (int i = 0; i < g[v].size(); i++) {
		    int to = g[v][i].v, w = g[v][i].c;
			if (d[to] > d[v] + w) {
				d[to] = d[v] + w;
				q.push(make_pair(to, d[to]));
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
	freopen("pathbgep.in", "r", stdin);
	freopen("pathbgep.out", "w", stdout);
	scanf("%d%d", &n, &m);
	for (int i = 0; i < m; i++) { 
			int a, b, c;
			scanf("%d%d%d", &a, &b, &c);
			a--;
			b--;
			g[a].push_back(edge(b, c));
			g[b].push_back(edge(a, c));

	}		
	init();
	bfs(0);
	for (int i = 0; i < n; i++)
		printf("%d ", d[i]);
	printf("\n");

}