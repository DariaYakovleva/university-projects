#include <cstdio>
 
int z[123456];
 
bool can(int x, int y, int n, int k) {
  int sc = k - n;
  if (x > sc && y > sc) return false;  
  if (x > n || y > n) return false;
  if ((x == n || y == n) && (x + y < k)) return false;
  return true;
}
 
int main() {
  int n, k;
  scanf("%d%d", &n, &k);
  int a = 0;
  int b = 0;
  int sc = k - n;
  int ans = -1;
  for (int i = 0; i < k; i++) {
    int x;
    scanf("%d", &x);
    if (can(a + 1, b, n, k) && can(a, b + 1, n, k)) {
       
    } else z[i] = 1;
    if (x == 1) ++a; else ++b;
    if (a > sc || b > sc) {
      if (ans < 0) ans = i;
    }
  }
  printf("%d\n", ans + 1);
  for (int i = 0; i < k; i++) {
    if (i > 0) putchar(' ');
    printf("%d", i[z]);
  }
  puts("");
}

