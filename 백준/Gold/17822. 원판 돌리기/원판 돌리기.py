n, m, t = map(int, input().split())
remain = n * m
arr = [list(map(int, input().split())) for _ in range(n)]


def rotate(row, d, k):
    temp = [0] * m

    for i in range(m):
        idx = (i + k) % m if d == 0 else (i - k + m) % m
        temp[idx] = row[i]

    for i in range(m):
        row[i] = temp[i]


def find():
    global remain
    remove = [[False] * m for _ in range(n)]
    ret = False

    for i in range(n):
        for j in range(m):

            if arr[i][j] == 0:
                continue

            if arr[i][j] == arr[i][(j + 1) % m]:
                remove[i][j] = True
                remove[i][(j + 1) % m] = True

            if i < n - 1 and arr[i][j] == arr[i + 1][j]:
                remove[i][j] = True
                remove[i + 1][j] = True

    for i in range(n):
        for j in range(m):
            if remove[i][j]:
                ret = True
                arr[i][j] = 0
                remain -= 1

    return ret


def solve(avg):

    for i in range(n):
        for j in range(m):

            if arr[i][j] == 0:
                continue

            if arr[i][j] < avg:
                arr[i][j] += 1
            elif arr[i][j] > avg:
                arr[i][j] -= 1


def getAvg():

    arr_sum = 0
    count = 0

    for i in range(n):
        for j in range(m):

            if arr[i][j] == 0:
                continue

            count += 1
            arr_sum += arr[i][j]

    return arr_sum / count


for _ in range(t):

    x, d, k = map(int, input().split())

    for r in range(x - 1, n, x):
        rotate(arr[r], d, k)

    if remain > 0:
        if not find():
            solve(getAvg())

ans = sum(sum(row) for row in arr)
print(ans)
