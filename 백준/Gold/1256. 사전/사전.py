n, m, k = map(int, input().split())
dp = [[0] * (n + m + 1) for _ in range(n + m + 1)]


def solve(n, m, k):
    if n == 0:
        return 'z' * m
    if m == 0:
        return 'a' * n

    a = dp[n + m - 1][n - 1]

    if k <= a:
        return 'a' + solve(n - 1, m, k)
    else:
        return 'z' + solve(n, m - 1, k - a)


for i in range(n + m + 1):
    dp[i][0] = dp[i][i] = 1
    for j in range(1, i):
        dp[i][j] = min(dp[i - 1][j] + dp[i - 1][j - 1], 1_000_000_001)

if k > dp[n + m][n]:
    print(-1)
else:
    print(solve(n, m, k))