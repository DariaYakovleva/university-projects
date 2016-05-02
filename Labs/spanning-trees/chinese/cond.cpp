#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

const int N = 100010;

int n, m;
vector<int> g[N];
vector<int> g2[N];
vector<int> res;
int colors[N];
bool circle = false;
int num[N];

void top_sort(int v) {
    colors[v] = 2;
    for (int i = 0; i < g[v].size(); i++) {
    	int to = g[v][i];
    	if (colors[to] == 1) {
    		circle = true;
    	}
    	if (colors[to] == 0) {
    		top_sort(to);
    	}
    }
    colors[v] = 2;
    res.push_back(v);
}

void cond(int v, int numb) {
	colors[v] = 2;
	num[v] = numb;
	for (int i = 0; i < g2[v].size(); i++) {
    	int to = g2[v][i];
       	if (colors[to] == 0) {
    		cond(to, numb);
    	}
    }
    colors[v] = 2;	
}

void init() {
	for (int i = 0; i < N; i++) {
		colors[i] = 0;
	}
}


int main() {
	freopen("cond.in", "r", stdin);
	freopen("cond.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
		g2[b].push_back(a);
	}
	init();
	for (int i = 0; i < n; i++) {
		if (colors[i] == 0) {
			top_sort(i);
		}
	}
	if (circle) {
		cout << "-1" << endl;
		return 0;
	}
	reverse(res.begin(), res.end());
	int cur = 1;
	init();
	for (int i = 0; i < n; i++) {
		if (colors[res[i]] == 0) {
			cond(res[i], cur);
			cur++;
		}		
	}
	cout << cur - 1 << endl;
	for (int i = 0; i < n; i++) {
		cout << num[i] << " ";
	}
	


	return 0;
}