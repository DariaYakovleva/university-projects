#include <bits/stdc++.h>

using namespace std;

const int N = 100010;

struct edge {
	int num;
	int v;
	int to;
	long long w;
};

int rankd[N];
int parent[N];
vector<edge> graph;
int n;
int m;
long long s;

void init() {
	for (int i = 0; i < N; i++) {
		rankd[i] = 0;
		parent[i] = i;
	}
}

int get(int x) {
	if (x == parent[x]) return x;
	return parent[x] = get(parent[x]);
}

void union_dsu(int x, int y) {
    x = get(x);
    y = get(y);
	if (x == y) {
		return;
	}
	if (rankd[x] < rankd[y]) {
		swap(x, y);
	}
	if (rankd[x] == rankd[y]) {
		rankd[x]++;
	}
	parent[y] = x;
}

int main() {
	freopen("destroy.in", "r", stdin);
	freopen("destroy.out", "w", stdout);
	cin >> n >> m >> s;
	for (int i = 0; i < m; i++) {
		int a, b;
		a--;
		b--;
		long long c;
		cin >> a >> b >> c;
		graph.push_back({i, a, b, c});
	}
	init();
	sort(graph.begin(), graph.end(), [](const edge &a, const edge &b) { return a.w > b.w; });
	vector<int> other;
	for (int i = 0; i < m; i++) {
		if (get(graph[i].v) != get(graph[i].to)) {
			union_dsu(graph[i].v, graph[i].to);
//			cout << graph[i].num << endl;
		} else {
			other.push_back(i);
		}
	}
	vector<int> ans;
	long long res = 0;
	for (int i = (int)other.size() - 1; i >= 0; i--) {
		if (graph[other[i]].w + res <= s) {
			ans.push_back(graph[other[i]].num);
			res += graph[other[i]].w;
		}	
	}
	sort(ans.begin(), ans.end());
	cout << (int)ans.size() << endl;
	for (int i = 0; i < (int)ans.size(); i++) {
		cout << ans[i] + 1 << " ";
	}
	cout << endl;


}
