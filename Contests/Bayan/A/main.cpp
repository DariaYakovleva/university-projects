#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int n;
int sh = 39;
string s = "+------------------------+\n|#.#.#.#.#.#.#.#.#.#.#.|D|)\n|#.#.#.#.#.#.#.#.#.#.#.|.|\n|#.......................|\n|#.#.#.#.#.#.#.#.#.#.#.|.|)\n+------------------------+\n";

int main() {
	cin >> n;
	int cnt = 0;
	int pos = 0;
	while (cnt < n) {
		while (pos < s.size() && s[pos] != '#') pos++;
		if (pos < s.size()) {
			s[pos] = 'O';
			cnt++;
		}
		while (s[pos] != '\n') {
			pos = (pos + 1) % s.size();	
		}
		pos = (pos + 1) % s.size();	
		
	}
	cout << s;
}