n, x = map(int, input().split())
a = list(map(int, input().split()))
a.sort()
R = n - 1
best = 0
for L in range(n):
    while R > 0 and a[L] + a[R] > x:
        R -= 1
    if L != R and a[L] + a[R] <= x:
        best = max(a[L] + a[R], best)
print(best)
