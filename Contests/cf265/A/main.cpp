#include <iostream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
	int n, res = 0;
	string s;
	cin >> n;
	cin >> s;
	while ((res < n) &&(s[res] == '1')) res++;
	if (res < n) res++;
	cout << res;
	return 0;
}