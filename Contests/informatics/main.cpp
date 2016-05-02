#include <cstdio>

bool leap(int y) {
    return y % 400 == 0 || (y % 4 == 0 && y % 100 != 0);
}

int main() {
	int n;
	scanf("%d", &n);
	int day = 0;
    bool isLeap = leap(n);
    while (true) {
        if (leap(n)) day = (day + 2) % 7; else day = (day + 1) % 7;
        n++;
        if (day == 0 && leap(n) == isLeap) break;
    }
    printf("%d\n", n);
}