#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int k;
int n;
int m;
int a;
int b;

void solve() {
	for (int mask = 0; mask < 1 << (n + m); mask++) {
		int cur = -1;
		int cnt = 0;
		int cntA = 0;
		int cntB = 0;
		bool good = true;
		for (int j = 0; j < n + m; j++) {
			if (((1 << j) & mask) > 0) {
				cntB++;
				if (cur == 2) {
					cnt++;
					if (cnt > b) {
						good = false;
						break;
					}
				} else {
					cur = 2;
					cnt = 1;
				}
			} else {
				cntA++;
				if (cur == 1) {
					cnt++;
					if (cnt > a) {
						good = false;
						break;
					}
				} else {
					cur = 1;
					cnt = 1;
				}

			}	
		}
		if (cntA != n || cntB != m) {
			good = false;
		}
		if (good) {
			cout << "YES" << endl;
			if (k == 0) return;
			for (int j = 0; j < n + m; j++) {
				if (((1 << j) & mask) > 0) {
		    		cout << "B";
		  	    } else {
		    		cout << "G";
		        }
		    }
		    cout << endl;
		    return;
		}
	
	}
	cout << "NO" << endl;

}

int main() {
	freopen("registration.in", "r", stdin);
	freopen("registration.out", "w", stdout);
	cin >> k;
	cin >> n >> m >> a >> b;
	solve();


	return 0;
}