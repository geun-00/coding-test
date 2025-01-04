from copy import deepcopy

n, m, r = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(n)]

command = list(map(int, input().split()))


def solve(num, arr):
    new_arr = []

    if num == 1:
        new_arr = arr[::-1]
    elif num == 2:
        new_arr = [row[::-1] for row in arr]
    elif num == 3:
        new_arr = [[arr[n - 1 - j][i] for j in range(n)] for i in range(m)]
    elif num == 4:
        new_arr = [[arr[j][m - 1 - i] for j in range(n)] for i in range(m)]
    elif num == 5:
        new_arr = deepcopy(arr)
        for i in range(n // 2):
            for j in range(m // 2, m):
                new_arr[i][j] = arr[i][j - m // 2]  # 1 -> 2
        for i in range(n // 2, n):
            for j in range(m // 2, m):
                new_arr[i][j] = arr[i - n // 2][j]  # 2 -> 3
        for i in range(n // 2, n):
            for j in range(m // 2):
                new_arr[i][j] = arr[i][j + m // 2]  # 3 -> 4
        for i in range(n // 2):
            for j in range(m // 2):
                new_arr[i][j] = arr[i + n // 2][j]  # 4 -> 1
    elif num == 6:
        new_arr = deepcopy(arr)
        for i in range(n // 2, n):
            for j in range(m // 2):
                new_arr[i][j] = arr[i - n // 2][j]  # 1 -> 4
        for i in range(n // 2, n):
            for j in range(m // 2, m):
                new_arr[i][j] = arr[i][j - m // 2]  # 4 -> 3
        for i in range(n // 2):
            for j in range(m // 2, m):
                new_arr[i][j] = arr[i + n // 2][j]  # 3 -> 2
        for i in range(n // 2):
            for j in range(m // 2):
                new_arr[i][j] = arr[i][j + m // 2]  # 2 -> 1

    return new_arr


for num in command:
    arr = solve(num, arr)
    n = len(arr)
    m = len(arr[0])

ans = []

for row in arr:
    print(" ".join(map(str, row)))
