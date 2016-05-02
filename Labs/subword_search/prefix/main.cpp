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
	freopen("prefix.in", "r", stdin);
	freopen("prefix.out", "w", stdout);
	cin >> s;
	p_fun(s);
	for (int i = 0; i < (int)s.size(); i++) {
		cout << p[i] << " ";
	}

	return 0;
}