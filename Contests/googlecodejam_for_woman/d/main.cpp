#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
#include <string>

using namespace std;

const int N = 110;
int n;
string arr[N];
bool have[N];
set<int> prev[N];
set<int> next[N];

const int MOD = 1000000007;

void init() {
	for (int i = 0; i < N; i++) {
		have[i] = false;
		prev[i].clear();
		next[i].clear();
	}
}

bool have_solve() {
	for (int i = 0; i < n; i++) {
		if (arr[i].size() == 1) return false;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < arr[i].size(); j++) {
			int cur = arr[i][j] - 'A';
			if (j < arr[i].size() - 1) {
				next[cur].insert(arr[i][j + 1] - 'A');
			}
			if (j > 0) {
				prev[cur].insert(arr[i][j - 1] - 'A');
			}
		}
	}
	int cntn = 0;
	int cntp = 0;
	for (int i = 0; i < 26; i++) {
		if (next[i].size() == 26) cntn++;
		if (prev[i].size() == 26) cntp++;
	}
	if (cntn > 1) return false;
	if (cntp > 1) return false;
	return true;
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int t;
	cin >> t;
	for (int ii = 0; ii < t; ii++) {
		cout << "Case #" << ii + 1 << ": "; 
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> arr[i];
		}
		init();
		string ans = "";
		for (int i = 0; i < 26; i++) {
			have[i] = true;
		}
		for (size_t i = 0; i < arr[0].size(); i++) {
			if (i > 0 && have[arr[0][i] - 'A']) {
				ans += arr[0][i];
				have[arr[0][i] - 'A'] = false;
			}
		}
		if (have[arr[0][0] - 'A']) {
			ans += arr[0][0];
			have[arr[0][0] - 'A'] = false;
		}
		for (int i = 0; i < 26; i++) {
			if (have[i]) {
				ans += (char)(i + 'A');
			}
		}
		if (arr[0].size() == 1) {
			cout << "IMPOSSIBLE" << endl;
		} else {
			cout << ans << endl;
		}
	}

	return 0;
}
