from collections import deque

n, m, k = map(int, input().split())

map = [input() for _ in range(n)]
visit = [[[False] * (k + 1) for _ in range(m)] for _ in range(n)]

qu = deque([(0, 0, 1, 0)])
visit[0][0][0] = True

dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)

ans = -1

while qu:

    x, y, move, wall = qu.popleft()

    if x == n - 1 and y == m - 1:
        ans = move
        break

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx < 0 or ny < 0 or nx >= n or ny >= m:
            continue

        if map[nx][ny] == '0':
            if not visit[nx][ny][wall]:
                visit[nx][ny][wall] = True
                qu.append((nx, ny, move + 1, wall))
        else:
            if wall < k and not visit[nx][ny][wall + 1]:
                visit[nx][ny][wall + 1] = True
                qu.append((nx, ny, move + 1, wall + 1))

print(ans)
