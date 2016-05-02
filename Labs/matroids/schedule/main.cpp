#include <bits/stdc++.h>

using namespace std;

const int N = 100010;

pair<int, int> arr[N];
int rankd[N];
int num[N];
int parent[N];

void init() {
	for (int i = 0; i < N; i++) {
		rankd[i] = 0;
		num[i] = i;
		parent[i] = i;
	}
}

int get(int x) {
	if (x == parent[x]) return x;
	return parent[x] = get(parent[x]);
}

int get_num(int x) {
	return num[get(x)];
}

void union_dsu(int x, int y) {
    x = get(x);
    y = get(y);
	if (x == y) {
		return;
	}
	num[y] = num[x];
	if (rankd[x] < rankd[y]) {
		swap(x, y);
	}
	if (rankd[x] == rankd[y]) {
		rankd[x]++;
	}
	parent[y] = x;
}

bool cmp(const pair<int, int> &a, const pair<int, int> &b) {
	return (a.second > b.second) || (a.second == b.second && a.first < b.first);
}

int main() {
	freopen("schedule.in", "r", stdin);
	freopen("schedule.out", "w", stdout);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].first >> arr[i].second;
		arr[i].first = min(arr[i].first, n);
	}
	init();
	sort(arr, arr + n, cmp);
	long long res = 0;
	for (int i = 0; i < n; i++) {
		int pos = get_num(arr[i].first);
		if (pos > 0) {
			union_dsu(pos - 1, pos);
		} else {
			res += (long long)arr[i].second;
		}
	}
	cout << res << endl;


}
