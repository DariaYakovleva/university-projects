#include <iostream>
#include <cstdio>
#include <iostream>
#include <cmath>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int M = 30;
const int N = 100010;

struct vertex {
	int symbol[M];
	int prev;
};

string s;
int n;
vertex trie[N];
int cnt = 1;

void addToTrie(int pos) {
	int cur = 0;
	for (int i = pos; i < n; i++) {
		int c = s[i] - 'a';
		if (trie[cur].symbol[c] != -1) {
			cur = trie[cur].symbol[c];		
		} else {
			trie[cur].symbol[c] = cnt;
			trie[cnt].prev = cur;
			for (int j = 0; j < M; j++) trie[cnt].symbol[j] = -1;
			cur = cnt;
			cnt++;	
		}
	}
}

void init() {
	trie[0].prev = -1;
	for (int j = 0; j < M; j++) trie[0].symbol[j] = -1;
}

int main() {
	freopen("trie.in", "r", stdin);
	freopen("trie.out", "w", stdout);
	init();
	getline(cin, s);
	n = (int)s.size();
	for (int i = n - 1; i >= 0; i--) {
		addToTrie(i);
	}
	cout << cnt <<  " " << cnt - 1 << endl;
	for (int i = 0; i < cnt; i++) {
		for (int j = 0; j < M; j++) {
			int to = trie[i].symbol[j];
			if (to != -1) {
				cout << i + 1 << " " << to + 1 << " " << (char)(j + 'a') << endl;
			}
		}
	}
	return 0;
}
