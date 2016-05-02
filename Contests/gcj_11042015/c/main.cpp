#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 2010;
int n;
long long x;
string s;
int arr[N]; 
int mm[4][4] = {{1, 2, 3, 4}, {2, -1, 4, -3}, {3, -4, -1, 2}, {4, 3, -2, -1}};

int mul(int a, int b) {
	int res;
	res = mm[abs(a) - 1][abs(b) - 1];
	if (a * b < 0) res = -res;
	return res;
}

bool solve() {
	int ii = 1;
	vector<int> posi;
	vector<int> posk;
	for (int i = 0; i < (int)s.size(); i++) {
	    int x = 1;
		if (s[i] == 'i') x = 2;
		if (s[i] == 'j') x = 3;
		if (s[i] == 'k') x = 4;
		ii = mul(ii, x);
		if (ii == 2) {
//			cerr << "i " << i << "  "<< ii << " " << endl;			
			posi.push_back(i);
		}
	}	
	int kk = 1;
	for (int k = (int)s.size() - 1; k >= 0; k--) {
  		int z = 1;
		if (s[k] == 'i') z = 2;
		if (s[k] == 'j') z = 3;
		if (s[k] == 'k') z = 4;
		kk = mul(z, kk);
		if (kk == 4) {
//			cerr << "k " << k << "  "<< kk << " " << endl;
			posk.push_back(k);
		}
	}	
	posi.push_back((int)s.size());
	int p = posk.size() - 1;
	for (int h = 0; h < (int)posi.size() - 1; h++) {
		while (p >= 0 && posk[p] < posi[h]) p--;
		if (p < 0) return false;
		if (posk[p] < posi[h + 1]) {
			int jj = 1;
			for (int j = posi[h] + 1; j < posk[p]; j++) {
			    int y = 1;
				if (s[j] == 'i') y = 2;
				if (s[j] == 'j') y = 3;
				if (s[j] == 'k') y = 4;
				jj = mul(jj, y);
//				cerr << "j" << j << endl;
//				cout << "j " << j << "  "<< jj << " " << endl;
			}	
			if (jj == 3) {
				return true;
			}						
		}		                                   	
	}
	return false;
}

int main() {
	freopen("input.in", "r", stdin);
	freopen("output.out", "w", stdout);
	int test;
	cin >> test;
	for (int t = 0; t < test; t++) { 
		cin >> n >> x;
		string ss;
		cin >> ss;
		s = "";
		for (int i = 0; i < min(x, x % 4 + 8); i++) {
			s += ss;
		}
		cerr << t << endl;
//		cout << s << endl;
		if (solve()) {
			cout << "Case #" << t + 1 << ": " << "YES" << endl;
		} else {
		    cout << "Case #" << t + 1 << ": " << "NO" << endl;
		}
	}   	

	return 0;
}