#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>


using namespace std;

const int N = 100010;
long long arr[N][3];
//a, ab, ac

int main() {
	freopen("abc.in", "r", stdin);
	freopen("abc.out", "w", stdout);
	string s;
	cin >> s;
	for (int i = 0; i < 3; i++) {
		arr[0][i] = 0;
	}
	for (int i = 0; i < s.size(); i++) {
		int pos = i + 1;
		for (int j = 0; j < 3; j++) arr[pos][j] = arr[pos - 1][j];
		if (s[i] == 'a') {
			arr[pos][0]++;	
		} else if (s[i] == 'b') {
			arr[pos][1] += arr[pos][0];
		} else if (s[i] == 'c') {
			arr[pos][2] += arr[pos][1];
		}
	}
	cout << arr[s.size()][2] << endl;

	
	return 0;
}