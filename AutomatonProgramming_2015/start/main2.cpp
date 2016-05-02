#include <bits/stdc++.h>
 
using namespace std;
 
const int N = 500 + 10;
const int M = 5 * 100000 + 10;
 
struct vertex {
    int l;
    int r;
    char c;
};
 
vertex tree[N];
int n;
int m;
char s[M];
vector<int> res;
int dp[N][M];
 
bool dfs(int v, int pos) {
    if (dp[v][pos] != -1) return (dp[v][pos] == 1);
    if (pos == m) {
        dp[v][pos] =  1;
        return true;
    }
    int left = tree[v].l;
    int right = tree[v].r;
    if (tree[left].c == s[pos]) {
        if (dfs(left, pos + 1)) {
            dp[v][pos] = 1;
            return true;
        }
    }
    if (tree[right].c == s[pos]) {
        if (dfs(right, pos + 1)) {
            dp[v][pos] = 1;
            return true;
        }
    }
    dp[v][pos] = 0;
    return false;
}
 
void init() {
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= m; j++) {
            dp[i][j] = -1;
        }
    }
}
int main() {
    freopen("start.in", "r", stdin);
    freopen("start.out", "w", stdout);
    scanf("%d %d", &m, &n);
    init();
    for (int i = 0; i < n; i++) {
        int l, r;
        char c;
        scanf("%d %d %c", &l, &r, &c);
        l--; r--;
        tree[i] = {l, r, c};
    }
    scanf("%s", s);
    scanf("%s", s);
    for (int j = m - 1; j >= 0; j--) {
        for (int i = 0; i < n; i++) {
            if (j == 0 && dfs(i, j)) {
                res.push_back(i);
            }
//          cout << i << " " << j << " " << dfs(i, j) << endl;
        }
    }
    printf("%d ", (int)res.size());
    for (int x: res) printf("%d ", x + 1);
    return 0;
}