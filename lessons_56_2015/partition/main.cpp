#include <bits/stdc++.h>

using namespace std;
const int N = 100;
int arr[N];

void go(int pos, int ost, int prev) {
	if (ost == 0) {
		for (int i = 0; i < pos; i++) cout << arr[i] << " ";
		cout << endl;
		return;
	}
	for (int cur = prev; cur <= ost; cur++) {
		if (ost - cur == 0 || 2 * cur <= ost) {
			arr[pos] = cur;
			go(pos + 1, ost - cur, cur);
		}
	}
}

int main() {
	int n;
	cin >> n;
	go(0, n, 1);

}