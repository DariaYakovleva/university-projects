#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
#include <vector>
#include <set>

using namespace std;

long long c, hr, hb, wr, wb;
long long det;

bool omnom(long long d) {
	long long  a = c * hb - d * wb;
	long long b = c * hr - d * wr;
	long long ax = (c - wb * b) / wr;
	if (a == 0) {
		if (abs(b) >= abs(det)) {
			return true;
		}
		return false;
	}
	if (b == 0) {
		if (abs(a) >= abs(det)) {
			return true;
		}
		return false;
	}
	if (abs(a) < abs(det) && abs(b) < abs(det)) {
		return false;
	}
	if (det < 0) {
		if (a <= 0 && b >= 0) {
			return true;	
		}
	} else {
		if (a >= 0 && b <= 0) {
			return true;	
		}
	}
	return false;
}

long long bs() {
	if (det == 0) {
		if (wb < wr) {
			return c * hb / wb;
		} else {
			return c * hr / wr;
		}
	}
	long long left = -1;
	if (det < 0) {
		left = c * hb / wb;
	} else {
		left = c * hr / wr;
	}
	long long right = max(hr, hb) * (c / min(wr, wb)) + 1;
	while (right - left > 1) {
		int m = (right + left) / 2;
		if (omnom(m)) {
			left = m;
		} else {
			right = m;
		}
	}
	if (omnom(right)) return right;
	return left;
}

int main() {
	cin >> c >> hr >> hb >> wr >> wb;
	det = wr * hb - hr * wb;
	cout << bs();
	return 0;
}