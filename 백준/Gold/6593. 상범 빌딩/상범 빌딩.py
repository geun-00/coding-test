from collections import deque

dz = (-1, 1, 0, 0, 0, 0)
dx = (0, 0, -1, 1, 0, 0)
dy = (0, 0, 0, 0, -1, 1)
L, R, C = 0, 0, 0
arr = []
visit = []


def bfs(start):

    global visit

    qu = deque([start])
    visit[start[0]][start[1]][start[2]] = True

    time = 0

    while qu:

        size = len(qu)

        for _ in range(size):

            z, x, y = qu.popleft()

            if arr[z][x][y] == 'E':
                return time

            for i in range(6):
                nz = z + dz[i]
                nx = x + dx[i]
                ny = y + dy[i]

                if not (0 <= nz < L and 0 <= nx < R and 0 <= ny < C):
                    continue
                if visit[nz][nx][ny] or arr[nz][nx][ny] == '#':
                    continue

                visit[nz][nx][ny] = True
                qu.append((nz, nx, ny))

        time += 1

    return -1


result = []

while True:

    L, R, C = map(int, input().split())

    if L == 0 and R == 0 and C == 0:
        break

    arr = []
    visit = [[[False] * C for _ in range(R)] for _ in range(L)]

    for _ in range(L):
        arr.append([list(input().strip()) for _ in range(R)])
        input()

    start = None

    for i in range(L):
        for j in range(R):
            for k in range(C):
                if arr[i][j][k] == 'S':
                    start = (i, j, k)
                    break

    x = bfs(start)

    if x != -1:
        result.append(f'Escaped in {x} minute(s).')
    else:
        result.append("Trapped!")

print("\n".join(result))
