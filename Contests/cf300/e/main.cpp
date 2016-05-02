#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>
#include <set>


using namespace std;
const int N = 200010;
int n;

vector<int> g[N];
int lis[N];

int cntLis(int v) {
	if (g[v].empty()) {
		lis[v] = 1;
		return 1;
	}
	int res = 0;
	for (int i = 0; i < (int)g[v].size(); i++) {
		res += cntLis(g[v][i]);
	}
	lis[v] = res;
	return res;
}

int haveMax(int v, int fun) {
	if (g[v].empty()) {
		return 1;
	}
	if (fun == 1) {
		int mch = -1;
		for (int i = 0; i < (int)g[v].size(); i++) {
			int to = g[v][i];
			if (mch == -1 || lis[to] < lis[mch]) {
				mch = to;
			}
		}
		return lis[v] - mch + haveMax(mch, fun ^ 1);
	} else {
		int mch = -1;
		for (int i = 0; i < (int)g[v].size(); i++) {
			int to = g[v][i];
			if (mch == -1 || lis[to] > lis[mch]) {
				mch = to;
			}
		}
		return lis[v] - mch + haveMax(mch, fun ^ 1);
	}
}


int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n - 1; i++) {
		int a, b;
		cin >> a >> b;
		a--;
		b--;
		g[a].push_back(b);
	}
	int m = cntLis(0);
	cout << haveMax(0, 1) << " ";
		
	return 0;
}