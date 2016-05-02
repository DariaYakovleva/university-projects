#include <bits/stdc++.h>

using namespace std;

const int N = 20;

int n;
int m;
int weigth[N];
bool have[1048579];

void set_c(int x) {
	have[x] = true;
	if (x == ((1 << n) - 1))
		return;
	for (int i = 0; i < n; i++) {
		if ((x & (1 << i)) == 0 && !have[x + (1 << i)]) {
			set_c(x + (1 << i));
		}
	}
}


int main() {
	freopen("cycles.in", "r", stdin);
	freopen("cycles.out", "w", stdout);
	scanf("%d%d", &n, &m);
	for (int i = 0; i < n; i++) {
		scanf("%d", &weigth[i]);
	}
	for (int mask = 0; mask < (1 << n); mask++) {
		have[mask] = false;
	}
	for (int i = 0; i < m; i++) {
		int c;
		scanf("%d", &c);
		int mm = 0;
		for (int j = 0; j < c; j++) {
			int k;
			scanf("%d", &k);
			k--;
			mm += (1 << k); 
		}
		set_c(mm);
	}
//	cerr << 2 << endl;
	int msum = 0;
	int msize = 0;
	for (int mask = 0; mask < (1 << n); mask++) if (!have[mask]) {
	    int sum = 0;
	    int c = 0;
	    for (int k = 0; k < n; k++) if ((mask & (1 << k)) > 0) {
			sum += weigth[k];
			c++;
		}
		if (c > msize) {
			msize = c;
			msum = 0;
		}
		msum = max(msum, sum);
	}
	printf("%d\n", msum);
}
