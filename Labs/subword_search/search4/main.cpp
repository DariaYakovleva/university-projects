#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

const int N = 1000010;
const int M = 28;

struct vertex {
	int prev;
	char pchar;
	int link;
	int up;
} tree[N];

//string s;
char s1[N];
int n;
int sizeT;
bool ans[N];
std::vector<int> leaf[N];
int next[N][M];
int go[N][M];

void add_str(char* ss, int ind) {
	int pos = 0;
	int sizeS = (int)strlen(ss);
	for (int i = 0; i < sizeS; i++) {
		int c = ss[i] - 'a';
		if (next[pos][c] != -1) {
			pos = next[pos][c];
		} else {
			next[pos][c] = sizeT;
			tree[sizeT].prev = pos;
			tree[sizeT].pchar = ss[i];
			for (int j = 0; j < M; j++) {
				next[sizeT][j] = -1;
				go[sizeT][j] = -1;
			}
			tree[sizeT].link = -1;
			tree[sizeT].up = -1;
			pos = sizeT;
			sizeT++;
		}
	}
	leaf[pos].push_back(ind);
}

int goV(int v, char c);

int get_link(int v) {
	if (tree[v].link == -1) {
//			cout << "getl "  << v << endl;

		if (v == 0 || tree[v].prev == 0) {
			tree[v].link = 0;
		} else {
			tree[v].link = goV(get_link(tree[v].prev), tree[v].pchar);
		}
	}
//	cout << "lnk " << v << " " << tree[v].link << endl;
	return tree[v].link;
}

int goV(int v, char c) {
	int ch = c - 'a';
	if (go[v][ch] == -1) {
//	    		cout << "go "  << v << endl;

		if (next[v][ch] != -1) {
			go[v][ch] = next[v][ch];
		} else {
			if (v == 0) {
				go[v][ch] = 0;
			} else {
				go[v][ch] = goV(get_link(v), c);
			}
		}
	}
	return go[v][ch];
}

int up(int v) {
	if (tree[v].up == -1) {
//		cout << "up "  << v << endl;
		int u = get_link(v);
		if (u == 0) {
			tree[v].up = 0;	
		} else if (leaf[u].size() > 0) {
			tree[v].up = u;
		} else {
			tree[v].up = up(u);
		}
	}
	return tree[v].up;
}


void init() {	
	for (int i = 0; i < n; i++) {
		ans[i] = false;
	}
	for (int j = 0; j < M; j++) {
		next[0][j] = -1;
		go[0][j] = -1;
	}
	tree[0].prev = -1;
	tree[0].pchar = 0;
	tree[0].link = -1;
	tree[0].up = -1;
	sizeT = 1;
}

void findSubstr(char* ss) {
	int pos = 0;
	int sizeS = (int)strlen(ss);
	for (int i = 0; i < sizeS; i++) {
		pos = goV(pos, ss[i]);
		int u = pos;
		while (u != 0) {
			if (leaf[u].size() > 0 && ans[leaf[u][0]]) break;
			for (int j = 0; j < (int)leaf[u].size(); j++) {
				ans[leaf[u][j]] = true;
			}
			u = up(u);
		}
	}
}


int main() {
	freopen("search4.in", "r", stdin);
	freopen("search4.out", "w", stdout);
	init();
	scanf("%d", &n);
	//getline(cin, s);
	for (int i = 0; i < n; i++) {
		//getline(cin, s);
		scanf("%s", s1);
		add_str(s1, i);
	}
//	getline(cin, s);
    scanf("%s", s1);
	findSubstr(s1);
	for (int i = 0; i < n; i++) {
		printf("%s\n", (ans[i]?"YES":"NO"));
	}
	return 0;
}