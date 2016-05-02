#include <bits/stdc++.h>

using namespace std;

int main() {
	int n;
	cin >> n;
	int cmin = 0;
	int mmin = 1000000010;
	int mmax = 0;
	int cmax = 0;
	for (int i = 0; i < n; i++) {
		int b;
		cin >> b;
		if (b < mmin) {
			mmin = b;
			cmin = 1;
		} else if (b == mmin) {
			cmin++;
		} 
		if (b > mmax) {
			mmax = b;
			cmax = 1;
		} else if (b == mmax) {
			cmax++;
		} 	
	}
	if (mmin == mmax) {
		cout << 0 << " " << (long long)cmin * (long long)(cmin - 1) / 2 << endl;
	} else {
		cout << mmax - mmin << " " << (long long)cmin * (long long)cmax << endl;
	}	

}