#include<bits/stdc++.h>

using namespace std;

const int N = 1010;

string s = "";
int n;

void init() {
	for (int i = 0; i < n; i++) {
		s += '0';
	}
}

int main() {
	cin >> n;
	init();
	for (int i = 0; i < n; i++) {
		cout << s << endl;
		cout.flush();
		int a;
		cin >> a;
		if (a == n) break;
		s[i] = '1';
		cout << s << endl;
		cout.flush();
		int b;
		cin >> b;
		if (b == n) break;
		if (a > b) {
			s[i] = '0';
		}
	}
}