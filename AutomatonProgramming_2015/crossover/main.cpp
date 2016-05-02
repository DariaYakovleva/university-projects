#include <bits/stdc++.h>

using namespace std;
const int N = 310;

string arr[N];
string s;
int n, m;

bool have() {
	for (int i = 0; i < n; i++) {
		if (arr[i] == s) return true;
	}
	return false;
}

bool one_point() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int pos = 0;
			while (pos < m && s[pos] == arr[i][pos]) pos++;
			while (pos < m && s[pos] == arr[j][pos]) pos++;
			if (pos == m) return true;
		}	
	}
	return false;
}

bool two_point() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int pos = 0;
			while (pos < m && s[pos] == arr[i][pos]) pos++;
			while (pos < m && s[pos] == arr[j][pos]) pos++;
			while (pos < m && s[pos] == arr[i][pos]) pos++;
			if (pos == m) return true;
		}	
	}
	return false;
}

bool many_point() {
	bool ok = false;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			bool cur = true;
			for (int pos = 0; pos < m; pos++) {
				if ((arr[i][pos] != s[pos]) && (arr[j][pos] != s[pos])) cur = false;
			}
			ok |= cur;
		}	
	}
	return ok;
}



int main() {
	freopen("crossover.in", "r", stdin);
	freopen("crossover.out", "w", stdout);
	cin >> n >> m;
	getline(cin, s);
	for (int i = 0; i < n; i++) {
		getline(cin, arr[i]);	
	}
	getline(cin, s);
	if (have() || one_point()) {
		cout << "YES" << endl << "YES" << endl << "YES" << endl;
	} else {
		if (two_point()) {
			cout << "NO" << endl << "YES" << endl << "YES" << endl;
		} else {
			if (many_point()) {
				cout << "NO" << endl << "NO" << endl << "YES" << endl;
			} else {
				cout << "NO" << endl << "NO" << endl << "NO" << endl;
			} 
		}
	}



}