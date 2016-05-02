#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
#include <string>

using namespace std;

int arr[110];

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int n;
	int t;
	cin >> t;
	for (int k = 0; k < t; k++) {
		cout << "Case #" << k + 1 << ": "; 
		cin >> n;
		for (int i = 0; i < 2 * n; i++) {
			cin >> arr[i];
		}
	//	sort(arr, arr + 2 * n);
		vector<int> res;
		res.clear();
		for (int i = 0; i < 2 * n; i++) {
			if (res.size() == n) break;
			for (int j = 0; j < 2 * n; j++) {
				if (i == j) continue;
				if (arr[i] == arr[j] / 4 * 3) {
					res.push_back(arr[i]);
					arr[j] = -1;
					arr[i] = -1;
				}
			}	
		}
		for (int i = 0; i < res.size(); i++) {
			cout << res[i] << " ";
		}
		cout << endl;
	}


	return 0;
}