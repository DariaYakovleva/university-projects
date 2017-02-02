#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

struct vertex {
	int prev;
	vector<int> edges;
	int d;
	vertex() {
		d = 0;
		prev = -1;
	};
};

const int N = 100010;
vertex graph[N];
int n;
bool used[N];

bool cmp(int a, int b) {
	return graph[a].d < graph[b].d;
}

int dfs(int v) {
	used[v] = true;
	int len = graph[v].edges.size();
	for (int i = 0; i < len; i++) {	
		int to = graph[v].edges[i];
		if (!used[to]) {
			dfs(to);
		}
	}
	if (len == 0) {
		graph[v].d = 1;
		return 1;
	}
	sort(graph[v].edges.begin(), graph[v].edges.end(), cmp);
	int res = len;
	for (int i = 0; i < len; i++) {
		int to = graph[v].edges[i];
		res = max(res, graph[to].d + len - i - 1);
	}
	graph[v].d = res;
	return res;
}

void init() {
	for (int i = 0; i < N; i++) {
		used[i] = false;
	}
}

int main() {
	cin >> n;
	init();
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		graph[i + 1].prev = a;
		graph[a].edges.push_back(i + 1);	
	}
	n++;
	cout << dfs(0) - 1 << endl;



	return 0;
}       