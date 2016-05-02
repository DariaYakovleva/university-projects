#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

const int N = 30010;
const int INF = 2009000999;

struct edge {
	int v, to, c;
	edge() {}
	edge(int a, int b, int w) {
		v = a;
		to = b;
		c = w;
	}
};

int n, m;
vector<edge> edges;
int p[N], rank[N];

bool sr(edge a, edge b) {
	return a.c < b.c;
}

int v_get(int v) {
	if (p[v] == v)
		return v;
	return p[v] = v_get(p[v]);
}

void v_union(int v1, int v2) {
	v1 = v_get(v1);
	v2 = v_get(v2);
	if (v1 == v2) return;
	if (rank[v1] < rank[v2])
		swap(v1, v2);
	if (rank[v1] == rank[v2]) {
		rank[v1]++;	
	}	
	p[v2] = v1;
}

int kruskal() {
	int res = 0;
	for (int i = 0; i < m; i++) {
		if (v_get(edges[i].v) != v_get(edges[i].to)) {
			res += edges[i].c;
			v_union(edges[i].v, edges[i].to);
		}	
	}
	return res;
}            

void init() {
	for (int i = 0; i < n; i++) { 
		p[i] = i;
    }
}

int main() {
	freopen("spantree2.in", "r", stdin);
	freopen("spantree2.out", "w", stdout);
	scanf("%d %d", &n, &m);
	init();
	for (int i = 0; i < m; i++) { 
			int a, b, c;                
			scanf("%d%d%d", &a, &b, &c);
			a--;
			b--;
			edges.push_back(edge(a, b, c));
	}		
	sort(edges.begin(), edges.end(), sr);
	cout << kruskal();
}