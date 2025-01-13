from collections import deque

dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)


def rotate(arr, l):
    n = len(arr)
    new_arr = [[0] * n for _ in range(n)]
    size = 1 << l

    for i in range(0, n, size):
        for j in range(0, n, size):
            for x in range(size):
                for y in range(size):
                    new_arr[i + y][j + size - x - 1] = arr[i + x][j + y]

    return new_arr


def decrease(arr):
    n = len(arr)
    mark = [[False] * n for _ in range(n)]

    for x in range(n):
        for y in range(n):

            count = 0

            for d in range(4):
                nx = x + dx[d]
                ny = y + dy[d]

                if nx < 0 or ny < 0 or nx >= n or ny >= n or arr[nx][ny] <= 0:
                    continue

                count += 1

            if count < 3:
                mark[x][y] = True

    for i in range(n):
        for j in range(n):
            if mark[i][j]:
                arr[i][j] -= 1


def get_sum(arr):
    return sum(max(0, value) for row in arr for value in row)


def get_max_size(arr):
    n = len(arr)
    visit = [[False] * n for _ in range(n)]
    max_size = 0

    for i in range(n):
        for j in range(n):
            if arr[i][j] > 0 and not visit[i][j]:
                max_size = max(max_size, bfs(i, j, arr, visit))

    return max_size


def bfs(i, j, arr, visit):
    size = 1
    n = len(arr)
    visit[i][j] = True

    qu = deque([(i, j)])

    while qu:
        x, y = qu.popleft()

        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]

            if nx < 0 or ny < 0 or nx >= n or ny >= n or visit[nx][ny] or arr[nx][ny] <= 0:
                continue

            size += 1
            visit[nx][ny] = True
            qu.append((nx, ny))

    return size


n, q = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(1 << n)]
queries = list(map(int, input().split()))

for query in queries:
    arr = rotate(arr, query)
    decrease(arr)

print(get_sum(arr))
print(get_max_size(arr))