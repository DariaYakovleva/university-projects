#include <bits/stdc++.h>

using namespace std;
const int N = 10 + 10;
const int M = 2000 + 10;
const int LEN = 10000 + 10;

int n, m;
pair<int, int> tree[N];
char str1[M][LEN];
char str2[M][LEN];
int length[M];
int cnt[N][2][27][LEN];

char res[N][2];

void go() {
	for (int i = 0; i < m; i++) {
		int v = 0;
		int len = length[i];
		for (int j = 0; j < len; j++) {
			char c = str2[i][j];
			int next = (str1[i][j] - '0');
			cnt[v][next][c - 'a'][len]++;
			if (next == 0) {
				v = tree[v].first;
			} else {
				v = tree[v].second;
			}
		}
	}
}

void get_ans() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 2; j++) {
			int sym = 0;
			double m = -1.0;
			for (int c = 0; c < 27; c++) {
				double val = 0.0;
				for (int len = 1; len < LEN; len++) {
					val += cnt[i][j][c][len] / (double)len;	
				}
				if (val > m) {
					m = val;
					sym = c;
				}
			}
//			cout << i << " " << j << m << endl;
			res[i][j] = (char)('a' + sym);
		}
	}	
}

int main() {
	freopen("discrete.in", "r", stdin);
	freopen("discrete.out", "w", stdout);
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &tree[i].first, &tree[i].second);
		tree[i].first--;
		tree[i].second--;
	}
	for (int i = 0; i < m; i++) {
		scanf("%d %s %s", &length[i], str1[i], str2[i]);
	}
	go();
	get_ans();
//	for (int i = 0; i < n; i++) {
//		for (int j = 0; j < 2; j++) {
//			for (int k = 0; k < 3; k++) {
//				for (int x = 0; x < 8; x++) {
//					cout << i << j << k << x << " =  " << cnt[i][j][k][x] << endl;
//				}
//			}
//		}
//	}
	for (int i = 0; i < n; i++) {
		printf("%c %c\n", res[i][0], res[i][1]);
	}

}                         