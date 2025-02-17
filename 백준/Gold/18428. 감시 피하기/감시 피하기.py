n = int(input())
grid = [list(input().split()) for _ in range(n)]
ans = "NO"
dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)


def solve(depth):
    if depth == 3:
        check()
        return

    for i in range(n):
        for j in range(n):
            if grid[i][j] == "X":
                grid[i][j] = "O"
                solve(depth + 1)
                grid[i][j] = "X"

def check():
    for i in range(n):
        for j in range(n):
            if grid[i][j] == "T":

                for d in range(4):
                    if not go(i, j, d):
                        return
    global ans
    ans = "YES"

def go(x, y, dir):
    while 0 <= x < n and 0 <= y < n and not grid[x][y] == "O":
        if grid[x][y] == "S":
            return False
        x += dx[dir]
        y += dy[dir]
    return True

solve(0)
print(ans)