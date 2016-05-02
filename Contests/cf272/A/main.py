a, b = map(int, input().split())
N = 1000000007
print((b * (b - 1) // 2 * (b * a * (a + 1) // 2 + a)) % N)