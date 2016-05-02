#include <bits/stdc++.h>

using namespace std;

const int N = 100010;
vector<int> g[N];
vector< pair<int, int> > weigth;
int n;
bool used[N];
int math[N];
int rmath[N];

bool dfs(int v) {
	if (used[v]) {
		return false;
	}
	used[v] = true;
	for (int to : g[v]) {
		if (math[to] == -1 || dfs(math[to])) {
			math[to] = v;
			return true;
		}
	}
	return false;
}

int main() {
	freopen("matching.in", "r", stdin);
	freopen("matching.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
		int w;
		cin >> w;
		weigth.push_back(make_pair(w, i));
		rmath[i] = -1;
		math[i] = -1;
	}
	sort(weigth.begin(), weigth.end(), [](const pair<int, int> &a, const pair<int, int> &b) { return a.first > b.first; });
	for (int i = 0; i < n; i++) {
		int k;
		cin >> k;
		for (int j = 0; j < k; j++) {
			int a;
			cin >> a;
			a--;
			g[i].push_back(a);
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			used[j] = false;
		}
		dfs(weigth[i].second);
	}
	for (int i = 0; i < n; i++) {
		if (math[i] != -1) rmath[math[i]] = i;
	}
	for (int i = 0; i < n; i++) {
		cout << rmath[i]  + 1 << " ";
	}
	cout << endl;

}