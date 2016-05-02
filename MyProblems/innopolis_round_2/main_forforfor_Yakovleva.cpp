#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>

using namespace std;

const int N = 200;
const long long INF = 5000000000100000;

struct tel {
	int x;
	int l;
	int r;
	int c;
	tel() {}
	tel(int b, int cc, int d, int e) {
		x = b;
		l = cc;
		r = d;
		c = e;
	}	
} telep[N];

int n, s;
long long dist[N][N];
long long dist2[N][N];
int ways[N];

void init() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			dist[i][j] = INF;
			dist2[i][j] = INF;
		}
		dist[i][i] = 0;
		dist2[i][i] = 0;
		ways[i] = 0;
	}
}

void forforfor(int s) {
	for (int k = 0; k < n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dist[i][k] + dist[k][j] < dist[i][j]) {
					dist[i][j] = dist[i][k] + dist[k][j];
				} else if (dist[i][k] + dist[k][j] == dist[i][j]) {
					
				}
			}
		}
	}
	for (int j = 0; j < n; j++) {
		for (int k = 0; k < n; k++) if (k != j && k != s) {
			if (dist[s][j] != INF && (dist[s][k] + dist2[k][j]) == dist[s][j]) {
				ways[j] += 1;
			}
		}
	}
	for (int j = 0; j < n; j++) {
		for (int k = 0; k < n; k++) if (k != j && k != s) {
			if (dist[s][j] != INF && (dist[s][k] + dist2[k][j]) == dist[s][j]) {
				ways[j] += ways[k];
			}
		}
	}

}

void constGr() {
	init();
	for (int i = 0; i < n; i++) {
//		cout << i << " " << telep[i].p << " " << telep[i].x << " " << telep[i].l << " " << telep[i].r << endl;
		for (int j = 0; j < n; j++) if (i != j) {
			int xx = telep[j].x;
			if ((xx >= telep[i].x - telep[i].r && xx <= telep[i].x - telep[i].l) || 
			(xx >= telep[i].x + telep[i].l && xx <= telep[i].x + telep[i].r)) {
//				cout << i << " " << j << endl;
				dist[i][j] = telep[i].c;	
				dist2[i][j] = telep[i].c;
			}
		}
	}		
}

int main() {
	freopen("teleports.in", "r", stdin);
	freopen("teleports.out", "w", stdout);
//	cerr << "TEST" << endl;
	cin >> n >> s;
	s--;
	for (int i = 0; i < n; i++) {
		int x, l, r, c;
		cin >> x >> l >> r >> c;
		telep[i] = tel(x, l, r, c);
	}
	constGr();
	ways[s] = 1;
	forforfor(s);
	for (int i = 0; i < n; i++) {
		int a = i;
		if (dist[s][a] < INF) {
			cout << dist[s][a];
			if (ways[a] > 1) cout << " YES";
			else cout << " NO";
			cout << endl;
		} else {
			cout << -1 << endl;
		}
	}
	return 0;
}