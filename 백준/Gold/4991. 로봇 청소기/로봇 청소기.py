from collections import deque
import sys

input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
w, h = 0, 0
ans = 0
cleaner_dist = []
dirty_dist = [[]]
dirty_pos = []
arr = [[]]
order = []


def range_check(x, y):
    return x < 0 or y < 0 or x >= h or y >= w


def bfs(start, target=None):

    global cleaner_dist

    qu = deque([start])
    visit = [False] * (w * h)
    visit[start] = True

    moved = 0
    found = 0

    while qu:
        size = len(qu)

        for _ in range(size):

            now = qu.popleft()

            if target is not None and now == target:
                return moved

            if target is None and now in dirty_pos:
                cleaner_dist[dirty_pos.index(now)] = moved
                found += 1

                if found == len(dirty_pos):
                    return True

            x, y = divmod(now, w)

            for d in range(4):
                nx = x + dx[d]
                ny = y + dy[d]

                next = nx * w + ny

                if range_check(nx, ny) or visit[next] or arr[nx][ny] == 'x':
                    continue

                visit[next] = True
                qu.append(next)

        moved += 1

    return -1 if target is None else False


def calculate_min(n):
    global ans

    sum_dist = cleaner_dist[order[0]]

    for i in range(n - 1):
        sum_dist += dirty_dist[order[i]][order[i + 1]]

    ans = min(ans, sum_dist)


def get_combinations(idx, visit, n):
    if idx == n:
        calculate_min(n)
        return

    for i in range(n):
        if visit & (1 << i):
            continue

        order[idx] = i
        get_combinations(idx + 1, visit | (1 << i), n)


while True:
    w, h = map(int, input().split())

    if w == 0 and h == 0:
        break

    ans = float('inf')
    arr = [list(input().strip()) for _ in range(h)]

    dirty_pos = []
    cleaner_pos = 0

    for i in range(h):
        for j in range(w):
            idx = i * w + j

            if arr[i][j] == 'o':
                cleaner_pos = idx
            elif arr[i][j] == '*':
                dirty_pos.append(idx)

    if not dirty_pos:
        print(0)
        continue

    n = len(dirty_pos)

    cleaner_dist = [0] * n
    dirty_dist = [[0] * n for _ in range(n)]

    if bfs(cleaner_pos) == -1:
        print(-1)
        continue

    flag = False

    for i in range(n - 1):
        for j in range(i + 1, n):

            start = dirty_pos[i]
            target = dirty_pos[j]

            dist = bfs(start, target)

            if not dist:
                flag = True
                print(-1)
                break

            dirty_dist[i][j] = dirty_dist[j][i] = dist
        if flag:
            break
    else:
        order = [0] * n
        get_combinations(0, 0, n)

        print(ans)
