#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 2000010;

string s;
int p[N];
int ans[N];

void p_fun(string ss) {
	p[0] = 0;
	for (int i = 1; i < (int)ss.size(); i++) {
		int j = p[i - 1];
		while (j > 0 && ss[i] != ss[j]) j = p[j - 1];
		if (ss[i] == ss[j]) j++;
		p[i] = j;
	}
}

int main() {

	int m, kk;
	cin >> m >> kk;
	cin >> s;
	p_fun(s);
	int n = (int) s.size();
	for (int i = 0; i < n; i++) {
		cout << p[i] <<  " " ;
		ans[i] = 0;
	}
	for (int i = 1; i < n; i++) {
		int k = i - p[i - 1];        	
		if (i % k != 0) k = i;
		if (i / k == kk) {
			for (int j = i + 1; j <= k; j++) {
				if (p[j] > 0) ans[j] = 1;
			}
		}
	}
	for (int i = 0; i < n; i++) {
		cout << ans[i];
	}
	cout << endl;
	
	return 0;
}