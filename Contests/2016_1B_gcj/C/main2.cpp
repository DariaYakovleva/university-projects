#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <algorithm>
#include <set>
#include <map>
using namespace std;

map<string, int> first;
map<string, int> second;
vector< pair<string, string> > arr;

int main() {

	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {		
		arr.clear();
		int n;
		cin >> n;
		for (int i = 0; i < n; i++) {
			string a, b;
			cin >> a >> b;
			arr.push_back({a, b});			
		}
		int res = 0;
		for (long long mask = 0; mask < (1 << n); mask++) {
			first.clear();
			second.clear();			
			int cnt = 0;
			for (long long i = 0; i < n; i++) {
				if ((mask & (1 << i)) > 0) {
					first[arr[i].first]++;
					second[arr[i].second]++;	
				} else cnt++;
			}
			for (long long i = 0; i < n; i++) {
				if ((mask & (1 << i)) == 0) {
					if (first[arr[i].first] == 0 || second[arr[i].second] == 0) {
						cnt = -1;
					}
			   	}
			}
			if (cnt > 0) res = max(res, cnt);
		}
		cout << "Case #" << tt + 1 << ": " << res << endl;			
		cerr << tt << endl;
	}

	return 0;
}
