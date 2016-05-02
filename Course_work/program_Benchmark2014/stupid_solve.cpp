#include <bits/stdc++.h>

//asymptotics is O(n! * 2^m)

using namespace std;

struct edge {
	int to;
	int d;
};

struct item {
	int p;
	int w;
	int num;
};


const int N = 100; //max cities
const int M = 30; //max items
const double INF = 10000010.0;

vector<edge> g[N];
int dist[N][N];
vector<item> items[M];

int n, m, w, r;
int k;

int perm[N];

void init() {
	for (int i = 0; i < n; i++) {
		perm[i] = i;
	}
}

int ww[N];

double z(long long mask) {
	double zz = 0;
	int wall = 0;
	for (int i = 0; i < n; i++) ww[i] = 0;

	for (int i = 0; i < n; i++) {
		for (size_t j = 0; j < items[i].size(); j++) {
			if (mask & (1 << items[i][j].num)) {
				zz += items[i][j].p;
				ww[i] += items[i][j].w;
			}		
		}
		wall += ww[i];
		if (wall > w) return -INF;
	}
	for (int i = 1; i < n; i++) {
		ww[perm[i]] += ww[perm[i - 1]];
	}
	double v_min = 0.1;
	double v_max = 1.0;
	double v = (v_max - v_min) / w;
	double sum = dist[perm[n - 1]][perm[0]] / (v_max - v * ww[perm[n - 1]]);
//	cout << sum << endl;
	for (int i = 0; i < n - 1; i++) {
		sum += dist[perm[i]][perm[i + 1]] / (v_max - v * ww[perm[i]]);		
//		cout << dist[perm[i]][perm[i + 1]] << " " << ww[perm[i]] << endl;
	}
//	cout << sum << endl;
	return zz - r * sum;
}

void debug_print(long long mask, double z) {
	for (int i = 0; i < n; i++) {
		cout << perm[i] + 1 << " ";
	}
	cout << "; ";
	for (int i = 0; i < m; i++) {
		if (mask & (1 << i)) cout << i + 1 << " ";
	}
	cout << "; ";
	cout << z << endl;
}


double go() {
    double res = -INF;
	while (next_permutation(perm + 1, perm + n)) {
		for (long long mask = 0; mask < (2 << m); mask++) {
			double cur_z = z(mask);
			res = max(res, cur_z);
//			debug_print(mask, cur_z);
		}
	}
	return res;	
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	cin >> n >> m >> w >> r;
	cin >> k;
	for (int i = 0; i < k; i++) {
		int a, b, d;
		cin >> a >> b >> d;
		a--;
		b--;
		g[a].push_back({b, d});
		g[b].push_back({a, d});
		dist[a][b] = d;
		dist[b][a] = d;

	}
	for (int i = 0; i < m; i++) {
		int pi, wi, c;
		cin >> pi >> wi >> c;
		c--;
		items[c].push_back({pi, wi, i});
	}
	init();
	cout.precision(10);
	cout << go() << endl;	

	return 0;
}

