n, k = map(int, input().split())
a = list(map(int, input().split()))

b = []
x = [0, 0]
z = k
for i in range(k):
    intr = True
    if max(x) == n - 1 and min(x) != k - n:
        intr = False
    if min(x) == k - n and min(x) != max(x):
        intr = False
    if max(x) > k - n:
        z = min(z, i)
    x[a[i] - 1] += 1
    b.append(0 if intr else 1)

print(z)
print(" ".join(map(str, b)))