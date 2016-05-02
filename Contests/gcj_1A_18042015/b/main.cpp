#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cmath>
#include <vector>
#include <set>
#include <queue>


using namespace std;

int arr[1100];
int b;
int n;
const long long N = 1000000010;

struct my {
	int x;
	int p;
	my(){}
	my(int a, int b) {
		x = a;
		p = b;
	}
};
my arr2[1100];

long long how(long long t) {
	long long res = 0;
	for (int i = 0; i < b; i++) {
		res += t / arr[i];
	}
	return res;
}

long long bs(int nn) {
	long long l = 0;
	long long r = N * nn;
	while (r - l > 1) {
		long long m = (r + l) / 2;
		if (how(m) < nn) {
			l = m;
		} else {
			r = m;
		}
	}
	return r;
}

bool cmp(my a, my b) {
	return (a.x > b.x) || (a.x == b.x && a.p < b.p);
}
int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		cin >> b;
		cin >> n;
		for (int i = 0; i < b; i++) {
			cin >> arr[i];
		}
		long long tt = bs(n - 1);
		int cnt = 0;
		int cur = n - 1;
		for (int i = 0; i < b; i++) {
			cnt += tt / arr[i]; 
		}
		while (cnt >= n) {
			cur--;
			tt = bs(cur);
			cnt = 0;
			for (int i = 0; i < b; i++) {
				cnt += tt / arr[i]; 
			}
		}	
		priority_queue<pair< long long, int> > q;
		for (int i = 0; i < b; i++) {
			q.push(make_pair(-arr[i] * (tt / arr[i]), -i));
		}
		cout << tt << " " << cnt << endl;
		for (int i = cnt; i < n - 1; i++) {
			pair<long long, int> pp = q.top();
			pp.first = pp.first + arr[pp.second];
			q.pop();
			q.push(pp);
		}
		int ans = -q.top().second;

		cout << "Case #" << t + 1 << ": " << ans + 1 << endl;
	}
	return 0;
}