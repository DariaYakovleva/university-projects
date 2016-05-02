#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 2000010;

string p, t;
int z[N];

void z_fun(string s) {
	int l = 0;
	int r = 0;
	z[0] = (int)s.size();
	for (int i = 1; i < (int)s.size(); i++) {
		if (i <= r) {
			z[i] = min(z[i - l], r - i + 1); 
		}
		while (i + z[i] < (int)s.size() && s[z[i]] == s[i + z[i]]) z[i]++;
		if (i + z[i] - 1 > r) {
			l = i;
			r = i + z[i] - 1;
		}
	}
}

int main() {
	freopen("search2.in", "r", stdin);
	freopen("search2.out", "w", stdout);
	cin >> p;
	cin >> t;
	vector<int> ans;
	string ss = p + "#" + t;
	z_fun(ss);
	for (int i = 0; i < (int)ss.size(); i++) {
		if (z[i] == (int)p.size()) ans.push_back(i);	
	}
	cout << ans.size() << endl;
	for (int i = 0; i < (int)ans.size(); i++) {
		cout << ans[i] - (int)p.size()  <<  " ";
	}

	return 0;
}