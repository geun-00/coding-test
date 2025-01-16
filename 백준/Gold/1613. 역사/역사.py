import sys

input = sys.stdin.readline

n, k = map(int, input().split())

arr = [[100_000 if i != j else 0 for j in range(n)] for i in range(n)]

for _ in range(k):
    a, b = map(int, input().split())
    a -= 1
    b -= 1

    arr[a][b] = 1

for p in range(n):
    for s in range(n):
        for e in range(n):
            arr[s][e] = min(arr[s][e], arr[s][p] + arr[p][e])

s = int(input())

ans = []

for _ in range(s):
    a, b = map(int, input().split())
    a -= 1
    b -= 1

    if arr[a][b] == arr[b][a]:
        ans.append(str(0))
    elif arr[a][b] > arr[b][a]:
        ans.append(str(1))
    else:
        ans.append(str(-1))

print("\n".join(ans))