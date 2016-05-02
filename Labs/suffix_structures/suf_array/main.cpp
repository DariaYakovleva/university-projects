#include <iostream>
#include <cstdio>
#include <iostream>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int M = 30;
const int N = 400010;

string s;
int n;
int c[N];
int p[N];
int pn[N];
int cn[N];
int classes[16][N];
int cntClass = 0;
int curK = 0;
int logN = 0;

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
		c[s[i] - 'a' + 1]++;
	}
	for (int i = 1; i < M; i++) {
		c[i] += c[i - 1];
	}
	for (int i = 0; i < n; i++) {
		c[s[i] - 'a' + 1]--;
		p[c[s[i] - 'a' + 1]] = i;
	}
	classes[curK][p[0]] = 0;
	cntClass = 1;
	for (int i = 1; i < n; i++) {
		if (s[p[i]] != s[p[i - 1]]) cntClass++;
		classes[curK][p[i]] = cntClass - 1;
	}
}

int cntSub() {
	int res = 0;
	for (int i = 0; i < n; i++) {
		res += n - p[i] - 1;
		cout << i << " " << i + 1 << " "<< get_lcp(i, i + 1) << endl;
		if (i < n - 1) res -= get_lcp(i, i + 1);	
	}
	return res;
}

int main() {
	freopen("array.in", "r", stdin);
	freopen("array.out", "w", stdout);
	getline(cin, s);
	s += (char)('a' - 1);
	n = (int)s.size();
	init();
	buildSuf();
	cout << cntSub() << endl;
	return 0;
}
