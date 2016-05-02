#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <set>

using namespace std;

const int N = 10;

int t;
int n;
int g[N][N];
int arr[N];
vector<int> v1, v2;


void bt(int pos, int k) {
	if (pos == 6) {
	if (k < 3) return;
		v1.clear();
		v2.clear();
		for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) g[i][j] = 0;
		for (int i = 0; i < 6; i++) {
			if (arr[i]) v1.push_back(i);
			else v2.push_back(i);
		}
		for (int i = 0; i < v1.size(); i++)
		for (int j = 0; j < v2.size(); j++) {
		    g[v1[i]][v2[j]] = 1;
		    g[v2[j]][v1[i]] = 1;
		}
		for (int i = 0; i < n; i++)
		for (int j = 0; j < i; j++)
			cout << g[i][j];
		cout << endl;
		return;
	}
	if (k < 3) {
		arr[pos] = 1;
		bt(pos + 1, k + 1);
		arr[pos] = 0;
		bt(pos + 1, k);
	}
	else {
		arr[pos] = 0;
		bt(pos + 1, k);
	}	
}
int main() {
	freopen("planaritycheck.in", "r", stdin);
	freopen("planaritycheck.out", "w", stdout);
	n = 6;
	bt(0,0);
	
	return 0;
	for (int i = 0; i < (1 << n); i++) {
		for (int j = 0; j < n; j++) {
			cout << ((i & (1 << j)) >> j);
		}
		cout << endl;
	}
	return 0;
}