def solve(row, visit):
    if visit == (1 << n) - 1:
        return 0
    if dp[row][visit] != -1:
        return dp[row][visit]

    min_cost = float('inf')
    for i in range(n):
        if visit & (1 << i) != 0:
            continue

        min_cost = min(min_cost, solve(row + 1, visit | (1 << i)) + cost[row][i])

    dp[row][visit] = min_cost
    return dp[row][visit]

n = int(input())
dp = [[-1] * (1 << n) for _ in range(n)]

cost = [list(map(int, input().split())) for _ in range(n)]

print(solve(0, 0))