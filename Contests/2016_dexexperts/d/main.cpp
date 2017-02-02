#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

string s, t, ss;
const int N = 300010;
int zf[N];

void init() {
	for (int i = 0; i < N; i++) {
		zf[i] = 0;
	}
}

void z_fun() {
	int l = 0;
	int r = 0;
	int len = (int)ss.size();
	for (int i = 1; i < len; i++) {
		if (i <= r) {
			zf[i] = min(zf[i - l], r - i + 1);
		}
		while (i + zf[i] < len && ss[i + zf[i]] == ss[zf[i]]) {
			zf[i]++;
		}
		if (i + zf[i] - 1 > r) {
			l = i;
			r = i + zf[i] - 1;
		}
	}
}

int main() {
	getline(cin, t);
	getline(cin, s);
	ss = t + "#" + s;
	init();
	z_fun();
//	for (int i = 0; i < ss.size(); i++) {
//		cout << zf[i] << " " ;
//	}
//	cout << endl;
	vector<string> ans;
	int last = ss.size();
	bool ok = true;
	for (int i = (int)ss.size() - 1; i > (int)t.size(); i--) {
		if (zf[i] == 0) continue;
		if (i + zf[i] >= last) {
			string next = "";
			for (int j = i; j < last; j++) {
				next += ss[j];
			}
			ans.push_back(next);
			last = i;		
		}
	} 
	if (last != (int)t.size() + 1) ok = false;
	if (ok) {
		cout << "Yes" << endl;
		for (int i = (int)ans.size() - 1; i >= 0; i--) {
			cout << ans[i] << " ";
		}
		cout << endl;
	} else {
		cout << "No" << endl;
	}

	return 0;
}       