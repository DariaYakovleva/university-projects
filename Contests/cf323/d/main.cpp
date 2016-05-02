#include <bits/stdc++.h>

using namespace std;

const int N = 110;
int n, m;
int arr[N];
int cnt[N * 5];

pair<int, int> d[N];


void go() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[j] <= arr[i] && d[j].first + 1 > d[i].first) {
				d[i].first = d[j].first + 1;
				d[i].second = d[j].second;
			}
		}
	}
}

int pref(int pos, int cnt_block) {
	
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < 500; i++) {
		cnt[i] = 0;
	}
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		cnt[arr[i]]++;
		d[i].first = 1;
		d[i].second = i;
	}
	go();
	int res = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= n; j++) {
			for (int k = 0; k <= n; k++) {
				res = max(res, pref(i, j) + suf(i, k) + cnt[arr[i]] * (m - j - k)); 
			} 
		}		
	}
	cout << res << endl;

}