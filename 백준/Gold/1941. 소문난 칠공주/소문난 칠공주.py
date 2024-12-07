from collections import deque
from itertools import combinations

N = 5
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

arr = [list(input().strip()) for _ in range(N)]


def bfs(pick):

    visit = set()
    qu = deque([pick[0]])
    visit.add(pick[0])

    connected_count = 1

    while qu:
        x, y = divmod(qu.popleft(), N)

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            next = nx * N + ny

            if nx < 0 or ny < 0 or nx >= N or ny >= N:
                continue

            if next in pick and next not in visit:
                visit.add(next)
                qu.append(next)
                connected_count += 1

    return connected_count == 7


ans = 0

for comb in combinations(range(25), 7):
    count_s = 0
    pick = []

    for idx in comb:
        x, y = divmod(idx, N)
        pick.append(idx)
        if arr[x][y] == 'S':
            count_s += 1

    if count_s >= 4 and bfs(pick):
        ans += 1

print(ans)