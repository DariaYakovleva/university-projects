#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

struct edge {
	int to;
	int c;
	edge() {}
	edge(int a, int b) {
		to = a;
		c = b;
	}	
};

const int N = 100010;

bool used[N];
vector<edge> g[N];
long long sum, maxSum;

void dfs(int v) {
    used[v] = true;

    for (int i = 0; i < (int)g[v].size(); i++) {
        int to = g[v][i].to, w = g[v][i].c;
        sum += w;
        if (!used[to]) {
            dfs(to);
        }
    }
}


int main() {
    int city, roads, a, b, c;
    cin >> city >> roads;
    for (int i = 0; i < roads; i++) {
        cin >> a >> b >> c;
        a--;
        b--;
        g[a].push_back(edge(b, c));
        g[b].push_back(edge(a, c));
    }

    for (int i = 0; i < city; i++) {
        used[i] = false;

    }
    maxSum = 0;
    for(int i = 0; i < city; i++) {
        if (!used[i]) {
        	sum = 0;
            dfs(i);
            sum /= 2;
            maxSum = max(maxSum, sum);
        }
    }
    cout << maxSum << endl;
    return 0;
}
