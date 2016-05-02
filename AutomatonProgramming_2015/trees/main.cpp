#include <bits/stdc++.h>

using namespace std;

const int N = 2 * 100000 + 10;

struct vertex {
	int p;
	int l;
	int r;
	int prev;
	int num;
	vertex() {
		prev = -1;
	}
	vertex(int a, int b, int c, int d, int e) {
		p = a;
		l = b;
		r = c;
		num = d;
		prev = e;
	}
};

vertex tree[N];
int tree2[N];
int n;
map<int, int> pred;
int color[N];

void dfs(int v) {
	if (v == -1) return;
	if (tree[v].l == -1) return;
	color[v] = 1;
	if (pred.find(tree[v].p) != pred.end() && pred[tree[v].p] != -1) {
//		cout << v << endl;
		int pp = pred[tree[v].p];
		int cur = tree[v].r;
		if (color[tree[pp].l] == 1) cur = tree[v].l;
		if (tree[tree[v].prev].l == v) {
            tree[tree[v].prev].l = cur; 
        } else {
            tree[tree[v].prev].r = cur;
        }
        tree[cur].prev = tree[v].prev;
        dfs(cur);
	} else {
		pred[tree[v].p] = v;
		dfs(tree[v].l);
		dfs(tree[v].r);
		pred[tree[v].p] = -1;
	}
	color[v] = 2;
}

int renum(int v, int num) {
	tree2[num] = v;
	tree[v].num = num;
	if (tree[v].l == -1) return 1;
	int cnt = renum(tree[v].l, num + 1);
	return cnt + renum(tree[v].r, num + cnt + 1) + 1;
}

int main() {
	freopen("trees.in", "r", stdin);
	freopen("trees.out", "w", stdout);
	scanf("%d", &n);
	char s[20];
	for (int i = 0; i < n; i++) {
		color[i] = 0;
		scanf("%s", s);
		if (s[0] == 'l') {
			int p;
			scanf("%d", &p);
			tree[i] = vertex(p, -1, -1, i, tree[i].prev);
		} else {
			int p, l, r;
			scanf("%d %d %d", &p, &l, &r);
			l--; r--;
			tree[i] = vertex(p, l, r, i, tree[i].prev);
			tree[l].prev = i;
			tree[r].prev = i;
		}
	}
	dfs(0);
	int cnt = renum(0, 0);
	printf("%d\n", cnt);
	for (int i = 0; i < cnt; i++) {
		int v = tree2[i];
		if (tree[v].l == -1) {
			printf("leaf %d\n", tree[v].p);
		} else {
			printf("choice %d %d %d\n", tree[v].p, tree[tree[v].l].num + 1, tree[tree[v].r].num + 1); 
		}                                    	
	}
	return 0;
}