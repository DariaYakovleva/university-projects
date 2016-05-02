#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>


using namespace std;

const int N = 110;
pair<int, int> arr[N];
int n;
int w;
long long maxi = 0;
int mas = 0;


int main() {
	freopen("knapsack.in", "r", stdin);
	freopen("knapsack.out", "w", stdout);
	cin >> n >> w;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].first >> arr[i].second;
	}
	for (int mask = 0; mask < (1 << n); mask++) {
		int ww = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if ((1 << i) & mask) {
				ww += arr[i].first;
				sum += arr[i].second;
			}
		}
		if (ww <= w && sum > maxi) {
			maxi = sum;
			mas = mask;
		}
	}
	vector<int> ans;
	for (int i = 0; i < n; i++) {
		if ((1 << i) & mas) {
			ans.push_back(i + 1);
		}
	}
	cout << ans.size()  << " " << maxi << endl;
	for (int x: ans) {
		cout << x << " " ;
	}

	
	return 0;
}