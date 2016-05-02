#include <bits/stdc++.h>

using namespace std;
const int N = 100010;

struct point {
	long long a;
	int pos;	
};

long long n, a, cf, cm, m;
point arr[N];
long long suf[N];
long long pref[N];
long long arr2[N];

bool cmp(point a, point b) {
	return a.a < b.a;
}

long long cnta = 0;
long long mmm = 0;

int main() {
	cin >> n >> a >> cf >> cm >> m;
	int ca = 0;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].a;
		if (arr[i].a == a) ca++;
		arr2[i] = arr[i].a;
		arr[i].pos = i;
	}
	cnta = ca;
	sort(arr, arr + n, cmp);
	suf[n] = 0;
	for (int i = n - 1; i >= 0; i--) {
		suf[i] = suf[i + 1] + a - arr[i].a;		
//		cout << "s=" << suf[i] << " ";
	}
	pref[0] = 0;
	for (int i = 1; i < n; i++) {
		pref[i] = pref[i - 1] + i * (arr[i].a - arr[i - 1].a);
//		cout << "p=" << pref[i] << " ";
	}
	long long res = 0;
	for (int i = ca; i <= n; i++) {
		long long cur = cf * i; 
		if (i == n) cur += cm * a;
		long long ost = m - suf[n - i];
		if (ost < 0) continue;
		int l = -1;
		int r = n - i;
		while (r - l > 1) {
			int m1 = (l + r) / 2;
			if (pref[m1] > ost) {
				r = m1;
			} else {
				l = m1;
			}
		}
		int pos = l;
//		cout << pos << endl;
		if (pos == -1) {
			if (cur > res) {
				res = cur;
				mmm = arr[0].a;
				cnta = i;
			}
			continue;
		}
		ost -= pref[pos];
		int minc = min(a, arr[pos].a + ost / (pos + 1));
		if (minc == a) cur += (pos + 1) * cf;
		cur += cm * minc;
 		if (cur > res) {
			res = cur;
			mmm = minc;
			cnta = i;
		}
	}
	for (int i = n - cnta; i < n; i++) {
		arr2[arr[i].pos] = a;
	}
	for (int i = 0; i < n; i++) 
		if (arr2[i] < mmm) arr2[i] = mmm;
	cout << res << endl;
	for (int i = 0; i < n; i++) {
		cout << arr2[i] << " ";
	}
	                        	





	return 0;
}