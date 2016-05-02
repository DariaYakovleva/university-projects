#include <bits/stdc++.h>

using namespace std;

int main() { 
	int n;
	cin >> n;
	string s;
	cin >> s;
	int ans = 0;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j <= n; j++) {
			int x = 0;
			int y = 0;
			for (int k = i; k < j; k++) {
				if (s[k] == 'U') y--;
				if (s[k] == 'D') y++;
				if (s[k] == 'R') x++;
				if (s[k] == 'L') x--;	
			} 
			if (x == 0 && y == 0) ans++;
		}
	}
	cout << ans << endl;

	return 0;
}