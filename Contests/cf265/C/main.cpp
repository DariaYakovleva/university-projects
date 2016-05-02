#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>

using namespace std;
const int N = 1010;

int n, p;
string s;
bool good = 0;
void next(int pos) {
	if (good) return;
	if (pos == n) {
		good = 1;
    	cout << s << endl;
		return;
	}
	for (int i = 0; i < p; i++) {
		if (pos == 0 || ((pos != 0 && char(i + 'a') != s[pos - 1]) && (pos == 1 || (pos > 1 && char(i + 'a') != s[pos - 2])))) {
			s[pos] = char(i + 'a');
			next(pos + 1);
		}
	}
    	
}

void prev(int pos) {
	if (good) return;
	for (int i = s[pos] - 'a' + 1; i < p; i++) {
		if (pos == 0 || ((pos != 0 && char(i + 'a') != s[pos - 1]) && (pos == 1 || (pos > 1 && char(i + 'a') != s[pos - 2])))) {
			s[pos] = char(i + 'a');
			next(pos + 1);
		}	
	}
	if (pos == 0) { 
		return;
	} else {
		prev(pos - 1);
	}	
}

int main() {
	cin >> n >> p;
	cin >> s;
	prev(n - 1);
	if (!good) {
		cout << "NO" << endl;
	}

	
	return 0;
}