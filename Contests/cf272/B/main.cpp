#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;
const int N = 100010, M = 10;

int n, m, k;
vector< vector<int> > sets;
vector<int> nused;
vector<int> group;

int gcd(int a, int b) {
	if (b == 0)
		return a;
	return gcd(b, a % b);
}

void solve() {
	int cur = 1;
	while (sets.size() < n) {
		group.clear();
		for (int i = 0; i < nused.size(); i++) if (nused[i] != -1) {
				int good = true;
				for (int j = 0; j < group.size(); j++) {
					if (!(gcd(nused[i], group[j]) == 1)) good = false;
				}
				if (good) {
					group.push_back(nused[i]);
					nused[i] = -1;	
				}
				if (group.size() == 4) break;
			}

		while (group.size() < 4) {
			int good = true;
			for (int j = 0; j < group.size(); j++) {
				if (!(gcd(cur, group[j]) == 1)) good = false;
			}
			if (good) {
				group.push_back(cur);
			} else {
			//	nused.push_back(cur);
			}
			cur++;	
		}
		sets.push_back(group);
		cur++;	
	}
}

int main() {
	
	cin >> n >> k;
	solve();
	cout << k * sets[n - 1][3] << endl;
	for (int i = 0; i < sets.size(); i++) {
		for (int j = 0; j < sets[i].size(); j++) {
			cout << sets[i][j] * k << " ";
		}
		cout << endl;
	}

	return 0;
}	