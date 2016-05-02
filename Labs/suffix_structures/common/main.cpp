#include <iostream>
#include <cstdio>
#include <iostream>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int M = 50;
const int N = 200100;

string s;
int n;
int c[N];
int p[N];
int pn[N];
int cn[N];
int classes[M][N];
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
		c[s[i] - '#']++;
	}
	for (int i = 1; i < N; i++) {
		c[i] += c[i - 1];
	}
	for (int i = 0; i < n; i++) {
		c[s[i] - '#']--;
		p[c[s[i] - '#']] = i;
	}
	classes[curK][p[0]] = 0;
	cntClass = 1;
	for (int i = 1; i < n; i++) {
		if (s[p[i]] != s[p[i - 1]]) cntClass++;
		classes[curK][p[i]] = cntClass - 1;
	}
}
void commonSub() {
	int maxLen = 0;
	int start = -1;
	for (int right = 0; right < n; right++) {
		if (p[right] > m) {
			if (right > 0 && p[right - 1] < m) {
				int lcp = get_lcp(p[right], p[right - 1]);
				if (lcp > maxLen) {
//					cout << p[right] <<  " " << p[right - 1] << " " << lcp << endl;
					maxLen = lcp;
					start = p[right];
				}
			} 
			if (right < n - 1 && p[right + 1] < m) {
				int lcp = get_lcp(p[right], p[right + 1]);
				if (lcp > maxLen) {
//					cout << p[right] <<  " " << p[right + 1] << " " << lcp << endl;
					maxLen = lcp;
					start = p[right];
				}
			}
		}
	}
	if (start == -1) {
		return;
	}
	for (int i = start; i < min(n, start + maxLen); i++) {
		cout << s[i];
	}
	cout << endl;
}

int main() {
	freopen("common.in", "r", stdin);
	freopen("common.out", "w", stdout);
	cin >> s;
//	getline(cin, s);
	m = (int)s.size();
	s += "$";
	string ss;
	cin >> ss;
//	getline(cin, ss);
	s += ss;
	s += "#";
	n = (int)s.size();
	init();
	buildSuf();
	commonSub();
	return 0;
}
