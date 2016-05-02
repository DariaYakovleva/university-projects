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
int nn;
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

bool cmp(my a, my b) {
	return (a.x > b.x) || (a.x == b.x && a.p < b.p);
}

int gcd(int a, int b) {
	if (b == 0) {
		return a;
	}
	return gcd(b, a % b);
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) {
		cin >> b;
		cin >> nn;
		priority_queue<pair< long long, int> > q;
		int g = 1;
		long long nok = 1;
		for (int i = 0; i < b; i++) {
			cin >> arr[i];
			if (i == 0) {
				g = arr[i];
			} else {
				g = gcd(nok, arr[i]);
				nok = nok * arr[i] / g;
			}
			q.push(make_pair(-arr[i], -i));
		}
		int cnt = 0;
		for (int i = 0; i < b; i++) {
			cnt += nok / arr[i];
		}
		int n = nn % cnt;
		if (n == 0) n = cnt - 1;
		for (int i = 0; i < n - 1; i++) {
			pair<long long, int> pp = q.top();
			pp.first = pp.first - arr[pp.second];
			q.pop();
			q.push(pp);
		}
		int ans = -q.top().second;
		cout << "Case #" << t + 1 << ": " << ans + 1 << endl;
	}
	return 0;
}