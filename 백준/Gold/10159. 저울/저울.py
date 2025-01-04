n = int(input())
m = int(input())

arr = [[0] * n for _ in range(n)]

for _ in range(m):
    a, b = map(int, input().split())
    a -= 1
    b -= 1

    arr[a][b] = 1

for k in range(n):
    for s in range(n):
        for e in range(n):
            if arr[s][k] == 1 and arr[k][e] == 1:
                arr[s][e] = 1

ans = []

for i in range(n):
    count = 0

    for j in range(n):
        if i == j:
            continue

        if arr[i][j] == 0 and arr[j][i] == 0:
            count += 1

    ans.append(str(count))

print("\n".join(ans))
