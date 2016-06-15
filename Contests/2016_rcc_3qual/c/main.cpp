#include <iostream>
#include <cstdio>
#include <math.h>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main() {
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		int n, k;
		cin >> n >> k;
		vector< pair<int, int> > res;
		int w = 3000;
		int h = 2;
		res.push_back({2, 1500});
		res.push_back({3000, 1500});
		int cur = -1;
		for (int i = 0; i < n - 1; i++) {
			if (i % 4 == 0) {
				cur++;
				if (k < cur) { 
					res.push_back({w, 1500 + 2 * k - 1});
					break;
				}
				res.push_back({w, 1500 + h});
				w -= 1;
				k -= cur;
			}
			if (i % 4 == 1) {
				res.push_back({3000 - w + 2, 1500 + h});
			}
			if (i % 4 == 2) {
				cur++;
				if (k < cur) { 
					res.push_back({3000 - w + 2, 1500 - (2 * k - 1)});
					break;
				}
				res.push_back({3000 - w + 2, 1500 - 2 * h});
				k -= cur;
			}
			if (i % 4 == 3) {
				res.push_back({w, 1500 - 2 * h});
				h += 2;
			}
		}
		for (int i = res.size() - 1; i >= 0; i--) {
			cout << res[i].first << " " << res[i].second << endl;
		}
//		cout << "other" <<endl;
		int x = 2;
		int y = 1500 - 2 * (h + 1);
//		cout << n << " " << res.size() << endl;
		for (int i = 0; i < n - ((int)res.size() - 1); i++) {
			if (i == 0) {
				cout << x << " "  << y << endl;
				continue;
			}
			if (i % 4 == 1 || i % 4 == 3) {
				x++;
			}
			if (i % 4 == 2) {
				y--;	     	
			}
			if (i % 4 == 0) {
				y++;
			}

			cout << x << " " << y << endl;
		}
//		cout << "end" << endl;      	   	
	}


	return 0;
}