#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 2000010;

string p, t;
int z1[N], z2[N];

void z_fun(string s, int* z) {
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
	freopen("search3.in", "r", stdin);
	freopen("search3.out", "w", stdout);
	cin >> p;
	cin >> t;
	vector<int> ans;
	string ss1 = p + "#" + t;
	reverse(p.begin(), p.end());
	reverse(t.begin(), t.end());
	string ss2 = p + "#" + t;
	z_fun(ss1, z1);
	z_fun(ss2, z2);
	for (int i = (int)p.size() + 1; i + (int)p.size() <= (int)ss1.size(); i++) {
		if (z1[i] + z2[(int)ss1.size() - i + 1] >= (int)p.size() - 1) ans.push_back(i);	
	}
	cout << ans.size() << endl;
	for (int i = 0; i < (int)ans.size(); i++) {
		cout << ans[i] - (int)p.size()  <<  " ";
	}

	return 0;
}