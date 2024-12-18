n, m, k = map(int, input().split())

ox, oy = -1, -1

if k != 0:
    ox, oy = divmod(k - 1, m)

dp = [[[0, 0] for _ in range(m)] for _ in range(n)]

dp[0][0][0] = 1

for x in range(n):
    for y in range(m):

        for dx, dy in [(1, 0), (0, 1)]:
            nx = x + dx
            ny = y + dy

            if nx >= n or ny >= m:
                continue

            if (nx, ny) == (ox, oy):
                dp[nx][ny][1] += dp[x][y][0]
            else:
                dp[nx][ny][0] += dp[x][y][0]
                dp[nx][ny][1] += dp[x][y][1]

print(dp[n - 1][m - 1][0 if k == 0 else 1])