#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <map>
#define ll long long 


using namespace std;

const int N = 100010;
int n, m;
int table[20][N];
map<int, ll> mp;

int gcd(int a, int b) {
	if (b == 0)
		return a;
	return gcd(b, a % b);
}


void init_sparse_table() {
	for (ll i = 1; (1 << i) <= n; i++) {
		for (ll j = 0; j < n; j++) {
			table[i][j] = gcd(table[i - 1][j], table[i - 1][j + (1 << (i - 1))]);
		}
	}
}

ll get_gcd(int l, int r) {
	int k = 0;
	while ((1 << (k + 1)) <= (r - l + 1)) k++;
	return gcd(table[k][l], table[k][r - (1 << k) + 1]);
}


int rbs(int x, int st) {
	int l = st - 1;
	int r = n;
	while (r - l > 1) {
		int m = (r + l) / 2;
		if (get_gcd(st, m) >= x) {
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
	    	int curG = get_gcd(i, cur);
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
	 	scanf("%d", &table[0][i]);
	 }
	 init_sparse_table();
	 solve();
	 scanf("%d", &m);
	 for (int i = 0; i < m; i++) {
	 	int x;
	 	scanf("%d", &x);
	 	printf("%I64d\n", max((ll)0, mp[x]));
	 }
}