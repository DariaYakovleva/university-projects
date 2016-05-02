#include <bits/stdc++.h>

using namespace std;

int main() {
	freopen("mutation.in", "r", stdin);
	freopen("mutation.out", "w", stdout);
	int n, m;
	cin >> n >> m;
	cout.precision(10);
	string s;
	getline(cin, s);
	for (int i = 0; i < m; i++) {
		string a;
		getline(cin, a);
		string b;
		getline(cin, b);
		double res = 1.0;
		for (int i = 0; i < (int)a.size(); i++) {
			if (a[i] != b[i]) {
				res /= (double)n;
		    } else {
		    	res *= (double)(n - 1) / (double)n;
		    }
		}
		cout << res << endl;
	}

}