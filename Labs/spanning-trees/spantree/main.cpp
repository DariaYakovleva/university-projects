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
int n;
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
			if (!used[j] && (v == -1 || d[j] < d[v])) {
				v = j;
			}
		}
		used[v] = true;
		for (int j = 0; j < n; j++) if (v != j) {
			if ((!used[j]) && g[v][j] < d[j]) {
				d[j] = g[v][j];
			}
		}
	}
}            

int main() {
	freopen("spantree.in", "r", stdin);
	freopen("spantree.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
			cin >> arr[i].first >> arr[i].second;
			used[i] = false;
			d[i] = INF;
	}
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) {
			g[i][j] = dist(arr[i], arr[j]); 
		}
	prim(0);
	double res = 0;
	for (int i = 0; i < n; i++) {
//		cout << d[i] << " " ;
		res += sqrt((double)d[i]);
	}
	printf("%.10lf\n", res);

	return 0;

}