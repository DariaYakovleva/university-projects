#include <bits/stdc++.h>
 
using namespace std;

const int N = 210;
int num[N];
bool have1[N];
bool have2[N];
bool used[N];
int n;

vector<pair<int, int>> arr;

void init() {
	for (int i = 0; i < 2 * n; i++) {
		have2[i] = false;
		have1[i] = false;
		used[i] = false;
	}
}

int main() {
	cin >> n;
	for (int i = 0; i < 2 * n; i++) {
	 	int a;
	 	cin >> a;
	 	arr.push_back({a, i});
	}
	init();
	sort(arr.begin(), arr.end());
	int prev = 0;
	int cnt1 = 0;
	int cnt2 = 0;
	for (int i = 0; i < 2 * n; i++) {
		if ((i == 0 || (arr[i].first != arr[i - 1].first)) && ((i == 2 * n - 1 || (arr[i].first != arr[i + 1].first)))) {
			num[arr[i].second] = prev;
			if (!prev) cnt1++;
			else cnt2++;
			prev ^= 1;
			used[i] = true;
		}	
	}
	prev = 0;
	int i = 0;
	while (i < 2 * n) {
		if (!used[i]) {
			int cur = arr[i].first;
			num[arr[i].second] = prev;
			prev ^= 1;
			i++;
			num[arr[i].second] = prev;
			prev ^= 1;
			i++;
			while (i < 2 * n && arr[i].first == cur) {
				if (cnt1 < cnt2) {
					num[arr[i].second] = 0;
					cnt1++;
				} else {
					num[arr[i].second] = 1;
					cnt2++;
				}
				i++;
			}
		} else {
			i++;
		}
	}
	cnt1 = 0;
	cnt2 = 0;
	for (int i = 0; i < 2 * n; i ++) {
		if (num[arr[i].second] == 0) {
			if (!have1[arr[i].first]) {
				cnt1++;
				have1[arr[i].first] = 1;
			}
		} else {
			if (!have2[arr[i].first]) {
				cnt2++;
				have2[arr[i].first] = 1;
			}

		}
	}
	cout << cnt1 * cnt2 << endl;
 	for (int i = 0; i < 2 * n; i++) {
 		cout << num[i] + 1 << " " ;
 	}	
 	cout << endl;


	return 0;
}