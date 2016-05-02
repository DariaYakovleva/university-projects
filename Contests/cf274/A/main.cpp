#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 10010;

int n;
vector< pair<int, int> > g;

bool sr(pair<int, int> a, pair<int, int> b) {
	return (a.first < b.first) || (a.first == b.first && a.second < b.second);
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a, b;                                                     
		cin >> a >> b;
		g.push_back(make_pair(a, b));
	}
	sort(g.begin(), g.end(), sr);
	int cur = -1;
	for (int i = 0; i < n; i++) {
		if (g[i].second >= cur) cur = g[i].second;
		else cur = g[i].first;
	}
	cout << cur;
    return 0;

}