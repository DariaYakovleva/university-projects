#include <bits/stdc++.h>

using namespace std;

const int N = 10010;


int n;
pair<int, int> arr[N];
int ans[N];
int countt[N];
int k;	

int lbs(int x) {
	int l = -1;
	int r = n;
	while (r - l > 1) {
		int m = (l + r) / 2;
		if (x > arr[m].first) {
			l = m;
		} else {
			r = m;
		}
	}
	return r;
}

int rbs(int x) {
	int l = -1;
	int r = n;
	while (r - l > 1) {
		int m = (l + r) / 2;
		if (x >= arr[m].first) {
			l = m;
		} else {
			r = m;
		}
	}
	return l;
}

int cnt(int x) {
	int pos = lbs(x);
	if (pos == n || arr[pos].first != x) return 0;
	return countt[pos];
}


int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].first;
		arr[i].second = i;
	}
	sort(arr, arr + n);
	for (int i = 0; i < n; i++) {
		int x = arr[i].first;
		countt[i] = rbs(x) - lbs(x) + 1;	
	}
//	for (int i = 0; i < n; i++) {
//		cout << arr[i].first << " " << rbs(arr[i].first) << " " << lbs(arr[i].first) << endl;
//	}
	for (int i = 0; i < n; i++) {
		int res = 0;
		int j = 0;
		while (j < i && arr[j].first * 2 < arr[i].first) {
			int cnta = cnt(arr[j].first);
			int cntb = cnt(arr[i].first - arr[j].first);
			res += cnta * cntb;
			j += cnta;
		}
		if (arr[i].first % 2 == 0) {
			int cnta =  cnt(arr[i].first / 2);
			res += cnta * (cnta - 1) / 2;
		}
		ans[arr[i].second] = res;
	}
	for (int i = 0; i < n; i++) {
		cout << ans[i] << endl;	
	}

	return 0;
}