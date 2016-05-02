#include <bits/stdc++.h>

using namespace std;

bool used[110];
int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		used[i] = false;
	}
	int m;
	cin >> m;
	for (int i = 0; i < m; i++) {
		int a;
		cin >> a;
		used[a - 1] = true;
	}

	cin >> m;
	for (int i = 0; i < m; i++) {
		int a;
		cin >> a;
		used[a - 1] = true;
	}
	bool ok = true;
	for (int i = 0; i < n; i++) {
		if (!used[i]) ok = false;
	}
	if (!ok) cout << "Oh, my keyboard!" << endl;
	else cout << "I become the guy." << endl;

}