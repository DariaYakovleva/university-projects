#include <bits/stdc++.h>

using namespace std;
const int N = 1010;
int arr[N];
bool used[N];
int n;
int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		used[i] = false;
	}
	int cnt = 0;
	int turn = 0;
	while (cnt < n) {
		for (int i = 0; i < n; i++) {
			if (!used[i] && arr[i] <= cnt) {
				used[i] = true;
				cnt++;
			}
		}
		if (cnt >= n) break;
		turn++;
		for (int i = n - 1; i >= 0; i--) {
			if (!used[i] && arr[i] <= cnt) {
				used[i] = true;
				cnt++;
			}
		}
		if (cnt >= n) break;
		turn++;
	}
	cout << turn << endl;
	
}