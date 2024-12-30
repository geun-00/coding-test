from collections import deque


def bfs(characterX, characterY, itemX, itemY, N, map):
    visit = [[False] * (N + 1) for _ in range(N + 1)]
    sx, sy = characterX * 2, characterY * 2

    visit[sx][sy] = True
    qu = deque()
    qu.append((sx, sy, 0))

    dx = (-1, 1, 0, 0)
    dy = (0, 0, -1, 1)

    while qu:
        x, y, move = qu.popleft()

        if x == itemX * 2 and y == itemY * 2:
            return move // 2

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx > N or ny > N or map[nx][ny] != 1 or visit[nx][ny]:
                continue

            visit[nx][ny] = True
            qu.append((nx, ny, move + 1))


def solution(rectangle, characterX, characterY, itemX, itemY):
    N = 100
    map = [[0] * (N + 1) for _ in range(N + 1)]

    for a in rectangle:
        x1, y1 = a[0] * 2, a[1] * 2
        x2, y2 = a[2] * 2, a[3] * 2

        for x in range(x1, x2 + 1):
            for y in range(y1, y2 + 1):
                if x == x1 or x == x2 or y == y1 or y == y2:
                    if map[x][y] == 0:
                        map[x][y] = 1
                else:
                    map[x][y] = -1

    return bfs(characterX, characterY, itemX, itemY, N, map)
