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
		first.clear();
		second.clear();
		arr.clear();
		int n;
		cin >> n;
		for (int i = 0; i < n; i++) {
			string a, b;
			cin >> a >> b;
			arr.push_back({a, b});
			first[a]++;
			second[b]++;
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			if (first[arr[i].first] > 1 && second[arr[i].second] > 1) {
				res++;
				first[arr[i].first]--;
				second[arr[i].second]--;
			}
		}
		cout << "Case #" << tt + 1 << ": " << res << endl;			
	}

	return 0;
}
