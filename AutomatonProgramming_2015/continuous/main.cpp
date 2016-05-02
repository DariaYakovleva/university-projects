#include <bits/stdc++.h>

using namespace std;
const int N = 10 + 10;
const int M = 50 + 10;
const int LEN = 250 + 10;
const double EPS = 1e-8;

int n, m;
pair<int, int> tree[N];
int length[M];

char in[M][LEN];
double out[M][LEN];
double gauss[2 * N][2 * N];
double res[2 * N];
int have[2 * N];

void go() {
	for (int i = 0; i < 2 * n; i++) {
		res[i] = 0.0;
		for (int j = 0; j < 2 * n; j++) {
			gauss[i][j] = 0.0;
		}
	}
	for (int k = 0; k < 2 * n; k++) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 2 * n; j++) have[j] = 0;
			int v = 0;
			int len = length[i];
			for (int j = 0; j < len; j++) {
				int next = in[i][j] - '0';
// number = 2 * v + next								
//				for (int h = 0; h < 2 * n; h++) {
//					if (have[h] > 0) have[h]++;
//				}
				have[2 * v + next]++;
				if (have[k] > 0) {
					res[k] += 2 * out[i][j] / len * have[k];
					for (int h = 0; h < 2 * n; h++) {
						if (have[h] > 0) gauss[k][h] += 2 * (double)have[h] / len * have[k];	
					}
				}
				if (next) v = tree[v].second;
				else v = tree[v].first;
			}
		}
	}

}

void get_ans() {
	for (int i = 0; i < 2 * n; i++) {
		if (fabs(gauss[i][i]) < EPS) {
			bool ok = false;
			for (int k = i + 1; k < 2 * n; k++) {
				if (ok) break;
				if (fabs(gauss[k][i]) > EPS) {
					ok = true;
					for (int h = 0; h < 2 * n; k++) {
						swap(gauss[i][h], gauss[k][h]);
					}
				}
			}
			if (!ok) {
				for (int k = 0; k < 2 * n; k++) {
					gauss[i][k] = 0.0;
				}
				res[i] = 0.0;
				gauss[i][i] = 1.0;
			}
		} 
		double cur = gauss[i][i];
		for (int k = 0; k < 2 * n; k++) {
			gauss[i][k] /= cur;
		}
		res[i] /= cur;
		for (int j = i + 1; j < 2 * n; j++) {
			double val = gauss[j][i];
			for (int k = 0; k < 2 * n; k++) {
				gauss[j][k] -= val * gauss[i][k];
			}					
			res[j] -= val * res[i];
		}
	}
	
	for (int i = 2 * n - 1; i >= 0; i--) {
		for (int j = 0; j < i; j++) {
			double val = gauss[j][i];
			for (int k = 0; k < 2 * n; k++) {
				gauss[j][k] -= val * gauss[i][k];
			}
			res[j] -= val * res[i];
		}
	}
//	for (int i = 0; i < 2 * n; i++) {
//		for (int j = 0; j < 2 * n; j++) {
//			cout << gauss[i][j] << " ";
//		}
//		cout << res[i] << endl;
//	}
}   	

int main() {
	freopen("continuous.in", "r", stdin);
	freopen("continuous.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> tree[i].first >> tree[i].second;
		tree[i].first--;
		tree[i].second--;
	}
	for (int i = 0; i < m; i++) {
		cin >> length[i];
		cin >> in[i];
		for (int j = 0; j < length[i]; j++) {
			cin >> out[i][j];
		}
	}
	go();
	get_ans();
	cout.precision(8);
	for (int i = 0; i < n; i++) {
		cout << res[2 * i] << " " << res[2 * i + 1] << endl;
	}

}                         