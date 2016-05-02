#include <iostream>
#include <cstdio>
#include <iostream>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int M = 30;
const int N = 150100;

int arr[N];
int n;
int c[N];
int p[N];
int pn[N];
int cn[N];
int classes[20][N];
int cntClass = 0;
int curK = 0;
int logN = 0;
int m;

void buildSuf() {
	for (int h = 0; (1 << h) < n; h++) {
		logN = h;
		for (int i = 0; i < n; i++) {
			pn[i] = p[i] - (1 << h);
			if (pn[i] < 0) pn[i] += n;
		}

		for (int i = 0; i < n; i++) {
			c[i] = 0;
		}

		for (int i = 0; i < n; i++) {
			c[classes[curK][pn[i]]]++;
		}

		for (int i = 1; i < cntClass; i++) {
			c[i] += c[i - 1];
		}
		for (int i = n - 1; i >= 0; i--) {
			c[classes[curK][pn[i]]]--;
			p[c[classes[curK][pn[i]]]] = pn[i];
		}
		cn[p[0]] = 0;
		cntClass = 1;
		for (int i = 1; i < n; i++) {
			int m1 = (p[i] + (1 << h)) % n;
			int m2 = (p[i - 1] + (1 << h)) % n;
			if (classes[curK][p[i]] != classes[curK][p[i - 1]] || classes[curK][m1] != classes[curK][m2]) {
				cntClass++;
			}
			cn[p[i]] = cntClass - 1;
		}
		curK++;
		for (int i = 0; i < n; i++) {
			classes[curK][i] = cn[i];
		}
	}
}

int get_lcp(int i, int j) {
	int ans = 0;
	for (int k = logN; k >= 0; k--) {
		if (classes[k][i] == classes[k][j]) {
			ans += (1 << k);
			i += (1 << k);
			j += (1 << k);
		}
	}
	return ans;
}

void init() {
	for (int i = 0; i < M; i++) {
		c[i] = 0;
	}
	for (int i = 0; i < n; i++) {
		c[arr[i]]++;
	}
	for (int i = 1; i < M; i++) {
		c[i] += c[i - 1];
	}
	for (int i = 0; i < n; i++) {
		c[arr[i]]--;
		p[c[arr[i]]] = i;
	}
	classes[curK][p[0]] = 0;
	cntClass = 1;
	for (int i = 1; i < n; i++) {
		if (arr[p[i]] != arr[p[i - 1]]) cntClass++;
		classes[curK][p[i]] = cntClass - 1;
	}
}

void refrain() {
	int maxH = n - 1;
	int maxW = 1;
	int start = 0;
	vector< pair<int, pair<long long, int> > > refrain;

	for (int i = 0; i <= n; i++) {
		long long x = 1;
		int lcp = 0;
		if (i > 1 && i < n) lcp = get_lcp(p[i], p[i - 1]);		
		while (!refrain.empty() && lcp <= refrain.back().second.second) {
			int id = refrain.back().first;
			int w = refrain.back().second.first;
			int h = refrain.back().second.second;
			refrain.pop_back();	
			x += (long long)w;
//			cout << "POP " << id << " " << x << " " << h << endl;
			if ((long long)h * x > (long long)maxW * (long long)maxH) {
				maxW = x;
				maxH = h;
				start = p[id];	
			}
		}
		if (refrain.empty() || lcp > refrain.back().second.second) {
//  			cout << "PUSH " << i << " " << x << " " << lcp << endl;
			refrain.push_back(make_pair(i, make_pair(x, lcp)));
		}
	}
	cout << (long long)maxH * (long long)maxW << endl;
	cout << maxH << endl;
	for (int i = start; i < start + maxH; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
}

int main() {
	freopen("refrain.in", "r", stdin);
	freopen("refrain.out", "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	arr[n] = 0;
	n++;
	init();
	buildSuf();
	refrain();
	return 0;
}
