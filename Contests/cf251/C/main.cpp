#include <iostream>                                                                          
#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

const int N = 100010;
int n, k, p, odd = 0, arr[N];
vector <int> group[N];
int main() {
    cin >> n >> k >> p;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		if (arr[i] % 2 == 1) odd++;
	}
	int pos = 0;
	if ((odd >= (k - p)) && ((odd - k + p) % 2 == 0) && (n - odd + (odd - k + p) / 2 >= p)) {
		cout << "YES" << endl;
		for (int i = 0; i < n; i++) {
			if (arr[i] % 2 == 1) {
				if (pos < k - p) {
					group[pos].push_back(arr[i]);
					if (pos + 1 < k) pos++;
				} else {
					group[pos].push_back(arr[i]);
					if (group[pos].size() == 2 && pos + 1 < k) pos++;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (arr[i] % 2 == 0) {
				group[pos].push_back(arr[i]);
				if (pos + 1 < k) pos++;	
			}
		}
		for (int i = 0; i < k; i++) {
			cout << group[i].size() << " ";
			for (int j = 0; j < group[i].size(); j++) {
				cout << group[i][j] << " ";
			}
			cout << endl;
		} 
			
	} else {
		cout << "NO" << endl;
	}
	return 0;
}