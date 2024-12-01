import sys

input = sys.stdin.readline
n, m = map(int, input().split())
edges = []
distance = [sys.maxsize] * (n + 1)

for i in range(m):
    start, end, time = map(int, input().split())
    edges.append((start, end, time))

distance[1] = 0

# n-1번 수행
for _ in range(n - 1):
    for start, end, time in edges:
        if distance[start] != sys.maxsize and distance[end] > distance[start] + time:
            distance[end] = distance[start] + time


isCycle = False

# 음수 사이클 여부 확인을 위해 한번 더 수행
for start, end, time in edges:
    if distance[start] != sys.maxsize and distance[end] > distance[start] + time:
        isCycle = True
        break

result = []

if not isCycle:
    for i in range(2, n + 1):
        if distance[i] != sys.maxsize:
            result.append(str(distance[i]))
        else:
            result.append(str(-1))

    print("\n".join(result))
else:
    print(-1)