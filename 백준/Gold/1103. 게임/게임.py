n, m = map(int, input().split())
board = [list(input().strip()) for i in range(n)]
dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)
mem = [[-1] * m for _ in range(n)]
visit = [[False] * m for _ in range(n)]
flag = False

def dfs(x, y):
    global flag

    if flag:
        return 0

    if mem[x][y] != -1:
        return mem[x][y]

    visit[x][y] = True
    mem[x][y] = 1
    num = int(board[x][y])

    for i in range(4):
        nx = x + (dx[i] * num)
        ny = y + (dy[i] * num)

        if nx < 0 or ny < 0 or nx >= n or ny >= m or board[nx][ny] == 'H':
            continue

        if visit[nx][ny]:
            flag = True
            return 0

        mem[x][y] = max(mem[x][y], dfs(nx, ny) + 1)

    visit[x][y] = False
    return mem[x][y]

dfs(0, 0)

print(-1 if flag else mem[0][0])