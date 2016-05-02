#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>

using namespace std;
const int N = 5010;

pair<int, int> mm[N];
pair<int, int> ff[N];
int cntm = 0;
int cntf = 0;

int main() {
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		char c;
		cin >> c;
		if (c == 'M') {
			cin >> mm[cntm].first >> mm[cntm].second;
			cntm++;
		} else {
			cin >> ff[cntf].first >> ff[cntf].second;
			cntf++;
		}
	}
	int res = 0;
	for (int i = 1; i <= 366; i++) {
		int curm = 0;
		int curf = 0;
		for (int j = 0; j < cntm; j++) {
			if (i >= mm[j].first && i <= mm[j].second) curm++;
		}
		for (int j = 0; j < cntf; j++) {
			if (i >= ff[j].first && i <= ff[j].second) curf++;
		}
		res = max(res, min(curm, curf) * 2);
	}
	cout << res << endl;
	return 0;
}