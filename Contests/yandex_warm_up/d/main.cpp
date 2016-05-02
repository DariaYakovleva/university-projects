#include <bits/stdc++.h>

using namespace std;

const int N = 100010;
set<long long> ss;
map<long long, long long> mm;
int cnt = 0;
long long maxi = 0;
int main() {
//	freopen("input.in", "r", stdin);
//	freopen("output.out", "w", stdout);
	int a;
	while (cin >> a) {
		cnt++;
		if (ss.count(a) == 0) {
			ss.insert(a);
			mm[a] = 1;
		} else {
			mm[a]++;
			maxi = max(maxi, mm[a]);
		}
	}
	if (maxi < cnt / 2) {
		maxi = max((int)ss.size() - 1, 0);	
	}  else {
		maxi = cnt - maxi;
	}
	cout << max((int)ss.size() - 1, 0) << " " << maxi  << "  " << endl;

	return 0;
}