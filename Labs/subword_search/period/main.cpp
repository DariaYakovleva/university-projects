#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int N = 2000010;

string s;
int p[N];

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
	freopen("period.in", "r", stdin);
	freopen("period.out", "w", stdout);
	cin >> s;
	p_fun(s);
	int n = (int) s.size();
	int k = n - p[n - 1];
	if (n % k != 0) k = n;
	cout << k << endl;

	return 0;
}