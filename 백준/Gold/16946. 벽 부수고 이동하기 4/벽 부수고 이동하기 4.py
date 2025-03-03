import sys
from collections import deque

dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)


def bfs(x, y, num):
    queue = deque([(x, y)])
    visited[x][y] = True
    temp[x][y] = num
    count = 1

    while queue:
        cx, cy = queue.popleft()

        for d in range(4):
            nx, ny = cx + dx[d], cy + dy[d]

            if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny] and grid[nx][ny] == '0':
                temp[nx][ny] = num
                visited[nx][ny] = True
                queue.append((nx, ny))
                count += 1

    cache[num] = count


def search_around(x, y):
    groups = set()
    for d in range(4):
        nx, ny = x + dx[d], y + dy[d]
        if 0 <= nx < n and 0 <= ny < m and grid[nx][ny] == '0':
            groups.add(temp[nx][ny])

    result = 1
    for group in groups:
        result += cache[group]
        result %= 10

    return result

n, m = map(int, input().split())
grid = [list(input()) for _ in range(n)]

visited = [[False] * m for _ in range(n)]
temp = [[0] * m for _ in range(n)]
cache = {}

num = 1
for i in range(n):
    for j in range(m):
        if grid[i][j] == '0' and not visited[i][j]:
            bfs(i, j, num)
            num += 1

result = []
for i in range(n):
    row = []
    for j in range(m):
        if grid[i][j] == '0':
            row.append('0')
        else:
            row.append(str(search_around(i, j)))
    result.append("".join(row))

print("\n".join(result))
