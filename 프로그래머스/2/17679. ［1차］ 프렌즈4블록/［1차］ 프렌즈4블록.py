from collections import deque


def solution(m, n, board):
    empty = 'x'
    my_map = [list(row) for row in board]

    ans = 0
    qu = deque()
    mark = [[False] * n for _ in range(m)]

    while True:

        flag = True

        for i in range(m - 1):
            for j in range(n - 1):

                if my_map[i][j] == empty:
                    continue

                if (my_map[i][j] == my_map[i][j + 1] and
                        my_map[i][j] == my_map[i + 1][j] and
                        my_map[i][j] == my_map[i + 1][j + 1]):
                    flag = False
                    mark[i][j] = mark[i + 1][j] = mark[i][j + 1] = mark[i + 1][j + 1] = True

        if flag:
            break

        for i in range(m):
            for j in range(n):
                if mark[i][j]:
                    ans += 1
                    my_map[i][j] = empty
                    mark[i][j] = False

        for i in range(n):
            for j in range(m - 1, -1, -1):
                if my_map[j][i] != empty:
                    qu.append(my_map[j][i])

            for j in range(m - 1, -1, -1):
                if not qu:
                    my_map[j][i] = empty
                else:
                    my_map[j][i] = qu.popleft()

    return ans
