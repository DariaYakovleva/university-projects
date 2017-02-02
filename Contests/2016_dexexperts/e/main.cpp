#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
#include <set>
#include <queue>

using namespace std;

const int N = 100010;
const int INF = 1000000010;
int n, m;
vector<pair<int, int>> graph[N];
vector<pair<int, int>> short_graph[N];
int dist[N];
bool used[N];
queue<int> que;

void bfs(int st) {
	que.push(st);
	used[st] = true;
	dist[st] = 0;
	while (!que.empty()) {
		int v = que.front();
		que.pop();
		for (size_t i = 0; i < graph[v].size(); i++) {
			int to = graph[v][i].first;
			if (!used[to]) {
				que.push(to);
				used[to] = true;
				dist[to] = dist[v] + 1;
			}
		} 
	}	
}

void dfs(int v) {
	if (dist[v] == 0) return;
	used[v] = true;
	for (size_t i = 0; i < graph[v].size(); i++) {
		pair<int, int> to = graph[v][i];
		if (dist[to.first] == dist[v] - 1) {
			short_graph[v].push_back(to);
			short_graph[to.first].push_back({v, to.second});
			dfs(to.first);
		}
	}
}


vector<int> ans;
int tin[N];
int up[N];
int cur_time = 0;
void findBridges(int v, int p) {
	used[v] = true;
	tin[v] = cur_time;
	cur_time++;
	up[v] = tin[v];
	for (size_t i = 0; i < short_graph[v].size(); i++) {
		pair<int, int> to = short_graph[v][i];
		if (to.second == p) continue;
		if (used[to.first]) {
			up[v] = min(up[v], up[to.first]);
		} else {
			findBridges(to.first, to.second);
			up[v] = min(up[v], up[to.first]);
			if (up[to.first] > tin[v]) ans.push_back(to.second);
		}
	}
	
	
}



void init() {
	for (int i = 0; i < N; i++) {
		used[i] = false;
	}
}

int main() {
	cin >> n >> m;
	init();
	for (int i = 0; i < N; i++) {
		dist[i] = INF;
	}	
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		graph[a].push_back({b, i});
		graph[b].push_back({a, i});
	}
	init();
	bfs(0);
	init();
	dfs(n - 1);
	init();
	findBridges(0, -1);
	if (ans.empty()) {
		cout << -1 << endl;
	} else {
		for (int e: ans) {
			cout << e + 1 << " ";
		}
		cout << endl;
	}



	return 0;
}       