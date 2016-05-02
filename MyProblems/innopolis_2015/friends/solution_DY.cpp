#include <bits/stdc++.h>

using namespace std;

const int N = 1000010;

pair<int, int> arr[N];
int a, b;
int n;

bool cmp(pair<int, int> x, pair<int, int> y) {
	return x.first < y.first;
}

int main() {	
	freopen("friends.in", "r", stdin);
	freopen("friends.out", "w", stdout);
	scanf("%d %d %d", &n, &a, &b);
	for (int i = 0; i < a; i++) {
		int x;
		scanf("%d", &x);
		arr[i].first = x;
		arr[i].second = 1;
	}
	for (int i = a; i < a + b; i++) {
		int x;
		scanf("%d", &x);
		arr[i].first = x;
		arr[i].second = 2;
	}
	int m = a + b;
	sort(arr, arr + m, cmp);
	int mina = 0;
	int minb = 0;
	int cntb = 0;
	int cnta = 0;
	for (int i = 0; i < m; i++) {
		if (arr[i].second == 1) {
			if (cntb == 0) {
				mina++;
			} else {
				cntb--;
			}
			cnta++;
		} else {
			if (cnta == 0) {
				minb++;
			} else {
				cnta--;
			}
			cntb++;
		}	
	}
	int maxa = a;
	int maxb = b;
	int alla = a + b - n;
	int allb = a + b - n;
	cnta = a - alla;
	cntb = b - allb;
	for (int i = 0; i < m; i++) {
		if (arr[i].second == 1) {
			if (alla > 0) {
				alla--;
				cntb++;
			}
			if (cnta > 0) {
				cnta--;
			} else {
				maxb--;
				allb--;
			}

		} else {
			if (cntb > 0) {
				cntb--;
			} else {
				maxa--;
				alla--;
			}
			if (allb > 0) {
				allb--;
				cnta++;
			}
		}	
	}
	if (a + b - n <= 0) {
		maxa = a;
		maxb = b;
	}
	cout << mina << " " << maxa << endl;
	cout << minb << " " << maxb << endl;
	
}