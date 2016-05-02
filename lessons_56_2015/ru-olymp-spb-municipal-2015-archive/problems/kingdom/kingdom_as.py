n, k, a, b = map(int, input().split())
x = list(map(int, input().split()))

INF = float("inf")

dp = [[INF] * (k + 1) for i in range(n + 1)]
dp[0][0] = 0

for i in range(n):
    for j in range(k):
        if dp[i][j] != INF:
            for p in range(a, b + 1):
                if i + p <= n and max(dp[i][j], x[i + p - 1] - x[i]) < dp[i + p][j + 1]:
                    dp[i + p][j + 1] = max(dp[i][j], x[i + p - 1] - x[i])

print(dp[n][k])