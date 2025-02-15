import sys

sys.setrecursionlimit(10**6)

n = int(input())
dice = [list(map(int, input().split())) for _ in range(n)]
opposite = (5, 3, 4, 1, 2, 0)

def solve(depth, total, prev_top):
    if depth == len(dice):
        return total

    next_bottom_index = -1
    for i in range(6):
        if dice[depth][i] == prev_top:
            next_bottom_index = i
            break

    top_index = opposite[next_bottom_index]
    side = 0
    for i in range(6):
        if i != next_bottom_index and i != top_index:
            side = max(side, dice[depth][i])

    return solve(depth + 1, total + side, dice[depth][top_index])

ans = 0

for i in range(6):
    max_side = 0
    for j in range(6):
        if i != j and i != opposite[j]:
            max_side = max(max_side, dice[0][j])

    ans = max(ans, solve(1, max_side, dice[0][i]))

print(ans)