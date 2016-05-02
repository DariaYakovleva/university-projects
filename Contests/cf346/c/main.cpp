#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <algorithm>
#include <set>
#include <string>

using namespace std;

const int N = 100010;
int arr[N];
int n, m;
vector<int> ans;

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}	
	arr[n] = -1;
	sort(arr, arr + n);
	int cur = 1;
	int pos = 0;
	int sum = 0;
	while (sum + cur <= m) {
		if (cur == arr[pos]) {
			pos++;
			cur++;
			continue;
		}
		sum += cur;
		ans.push_back(cur);
		cur++;
	}
	cout << ans.size() << endl;
	for (int x: ans) {
		cout << x << " ";
	}


	return 0;
}