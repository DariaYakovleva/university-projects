#include <iostream>
#include <cstdio>
#include <vector>
#include <cmath>
#include <algorithm>
#include <string>
#include <random>

using namespace std;

const int N = 110;
int n;
string arr[N];
bool have[N];

const int MOD = 1000000007;

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	int t;
	cin >> t;
	for (int ii = 0; ii < t; ii++) {
		cout << "Case #" << ii + 1 << ": "; 
		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> arr[i];
		}
		string ans = "";
		for (int i = 0; i < 26; i++) {
			ans += (char)('A' + i);
		}
		bool gok = false;
		random_device rd;
		for (int i = 0; i < 10000; i++) {
    		mt19937 g(rd()); 
    		shuffle(ans.begin(), ans.end(), g);
    		bool ok = true;
    		for (int k = 0; k < n; k++) {
    			if (ans.rfind(arr[k]) != std::string::npos) {
    				ok = false;
    				break;
       			}
    		}
    		if (ok) {
    			cout << ans << endl;
    			gok = true;
    			break;
    		}
		}
		if (!gok) cout << "IMPOSSIBLE" << endl;
	}

	return 0;
}
