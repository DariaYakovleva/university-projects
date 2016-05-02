#include <iostream>
#include <cstdio>
#include <cstring>
#include <vector>
#include <queue>
#include <set>
#include <cmath>

using namespace std;

const int N = 5010;
const int INF = 1000000010;

pair<int, int> arr[N];
int n, m;
int g[N][N];
bool used[N];
int d[N];

int dist(pair<int, int> a, pair<int, int> b) {
	return (b.first - a.first) * (b.first - a.first) + (b.second - a.second) * (b.second - a.second);
}

void prim(int st) {
	d[st] = 0;	
	for (int i = 0; i < n; i++) {
		int v = -1;
		for (int j = 0; j < n; j++) {
			if ((!used[j]) && (v == -1 || d[j] < d[v])) {
				v = j;
			}
		}
		cout << v << endl;
		used[v] = true;
		for (int j = 0; j < n; j++) {
			if ((!used[j]) && g[v][j] < d[j]) {
				d[j] = g[v][j];
			}
		}
	}
}            
void init() {
	for (int i = 0; i < n; i++) {
		used[i] = false;
		d[i] = INF;
		for (int j = 0; j < n; j++)
			g[i][j] = INF + 1;
	}
}

int main() {
	cin >> n >> m;
	init();
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--; b--;
		g[a][b] = c;
		g[b][a] = c;
	}
	prim(0);
	double res = 0;
	for (int i = 0; i < n; i++) {
		cout << d[i] << " " ;
		res += d[i];
	}
	cout << res << endl;

	return 0;

}