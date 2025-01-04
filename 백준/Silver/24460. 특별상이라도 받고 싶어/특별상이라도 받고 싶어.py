def solve(n, x, y):
    if n == 1:
        return arr[x][y]

    half = n // 2

    temp = [
        solve(half, x, y),
        solve(half, x, y + half),
        solve(half, x + half, y),
        solve(half, x + half, y + half)
    ]

    temp.sort()

    return temp[1]


n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]

print(solve(n, 0, 0))
