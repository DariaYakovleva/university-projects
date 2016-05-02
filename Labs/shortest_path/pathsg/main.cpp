#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

const int N = 210;
const int INF = 10000010;

struct edge {
	int v, c;
	edge() {}
	edge(int a, int b) {
		v = a;
		c = b;
	}
};

int n, m;
int d[N][N];

void forforfor() {
	for (int k = 0; k < n; k++) {	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (d[i][k] + d[k][j] < d[i][j]) {
					d[i][j] = d[i][k] + d[k][j];
				}
			}
		}
	}
}            

void init() {
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < n; j++) { 
			if (i == j) {
				d[i][j] = 0;
			} else {
				d[i][j] = INF;
			}
    	}
}

int main() {
	freopen("pathsg.in", "r", stdin);
	freopen("pathsg.out", "w", stdout);
	cin >> n >> m;
	init();
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--;
		b--;
		d[a][b] = c;		
	}		
	forforfor();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << d[i][j] << " ";
	    }
		cout << endl;
    }

    return 0;
}