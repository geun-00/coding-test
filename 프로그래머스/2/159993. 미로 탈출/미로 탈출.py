from collections import deque


def solution(maps):

    start, lever, exit = (0, 0), (0, 0), (0, 0)

    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if maps[i][j] == 'S':
                start = (i, j)
            elif maps[i][j] == 'L':
                lever = (i, j)
            elif maps[i][j] == 'E':
                exit = (i, j)

    start_to_level = bfs(maps, start, lever)
    lever_to_exit = bfs(maps, lever, exit)

    if start_to_level == -1 or lever_to_exit == -1:
        return -1

    return start_to_level + lever_to_exit

def bfs(maps, start, end):
    n, m = len(maps), len(maps[0])

    sx, sy = start
    ex, ey = end

    visit = [[False] * m for _ in range(n)]
    visit[sx][sy] = True

    qu = deque([(sx, sy, 0)])

    dx = (-1, 1, 0, 0)
    dy = (0, 0, -1, 1)

    while qu:
        x, y, count = qu.popleft()

        if x == ex and y == ey:
            return count

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue
            if visit[nx][ny] or maps[nx][ny] == 'X':
                continue

            visit[nx][ny] = True
            qu.append((nx, ny, count + 1))
    
    return -1