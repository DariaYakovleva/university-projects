#include <cstdio>
 
int s[1234567];
 
int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    int c = getchar();
    int len = 0;
    while (c <= 32) c = getchar();
    while (c > 32) {
      s[len++] = c;
      c = getchar();
    }
    bool f = false;
    if (s[0] >= 'A' && s[0] <= 'Z') {
      f = true;
      s[0] -= 'A';
      s[0] += 'a';
    }
    for (int j = 1; j < len; j++) {
      if (s[j] == 's' && (j + 1 >= len || s[j + 1] != 'h')) {
        for (int e = len; e > j; e--) s[e] = s[e - 1];
        s[j] = 't';
        s[j + 1] = 'h';
        ++len;
      }
    }
    if (s[0] == 'e') {
      for (int j = len; j > 0; j--) s[j] = s[j - 1];
      s[0] = 'a';
      ++len;
    }
    for (int j = 0; j < len; ) {
      if (s[j] != 'o') {
        j++;
        continue;
      }
      int k = j;
      while (k < len && s[k] == 'o') ++k;
      if (k - j >= 2) {
        s[j + 1] = 'u';
      }
      j = k;
    }
    if (f) {
      s[0] -= 'a';
      s[0] += 'A';
    }
    for (int j = 0; j < len; j++) {
      putchar(s[j]);
    }
    puts("");
  }
}
