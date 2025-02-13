def multiply(a, b):
    n = len(a)
    temp = [[0] * n for _ in range(n)]

    for i in range(n):
        for j in range(n):
            for k in range(n):
                temp[i][j] += a[i][k] * b[k][j]
                temp[i][j] %= 1000

    return temp


def solve(arr, b):
    if b == 1:
        return arr

    half = solve(arr, b // 2)
    result = multiply(half, half)

    if b % 2 == 1:
        result = multiply(result, arr)
    return result

n, b = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]

result = solve(arr, b)

for row in result:
    print(" ".join(map(lambda x: str(x % 1000), row)))
