#include <bits/stdc++.h>

using namespace std;

const int N = 1000010;
int arr[N];
int n;
int res = 0;

int main() {
	freopen("formation.in", "r", stdin);
	freopen("formation.out", "w", stdout);
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}

	int cur_len = 1;
	int c1 = arr[0];
	int c2 = -1;
	int st = 0;

	for (int i = 1; i < n; i++) {
		if (arr[i] == arr[i - 1]) {
			cur_len++;
		} else {
			if (c2 == -1) {
				c2 = arr[i];
				cur_len++;
			} else if (arr[i] == c1 || arr[i] == c2) {
				cur_len++;
			} else {
				res = max(res, cur_len);
				cur_len = i - st + 1;
				c1 = arr[i - 1];
				c2 = arr[i];
			}
			st = i;
		}
//		cout << cur_len << " " << i << " " << st << endl;	
	}
	res = max(res, cur_len);
	if (c2 == -1) res = 0;
	printf("%d\n", res);
	
}
