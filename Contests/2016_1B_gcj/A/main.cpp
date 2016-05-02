#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;

string digits[] = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
char nums[] = {'Z', 'W', 'X', 'G', 'U', 'O', 'T', 'F', 'S', 'E'};
int numbers[] = {0, 2, 6, 8, 4, 1, 3, 5, 7, 9};
int cnt[30];
int main() {

	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		string s;
		cin >> s;
		for (int i = 0; i < 30; i++) cnt[i] = 0;
		for (char c: s) {
			cnt[c - 'A']++;
		}
		string res = "";
		for (int i = 0; i < 10; i++) {
			int k = cnt[nums[i] - 'A'];
//			cout << k << endl;
			for (int j = 0; j < k; j++) {
				res += (char)(numbers[i] + '0');
			}		
			for (char c: digits[numbers[i]]) {
				cnt[c - 'A'] -= k;
			}
		}
		sort(res.begin(), res.end());
		cout << "Case #" << tt + 1 << ": " << res << endl;			
	}

	return 0;
}
