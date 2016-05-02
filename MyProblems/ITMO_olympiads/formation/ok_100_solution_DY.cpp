#include <bits/stdc++.h>

using namespace std;

const int N = 1000010;
int arr[N];
int n;
int res = 0;
set<int> ss;

int main() {
	freopen("candies.in", "r", stdin);
	freopen("candies.out", "w", stdout);
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j <= n; j++) {
			ss.clear();
			for (int k = i; k < j; k++) {
				ss.insert(arr[k]);				
			}	
			if (ss.size() == 2) res = max(res, j - i);
		}
	}
	cout << res << endl;
	
}
