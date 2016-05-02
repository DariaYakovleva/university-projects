#include <cstdio>
#include <algorithm>
 
int a[1234567];
 
int main() {
  int n, x;
  scanf("%d%d", &n, &x);
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
  }
  std::sort(a, a + n);
  int ans = -1;
  for (int i = 0, j = n - 1; i < n; i++) {
    while (j > i && a[i] + a[j] > x) {
      --j;
    }
    if (j > i) {
      ans = std::max(ans, a[i] + a[j]);
    }
  }
  printf("%d\n", ans);
}
