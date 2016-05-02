#include <bits/stdc++.h>

using namespace std;

int cnt[50];
int main() {
	string s;
	int k;
	cin >> k;
	cin >> s;
	int n = (int)s.size();
	for (auto c: s) {
		cnt[c - 'a']++;
	}
	if (n % k != 0) {
		cout << -1 << endl;
		return 0;
	}
	string res = "";
	for (int i = 0; i < 50; i++) {
		if (cnt[i] % k != 0) {
			cout << -1 << endl;
			return 0;
		}
		for (int j = 0; j < cnt[i] / k; j++) {
			res += (char)('a' + i);	
		}
	}
	for (int i = 0; i < k; i++) cout << res;
	cout << endl;

}