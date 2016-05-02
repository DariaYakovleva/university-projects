#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <map>
#define ll long long 


using namespace std;

const int N = 100010;
int n, m;
int arr[N];
int tree[4 * N];
map<int, ll> mp;

int gcd(int a, int b) {
	while (b != 0) {
		int c = a;
		a = b;
		b = c % b;
	}
	return a;
}

void build(int v, int l, int r) {
    if (v >= 4 * N) return;
	if (r - l == 1) {
		tree[v] = arr[l];
		return;
   	}
   	int m = (l + r) / 2;
	build(2 * v + 1, l, m);
	build(2 * v + 2, m, r);
	if (2 * v + 2 < 4 * N) {
		tree[v] = gcd(tree[2 * v + 1], tree[2 * v + 2]);
	} else if (2 * v + 1 < 4 * N) {
		tree[v] = tree[2 * v + 1];
	} else {
		tree[v] = 0;
	}
}


void update(int v, int l, int r, int x, int b) {
	if (v >= 4 * N) return;
	if (l > x || r <= x) return;
	if (r - l == 1) {
		tree[v] = b;
		return;
	}
	int m = (l + r) / 2;
	update(2 * v + 1, l, m, x, b);
	update(2 * v + 2, m, r, x, b);
	if (2 * v + 2 < 4 * N) {
		tree[v] = gcd(tree[2 * v + 1], tree[2 * v + 2]);
	} else if (2 * v + 1 < 4 * N) {
		tree[v] = tree[2 * v + 1];
	} else {
		tree[v] = 0;
	}
}

int get_gcd(int v, int l, int r, int ql, int qr) {
	if (v >= 4 * N) return 0;
	if (l >= qr || r <= ql) return 0;
	if (l >= ql && r <= qr) {
		return tree[v];
	}
	int m = (l + r) / 2;
	return gcd(get_gcd(2 * v + 1, l, m, ql, qr), get_gcd(2 * v + 2, m, r, ql, qr));
}

int rbs(int x, int st) {
	int l = st - 1;
	int r = n;
	while (r - l > 1) {
		int m = (r + l) / 2;
		if (get_gcd(0, 0, N, st, m + 1) >= x) {
			l = m;
		} else {
			r = m;
		}
	}
//	cout << x << " " << st << " " << l << endl;
	return l;
}

void solve() {
	for (int i = 0; i < n; i++) {
	    int cur = i;
	    while (cur < n) {
	    	int curG = get_gcd(0, 0, N, i, cur + 1);
	    	int cnt = rbs(curG, i) - cur + 1;
	    	if (mp[curG] == -1) {
	    		mp[curG] = cnt;
	    	} else {
	    		mp[curG] += cnt;
	    	}
	    	cur += cnt;
	    }
		//res += (rbs(x, i) - lbs(x, i) + 1);
	}
} 

int main() {
	 scanf("%d", &n);
	 for (int i = 0; i < n; i++) {
	 	scanf("%d", &arr[i]);
	 }
	 build(0, 0, N);
	 solve();
	 scanf("%d", &m);
	 for (int i = 0; i < m; i++) {
	 	int x;
	 	scanf("%d", &x);
	 	printf("%I64d\n", max((ll)0, mp[x]));
	 }
}