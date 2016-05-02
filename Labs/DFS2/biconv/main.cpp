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
int colors[N * 10];
vector<int> stack;

void point(int num) {
	while (!stack.empty() && stack.back() != num) {
		int cur = stack.back();
		colors[cur] = color;
		stack.pop_back();
	}
	colors[num] = color;
	stack.pop_back();
	color++;
}

void dfs(int v, int p) {
	used[v] = true;
	tin[v] = timer;
	fmin[v] = timer;
	timer++;
	int ch = 0;
	for (int i = 0; i < g[v].size(); i++) {
		int to = g[v][i].v, num = g[v][i].edge;
		if (to != p) {
			if (used[to]) {
				if (tin[to] < tin[v]) 
					stack.push_back(num);
				fmin[v] = min(fmin[v], tin[to]);
			} else {
				stack.push_back(num);
				dfs(to, v);
				ch++;
				fmin[v] = min(fmin[v], fmin[to]);
				if (fmin[to] >= tin[v]) {
					point(num);
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
	}
}

int main() {
	freopen("biconv.in", "r", stdin);
	freopen("biconv.out", "w", stdout);
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
    	}
    }
    
    cout << color << endl;
    for (int i = 0; i < m; i++) {
    	cout << colors[i] + 1 << " ";
    }

	return 0;
}                     