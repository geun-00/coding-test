import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [[0] * (m + 1) for _ in range(n + 1)]
dp = [[0] * (m + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
    data = input().split()
    for j in range(1, m + 1):
        arr[i][j] = int(data[j - 1])

for i in range(1, m + 1):
    dp[1][i] = dp[1][i - 1] + arr[1][i]

temp = [[0] * (m + 2) for _ in range(2)]

for i in range(2, n + 1):

    temp[0][0] = float('-inf')
    for j in range(1, m + 1):
        temp[0][j] = max(temp[0][j - 1], dp[i - 1][j]) + arr[i][j]

    temp[1][m + 1] = float('-inf')
    for j in range(m, 0, -1):
        temp[1][j] = max(temp[1][j + 1], dp[i - 1][j]) + arr[i][j]

    for j in range(1, m + 1):
        dp[i][j] = max(temp[0][j], temp[1][j])

print(dp[n][m])