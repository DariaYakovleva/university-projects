#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
#include <vector>
#include <set>

using namespace std;

long long c, hr, hb, wr, wb;
long long m = 0;
int am;
int bm;

int main() {
	cin >> c >> hr >> hb >> wr >> wb;
	for (long long a = 0; a < 200000; a++) {
		if (wr * a > c) break;
		long long b = (c - wr * a) / wb;
		if (hb * b + hr * a > m) {
			m = hb * b + hr * a;
			am = a;
			bm = b;
		}		                      	
	}

	long long st = c / wr;
	for (long long a = c / wr; st - a < 200000; a--) {
		if (a < 0) break;
		if (wr * a > c) break;
		long long b = (c - wr * a) / wb;
		if (hb * b + hr * a > m) {
			m = hb * b + hr * a;
			am = a;
			bm = b;
		}		                      	
	}                           
	for (long long b = 0; b < 200000; b++) {
		if (wb * b > c) break;
		long long a = (c - wb * b) / wr;
		if (hr * a + hb * b > m) {
			m = hb * b + hr * a;
			am = a;
			bm = b;
		}		                      	
	}

	st = c / wb;
	for (long long b = c / wb; st - b < 200000; b--) {
			if (b < 0) break;
		if (wb * b > c) break;
		long long a = (c - wb * b) / wr;
		if (hb * b + hr * a > m) {
			m = hb * b + hr * a;
			am = a;
			bm = b;
		}		                      	
	}
	

	cout << m << endl;

	return 0;
}