#include <bits/stdc++.h>

using namespace std;

const int N = 1010;
vector<pair<int, pair<int, int> > > arr;


int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		int pos = -1;
		for (int j = 0; j < (int)arr.size(); j++) {
			if (arr[j].first == a) pos = j;
		}
		if (pos == -1) {
			arr.push_back({a, {1, i}});
		} else {
			arr[pos].second = {arr[pos].second.first + 1, i};
		}
	}
	int pos = 0;
	for (int i = 0; i < arr.size(); i++) {
		if (arr[i].second.first > arr[pos].second.first || 
		(arr[i].second.first == arr[pos].second.first && arr[i].second.second < arr[pos].second.second)) {
			pos = i;
		}
	}
	cout << arr[pos].first << endl;

	return 0;
}
