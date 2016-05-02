#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

const int N = 30;
int a, b;
int factors[N];
int powers[2][N];
int k = 0;

void add_divider(int x, int pos) {
	int have = false;
	for (int i = 0; i < k; i++) {
		if (factors[i] == x) {
			powers[pos][i]++;
			have = true;
		}
	}
	if (!have) {
		factors[k] = x;
		powers[pos][k] = 1;
		powers[1 - pos][k] = 0;
		k++;
	}

}

void factorization(int a, int pos) {
	int x = a;
	for (int i = 2; i * i <= a; i++) {
		while (x % i == 0) {
			add_divider(i, pos);
			x /= i;
		}	
	}
	if (x != 1) add_divider(x, pos);
	
}

long long power(int a, int x) {
	long long res = 1;
	for (int i = 1; i <= x; i++) res *= (long long)a;
	return res;
}

int main() {
	freopen("gcm.in", "r", stdin);
	freopen("gcm.out", "w", stdout);
	cin >> a >> b;
	factorization(a, 0);
	factorization(b, 1);
	long long x = a;
	long long y = b;
	if (x > y) swap(x, y);
	for (int i = 0; i < (1 << k); i++) {
		long long  cur_x = 1;
		long long cur_y = 1;
		for (int j = 0; j < k; j++) {
			if (i & (1 << j)) {
				cur_x *= pow((long long)factors[j], min(powers[0][j], powers[1][j]));	
				cur_y *= pow((long long)factors[j], max(powers[0][j], powers[1][j]));
			} else {
				cur_y *= pow((long long)factors[j], min(powers[0][j], powers[1][j]));	
				cur_x *= pow((long long)factors[j], max(powers[0][j], powers[1][j]));	
			}
		}
		if (cur_x > cur_y) swap(cur_x, cur_y);
		if (cur_y - cur_x < y - x) {
			y = cur_y;
			x = cur_x;
		}	
	}
	cout << x << " " << y << endl;
	return 0;

}
