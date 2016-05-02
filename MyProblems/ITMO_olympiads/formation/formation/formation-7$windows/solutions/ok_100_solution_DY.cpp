#include <bits/stdc++.h>

using namespace std;

const int N = 1000010;
int arr[N];
int n;
int res = 0;
vector<int> ss;

int main() {
	freopen("formation.in", "r", stdin);
	freopen("formation.out", "w", stdout);
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j <= n; j++) {
			ss.clear();
			for (int k = i; k < j; k++) {
				bool have = false;
				for (size_t h = 0; h < ss.size(); h++) if (ss[h] == arr[k]) have = true;
				if (!have) ss.push_back(arr[k]);
				
			}	
			if (ss.size() == 2) res = max(res, j - i);
		}
	}
	cout << res << endl;
	
}
