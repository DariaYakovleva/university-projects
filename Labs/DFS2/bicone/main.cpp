#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

const int N = 20010;
const int INF = 10000010;
struct vertex {
	int v, edge;
	vertex() {}
	vertex(int a, int b) {
		v = a;
		edge = b;
	}
};
vector<vertex> g[N];
bool used[N];
int n, m;
int timer = 0;
int color = 0;
int tin[N];
int fmin[N];
int colors[N];
vector<int> res;
vector<int> stack;

void paint(int v) {
	if (stack.empty()) return;
	int cur = stack.back();
	while (cur != v && !stack.empty()) {
		stack.pop_back();		
		colors[cur] = color;
		cur = stack.back();
	}
	stack.pop_back();		
	colors[cur] = color;
	color++;
}

void dfs(int v, int p) {
	used[v] = true;
	stack.push_back(v);
	tin[v] = timer;
	fmin[v] = timer;
	timer++;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i].v, num = g[v][i].edge;
		if (num != p) {
			if (used[to]) {
				fmin[v] = min(fmin[v], tin[to]);
			} else {
				dfs(to, num);
				fmin[v] = min(fmin[v], fmin[to]);
				if (fmin[to] > tin[v]) {
					paint(to);
				}
			}
		}
	}
}

void init() {
	for (int i = 0; i < N; i++) {
		used[i] = false;
		tin[i] = INF;
		fmin[i] = INF;
		colors[i] = -1;
	}
}

int main() {
	freopen("bicone.in", "r", stdin);
	freopen("bicone.out", "w", stdout);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
    	int a, b;
    	cin >> a >> b;                     
    	a--;
    	b--;
    	g[a].push_back(vertex(b, i));
    	g[b].push_back(vertex(a, i));
    }
    init();
    for (int i = 0; i < n; i++) {
    	if (!used[i]) {
    		dfs(i, -1);
    		paint(i);
    	}
    }
    cout << color << endl;
    for (int i = 0; i < n; i++) 
    	cout << colors[i] + 1 << " ";
    

	return 0;
}                     