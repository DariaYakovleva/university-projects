#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
#include <cstring>

using namespace std;
const int N = 110;
string s, t;
int arr[N][N], n, m;
bool nuse[N];

int lcs() {
    for (int i = 0; i <= n; i++)
    	arr[i][0] = 0;
    for (int i = 0; i <= m; i++)
    	arr[0][i] = 0;
    for (int i = 1; i <= n; i++)
    	for (int j = 1; j <= m; j++) {
    		if (s[i - 1] == t[j - 1]) arr[i][j] = max(arr[i - 1][j - 1] + 1, max(arr[i - 1][j], arr[i][j - 1]));
    		else arr[i][j] = max(arr[i - 1][j], arr[i][j - 1]);
    }
    return arr[n][m];
}

bool have() {
	bool good;
	for (int i = 0; i < n; i++) nuse[i] = true;
	for (int j = 0; j < m; j++) {
		good = false;
		for (int i = 0; i < n; i++) {
			if ((t[j] == s[i]) && (nuse[i])) {
				good = true;
				nuse[i] = false;
				break;
			}
		}
		if (!good) return false;
	}	
	return true;		
}

int main() {
	cin >> s;
	cin >> t;
	n = s.size();
	m = t.size();
	//cout << lcs() << endl;
	if (lcs() == m) { 
		cout << "automaton" << endl;
	} else {
		if (have()) {
			if (n == m) {
				cout << "array" << endl;
			} else {
				cout << "both" << endl;
			}	
		} else {
			cout << "need tree" << endl;
		}
	}	

	return 0;
}