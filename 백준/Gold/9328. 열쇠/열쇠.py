from collections import deque

EMPTY = '.'
WALL = '*'
DOCUMENT = '$'

dx = (-1, 1, 0, 0)
dy = (0, 0, -1, 1)
h, w = 0, 0
my_map = [[]]
isGetKey = []
lazy = []


def bfs():
    qu = deque([(0, 0)])
    visit = [[False] * w for _ in range(h)]
    visit[0][0] = True

    count = 0

    while qu:
        x, y = qu.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or ny < 0 or nx >= h or ny >= w or visit[nx][ny] or my_map[nx][ny] == WALL:
                continue

            ch = my_map[nx][ny]

            if ch == DOCUMENT:
                count += 1
                my_map[nx][ny] = EMPTY

            if ch.isupper():
                if isGetKey[ord(ch) - ord('A')]:
                    my_map[nx][ny] = EMPTY
                else:
                    lazy[ord(ch) - ord('A')].append((nx, ny))
                    continue

            if ch.islower():
                my_map[nx][ny] = EMPTY
                isGetKey[ord(ch) - ord('a')] = True

                for px, py in lazy[ord(ch) - ord('a')]:
                    visit[px][py] = True
                    qu.append((px, py))

            visit[nx][ny] = True
            qu.append((nx, ny))

    return count


t = int(input())
ans = []
for _ in range(t):

    h, w = map(int, input().split())
    h += 2
    w += 2

    my_map = [[EMPTY] * w for _ in range(h)]
    isGetKey = [False] * 26
    lazy = [[] for _ in range(26)]

    for i in range(1, h - 1):
        row = input().strip()
        for j in range(1, w - 1):
            my_map[i][j] = row[j - 1]

    keys = input().strip()

    for key in keys:
       if key == '0':
           break
       isGetKey[ord(key) - ord('a')] = True

    ans.append(str(bfs()))

print("\n".join(ans))
