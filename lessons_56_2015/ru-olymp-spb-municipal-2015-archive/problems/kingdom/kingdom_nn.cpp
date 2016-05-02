#include <cstdio>
#include <algorithm>
 
int const INF = 1 << 30;
 
int dp[222][222];
int x[222];
 
int main() {
  int n, k, a, b;
  scanf("%d%d%d%d", &n, &k, &a, &b);
  for (int i = 0; i < n; i++) scanf("%d", x + i);
  for (int i = 0; i <= n; i++)
    for (int j = 0; j <= k; j++)
      dp[i][j] = INF;
  dp[0][0] = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j <= k; j++) {
      int val = dp[i][j];
      if (val == INF) continue;
      for (int e = i + a; e <= n && e <= i + b; e++) {
        int cur = std::max(val, x[e - 1] - x[i]);
        if (dp[e][j + 1] > cur)
          dp[e][j + 1] = cur;
      }
    }
  }
  printf("%d\n", dp[n][k]);
}
